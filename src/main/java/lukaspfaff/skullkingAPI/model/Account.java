package lukaspfaff.skullkingAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotNull;

import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(unique = true)
    @NotNull
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    private String password;

    @OneToMany(mappedBy = "account")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Game> games;

    @OneToMany(mappedBy = "account")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Player> players;

    private boolean enabled = true;
    private boolean credentialsNonExpired = true;
    private boolean nonExpired = true;
    private boolean nonLocked = true;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "AccountRole",
            joinColumns = @JoinColumn(name = "accountId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "roleId", referencedColumnName = "id")
    )
    private Set<Role> roles;
}