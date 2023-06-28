package lukaspfaff.skullkingAPI.repository;

import lukaspfaff.skullkingAPI.model.PlayerScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlayerScoreRepository extends JpaRepository<PlayerScore, UUID> {

}
