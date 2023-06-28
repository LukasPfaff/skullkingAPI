package lukaspfaff.skullkingAPI.controller;

import lombok.RequiredArgsConstructor;
import lukaspfaff.skullkingAPI.model.Account;
import lukaspfaff.skullkingAPI.service.account.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static lukaspfaff.skullkingAPI.controller.GetLocationHelper.getLocation;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<UUID> createUser(@RequestBody Account account){
        Account newAccount = accountService.createAccount(account);
        return ResponseEntity.created(getLocation(newAccount.getId())).body(newAccount.getId());
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts(){
        return ResponseEntity.ok(accountService.getAllAccounts());
    }
}
