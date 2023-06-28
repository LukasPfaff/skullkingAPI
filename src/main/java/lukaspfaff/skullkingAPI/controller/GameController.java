package lukaspfaff.skullkingAPI.controller;

import lombok.RequiredArgsConstructor;
import lukaspfaff.skullkingAPI.model.Game;
import lukaspfaff.skullkingAPI.service.game.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static lukaspfaff.skullkingAPI.controller.GetLocationHelper.getLocation;

@RestController
@RequestMapping("api/games")
@RequiredArgsConstructor
public class GameController {
    public final GameService gameService;

    @PostMapping
    public ResponseEntity<UUID> createGame(Game game){
        Game newGame = gameService.createGame(game);
        return ResponseEntity.created(getLocation(newGame.getId())).body(newGame.getId());
    }

    @GetMapping
    public ResponseEntity<List<Game>> getAllGames(){
        return ResponseEntity.ok(gameService.getAllGames());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Game>> getGamesByPlayer(@PathVariable String id){
        return ResponseEntity.ok(gameService.getGamesByPlayer(UUID.fromString(id)));
    }
}
