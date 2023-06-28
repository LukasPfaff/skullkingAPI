package lukaspfaff.skullkingAPI.service.game;

import lukaspfaff.skullkingAPI.model.Game;

import java.util.List;
import java.util.UUID;

public interface GameService {
    Game createGame(Game game);
    List<Game> getAllGames();
    List<Game> getGamesByPlayer(UUID playerId);
}
