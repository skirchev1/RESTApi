package api.restapi.controlers;

import api.restapi.domain.dto.PlayerDTO;
import api.restapi.domain.dto.UpdatePlayerDTO;
import api.restapi.domain.entity.Player;
import api.restapi.enums.Position;
import api.restapi.exception.ResourceNotFoundException;
import api.restapi.service.PlayerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/api/player")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getAllPlayers(
            @RequestParam(required = false) String country,
            @RequestParam(required = false)Position position,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
            ){
        List<Player> players = new ArrayList<>();
        Pageable paging = PageRequest.of(page, size);

        Page<Player> pagePlayers = null;

        if (country == null && position == null){
            pagePlayers = playerService.getAllPlayers(paging);
        }else if (country == null){
            pagePlayers = playerService.getAllByPosition(position, paging);
        }else if (position == null){
            pagePlayers = playerService.getAllByCountry(country, paging);
        }

        if (pagePlayers == null) throw new ResourceNotFoundException("Something went wrong");

        players = pagePlayers.getContent();

        Map<String, Object> response = new HashMap<>();
        response.put("players", players);
        response.put("currentPage", pagePlayers.getNumber());
        response.put("totalPlayers", pagePlayers.getTotalElements());
        response.put("totalPages", pagePlayers.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Player> createPlayer(@RequestBody @Valid PlayerDTO playerDTO) {
        Player player = playerService.createPlayer(playerDTO);
        return ResponseEntity.ok().body(player);
    }

    @PutMapping
    @ResponseBody
    public Player updatePlayer(@RequestBody @Valid UpdatePlayerDTO updatePlayerDTO){
       return playerService.updatePlayer(updatePlayerDTO);
    }
}
