package lukaspfaff.skullkingAPI.controller;

import lombok.RequiredArgsConstructor;
import lukaspfaff.skullkingAPI.model.Player;
import lukaspfaff.skullkingAPI.service.player.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static lukaspfaff.skullkingAPI.controller.GetLocationHelper.getLocation;

@RestController
@RequestMapping("/api/players")
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;

    @PostMapping
    public ResponseEntity<UUID> createPlayer(@RequestBody Player player){
        Player newPlayer = playerService.createPlayer(player);
        return ResponseEntity.created(getLocation(newPlayer.getId())).body(newPlayer.getId());
    }

    @GetMapping
    public ResponseEntity<List<Player>> getPlayers(){
        return ResponseEntity.ok(playerService.getAllPlayers());
    }

    @GetMapping("/avg/{id}")
    public ResponseEntity<Double> getAvgById(@PathVariable String id){
        return ResponseEntity.ok(playerService.getAverageById(UUID.fromString(id)));
    }

    @GetMapping("/max/{id}")
    public ResponseEntity<Integer> getHighscoreById(@PathVariable String id){
        return ResponseEntity.ok(playerService.getHighscoreById(UUID.fromString(id)));
    }
}