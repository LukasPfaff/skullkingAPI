package lukaspfaff.skullkingAPI.service.playerscore;

import lukaspfaff.skullkingAPI.model.PlayerScore;

import java.util.List;
import java.util.UUID;

public interface PlayerScoreService {
    PlayerScore addPlayerScore(PlayerScore playerScore);
    List<PlayerScore> getPlayerScoresByGame(UUID gameId);
    void checkPlayer(PlayerScore playerScore);
    Boolean gameExists(PlayerScore playerScore);
}
