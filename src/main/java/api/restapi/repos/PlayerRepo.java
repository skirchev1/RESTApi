package api.restapi.repos;

import api.restapi.domain.entity.Player;
import api.restapi.enums.Position;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.UUID;

public interface PlayerRepo extends PagingAndSortingRepository<Player, UUID>, JpaRepository<Player, UUID> {

    Optional<Player> findById(UUID uuid);

    Page<Player> findAll(Pageable pageable);

    Page<Player> findAllByCountryOrderByCreatedAtDesc(String country, Pageable pageable);

    Page<Player> findAllByPositionOrderByCreatedAtDesc(Position position, Pageable pageable);


}
