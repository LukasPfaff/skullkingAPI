package lukaspfaff.skullkingAPI.service.account;

import lukaspfaff.skullkingAPI.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    Account createAccount(Account account);
    Account findByUsername(String username);

    List<Account> getAllAccounts();
    Optional<Account> login(Account account);
}
