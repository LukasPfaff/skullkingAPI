package lukaspfaff.skullkingAPI.service.player;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lukaspfaff.skullkingAPI.model.Account;
import lukaspfaff.skullkingAPI.model.Game;
import lukaspfaff.skullkingAPI.model.Player;
import lukaspfaff.skullkingAPI.model.PlayerScore;
import lukaspfaff.skullkingAPI.repository.AccountRepository;
import lukaspfaff.skullkingAPI.repository.PlayerRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlayerServiceImpl implements PlayerService{
    private final PlayerRepository playerRepository;
    private final AccountRepository accountRepository;

    @Override
    public Player createPlayer(Player player) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if ( principal instanceof UserDetails){
            String username = ((UserDetails)principal).getUsername();
            Account account = accountRepository.findByUsername(username);
            player.setAccount(account);
        }

        return playerRepository.save(player);
    }

    @Override
    public Player findById(UUID id) {
        if (playerRepository.findById(id).isPresent()){
            return playerRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public List<Player> getAllPlayers() {
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        UUID userId = accountRepository.findByUsername(username).getId();
        return accountRepository.findByUsername(username).getPlayers().stream().toList();
    }

    @Override
    public Boolean deleteById(UUID id) {
        playerRepository.deleteById(id);
        return true;
    }

    @Override
    public double getAverageById(UUID id) {
        return findById(id)
                .getPlayerPlayerScores()
                .stream()
                .mapToDouble(PlayerScore::getScore)
                .average()
                .orElse(0.0);
    }

    @Override
    public int getHighscoreById(UUID id) {
        return findById(id)
                .getPlayerPlayerScores()
                .stream()
                .mapToInt(PlayerScore::getScore)
                .max()
                .orElse(0);
    }
}
