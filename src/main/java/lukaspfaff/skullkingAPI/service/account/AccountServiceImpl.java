package lukaspfaff.skullkingAPI.service.account;

import lombok.RequiredArgsConstructor;
import lukaspfaff.skullkingAPI.model.Account;
import lukaspfaff.skullkingAPI.model.Role;
import lukaspfaff.skullkingAPI.repository.AccountRepository;
import lukaspfaff.skullkingAPI.repository.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    @Override
    public Account createAccount(Account account) {
        account.setPassword(encoder.encode(account.getPassword()));
        Role role = roleRepository.findByName("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        account.setRoles(roles);

        return accountRepository.save(account);
    }

    @Override
    public Account findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> login(Account account) {
        Account accountInDb = findByUsername(account.getUsername());
        return encoder.matches(account.getPassword(), accountInDb.getPassword()) ? Optional.of(accountInDb) : Optional.empty();
    }
}