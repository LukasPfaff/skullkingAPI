package lukaspfaff.skullkingAPI.controller;

import lombok.RequiredArgsConstructor;
import lukaspfaff.skullkingAPI.model.PlayerScore;
import lukaspfaff.skullkingAPI.service.playerscore.PlayerScoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static lukaspfaff.skullkingAPI.controller.GetLocationHelper.getLocation;

@RestController
@RequestMapping("api/playerscores")
@RequiredArgsConstructor
public class PlayerScoreController {
    private final PlayerScoreService playerScoreService;

    @PostMapping
    public ResponseEntity<PlayerScore> addPlayerScore(@RequestBody PlayerScore playerScore){
        playerScoreService.checkPlayer(playerScore);
        if (!playerScoreService.gameExists(playerScore)){
            return ResponseEntity.notFound().build();
        }
        PlayerScore newPlayerScore = playerScoreService.addPlayerScore(playerScore);
        return ResponseEntity.created(getLocation(newPlayerScore.getId())).body(newPlayerScore);
    }
}
