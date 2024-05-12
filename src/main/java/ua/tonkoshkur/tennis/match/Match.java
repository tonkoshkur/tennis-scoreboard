package ua.tonkoshkur.tennis.match;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.tonkoshkur.tennis.player.Player;

import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name = "Matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "player1", nullable = false)
    private Player player1;

    @ManyToOne
    @JoinColumn(name = "player2", nullable = false)
    private Player player2;

    @ManyToOne
    @JoinColumn(name = "winner", nullable = false)
    private Player winner;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Match match = (Match) object;
        return Objects.equals(id, match.id) && Objects.equals(player1, match.player1) && Objects.equals(player2, match.player2) && Objects.equals(winner, match.winner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, player1, player2, winner);
    }
}
