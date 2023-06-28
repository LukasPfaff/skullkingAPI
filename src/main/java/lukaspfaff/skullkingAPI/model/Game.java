package lukaspfaff.skullkingAPI.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    @Id
    @GeneratedValue
    private UUID id;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Set<PlayerScore> gamePlayerScores;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="account_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Account account;
}
