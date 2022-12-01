package api.restapi.service;

import api.restapi.domain.dto.PlayerDTO;
import api.restapi.domain.dto.UpdatePlayerDTO;
import api.restapi.domain.entity.Player;
import api.restapi.enums.Position;
import api.restapi.exception.DataFormatException;
import api.restapi.exception.ResourceNotFoundException;
import api.restapi.repos.PlayerRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PlayerService {


    private final PlayerRepo playerRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public PlayerService(PlayerRepo playerRepo, ModelMapper modelMapper) {
        this.playerRepo = playerRepo;
        this.modelMapper = modelMapper;
    }

    public Player createPlayer(PlayerDTO playerDto){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            simpleDateFormat.parse(playerDto.getBirthDate());
        } catch (Exception e) {
            throw new DataFormatException("Wrong date");
        }

        Player player = this.modelMapper.map(playerDto, Player.class);
//        player.setBirthDate(new Date(playerDto.getBirthDate())); TODO: gurmi
        return playerRepo.save(player);
    }

    public Player updatePlayer(UpdatePlayerDTO updatePlayerDTO){
        if (playerRepo.findById(updatePlayerDTO.getId()).isEmpty()){
            throw new ResourceNotFoundException("No such player in DB");
        }

        Player player1 = playerRepo.findById(updatePlayerDTO.getId()).get();
        player1.setName(updatePlayerDTO.getName());
        player1.setCountry(updatePlayerDTO.getCountry());
        if (!updatePlayerDTO.getBirthDate().equals("yyyy-MM-dd")){
            throw new DataFormatException("Wrong date format! Date format must be: yyyy-MM-dd.");
        }
        player1.setBirthDate(updatePlayerDTO.getBirthDate());
        player1.setPosition(updatePlayerDTO.getPosition());

        return playerRepo.save(player1);
    }

    public Page<Player> getAllPlayers(Pageable paging){
        return playerRepo.findAll(paging);
    }

    public Page<Player> getAllByCountry(String country, Pageable paging){
        return playerRepo.findAllByCountryOrderByCreatedAtDesc(country, paging);
    }

    public Page<Player> getAllByPosition(Position position, Pageable paging){
        return playerRepo.findAllByPositionOrderByCreatedAtDesc(position, paging);
    }

}
