package lukaspfaff.skullkingAPI.service.game;

import lombok.RequiredArgsConstructor;
import lukaspfaff.skullkingAPI.model.Game;
import lukaspfaff.skullkingAPI.model.Player;
import lukaspfaff.skullkingAPI.repository.AccountRepository;
import lukaspfaff.skullkingAPI.repository.GameRepository;
import lukaspfaff.skullkingAPI.repository.PlayerRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService{
    private final GameRepository gameRepository;
    private final AccountRepository accountRepository;
    private final PlayerRepository playerRepository;
    @Override
    public Game createGame(Game game) {
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        game.setAccount(accountRepository.findByUsername(username));
        game.setCreatedAt(LocalDateTime.now());
        return gameRepository.save(game);
    }

    @Override
    public List<Game> getAllGames() {
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return accountRepository.findByUsername(username).getGames().stream().toList();
    }

    @Override
    public List<Game> getGamesByPlayer(UUID playerId) {
        return getAllGames()
                .stream()
                .filter(game ->
                            game
                                .getGamePlayerScores()
                                .stream()
                                .anyMatch(playerScore ->
                                            playerScore
                                                    .getPlayer()
                                                    .getId()
                                                    .toString()
                                                    .equals(playerId.toString()))
                )
                .collect(Collectors.toList());
    }


}
