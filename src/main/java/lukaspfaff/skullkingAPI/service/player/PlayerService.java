package lukaspfaff.skullkingAPI.service.player;

import lukaspfaff.skullkingAPI.model.Player;

import java.util.List;
import java.util.UUID;

public interface PlayerService {
    Player createPlayer(Player player);
    Player findById(UUID id);
    List<Player> getAllPlayers();
    Boolean deleteById(UUID id);
    double getAverageById(UUID id);
    int getHighscoreById(UUID id);

}
