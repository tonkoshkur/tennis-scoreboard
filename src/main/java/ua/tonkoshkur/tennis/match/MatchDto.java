package ua.tonkoshkur.tennis.match;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.tonkoshkur.tennis.match.score.MatchScoreDto;
import ua.tonkoshkur.tennis.player.PlayerDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchDto {

    private PlayerDto player1;
    private PlayerDto player2;
    private PlayerDto winner;

    private MatchScoreDto player1Score = new MatchScoreDto();
    private MatchScoreDto player2Score = new MatchScoreDto();
    
    public MatchDto(PlayerDto player1, PlayerDto player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public boolean isFinished() {
        return winner != null;
    }

}
