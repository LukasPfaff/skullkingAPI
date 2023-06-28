package lukaspfaff.skullkingAPI.service.playerscore;

import lombok.RequiredArgsConstructor;
import lukaspfaff.skullkingAPI.model.Player;
import lukaspfaff.skullkingAPI.model.PlayerScore;
import lukaspfaff.skullkingAPI.repository.GameRepository;
import lukaspfaff.skullkingAPI.repository.PlayerRepository;
import lukaspfaff.skullkingAPI.repository.PlayerScoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlayerScoreServiceImpl implements PlayerScoreService{
    private final PlayerScoreRepository playerScoreRepository;
    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;

    @Override
    public PlayerScore addPlayerScore(PlayerScore playerScore) {
        return playerScoreRepository.save(playerScore);
    }

    @Override
    public List<PlayerScore> getPlayerScoresByGame(UUID gameId) {
        return playerScoreRepository
                .findAll()
                .stream()
                .filter(playerScore -> playerScore.getGame().getId().equals(gameId))
                .collect(Collectors.toList());
    }

    @Override
    public void checkPlayer(PlayerScore playerScore) {
        if(!playerRepository.existsById(playerScore.getPlayer().getId())){
            Player newPlayer = playerScore.getPlayer();
            playerRepository.save(newPlayer);
        }
    }

    @Override
    public Boolean gameExists(PlayerScore playerScore) {
        return gameRepository.existsById(playerScore.getGame().getId());
    }
}