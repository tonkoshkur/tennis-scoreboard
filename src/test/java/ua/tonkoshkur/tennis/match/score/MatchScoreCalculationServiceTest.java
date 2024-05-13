package ua.tonkoshkur.tennis.match.score;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.tonkoshkur.tennis.match.MatchDto;
import ua.tonkoshkur.tennis.player.PlayerDto;

public class MatchScoreCalculationServiceTest {

    private static MatchScoreCalculationService matchScoreCalculationService;
    private static PlayerDto player1;
    private static PlayerDto player2;
    private MatchDto match;

    @BeforeAll
    public static void init() {
        matchScoreCalculationService = new MatchScoreCalculationServiceImpl();
        player1 = new PlayerDto(1, "Ivan");
        player2 = new PlayerDto(2, "Bob");
    }

    @BeforeEach
    public void createMatch() {
        match = new MatchDto(player1, player2);
    }

    @Test
    void givenNewMatch_whenPlayer1WinsPoints_thenPlayer1Has15Points() {
        matchScoreCalculationService.updateScore(match, player1.getId());
        Assertions.assertEquals(15, match.getPlayer1Score().getPoints());
    }

    @Test
    void givenMatchWithPlayer1Has40PointsAndPlayer2Has0Points_whenPlayer1WinsPoints_thenPlayer1WinsGame() {
        match.getPlayer1Score().setPoints(40);
        match.getPlayer2Score().setPoints(0);

        matchScoreCalculationService.updateScore(match, player1.getId());

        Assertions.assertEquals(1, match.getPlayer1Score().getGames());
    }

    @Test
    void testPlayer1WinsPointsAtDeuce() {
        Assertions.assertAll(
                this::givenMatchWithPlayersAtDeuce_whenPlayer1WinsPoints_thenPlayer1DoesNotWinGame,
                this::givenMatchWithPlayersAtDeuce_whenPlayer1WinsPoints_thenPlayer1HasAdvantage,
                this::givenMatchWithPlayersAtDeuceAndPlayer1HasAdvantage_whenPlayer1WinsPoints_thenPlayer1WinsGame,
                this::givenMatchWithPlayersAtDeuceAndPlayer1HasAdvantage_whenPlayer2WinsPoints_thenPlayer1LosesAdvantage,
                this::givenMatchWithPlayersAtDeuceAndPlayer1HasAdvantage_whenPlayer2WinsPoints_thenPlayer2HasNoAdvantage
        );
    }

    @Test
    void givenMatchWithPlayersAtDeuce_whenPlayer1WinsPoints_thenPlayer1DoesNotWinGame() {
        match.getPlayer1Score().setPoints(40);
        match.getPlayer2Score().setPoints(40);

        matchScoreCalculationService.updateScore(match, player1.getId());

        Assertions.assertEquals(0, match.getPlayer1Score().getGames());
    }

    @Test
    void givenMatchWithPlayersAtDeuce_whenPlayer1WinsPoints_thenPlayer1HasAdvantage() {
        match.getPlayer1Score().setPoints(40);
        match.getPlayer2Score().setPoints(40);

        matchScoreCalculationService.updateScore(match, player1.getId());

        Assertions.assertTrue(match.getPlayer1Score().isAdvantage());
    }

    @Test
    void givenMatchWithPlayersAtDeuceAndPlayer1HasAdvantage_whenPlayer1WinsPoints_thenPlayer1WinsGame() {
        match.getPlayer1Score().setPoints(40);
        match.getPlayer2Score().setPoints(40);
        match.getPlayer1Score().setAdvantage(true);

        matchScoreCalculationService.updateScore(match, player1.getId());

        Assertions.assertEquals(1, match.getPlayer1Score().getGames());
    }

    @Test
    void givenMatchWithPlayersAtDeuceAndPlayer1HasAdvantage_whenPlayer2WinsPoints_thenPlayer1LosesAdvantage() {
        match.getPlayer1Score().setPoints(40);
        match.getPlayer2Score().setPoints(40);
        match.getPlayer1Score().setAdvantage(true);

        matchScoreCalculationService.updateScore(match, player2.getId());

        Assertions.assertFalse(match.getPlayer1Score().isAdvantage());
    }

    @Test
    void givenMatchWithPlayersAtDeuceAndPlayer1HasAdvantage_whenPlayer2WinsPoints_thenPlayer2HasNoAdvantage() {
        match.getPlayer1Score().setPoints(40);
        match.getPlayer2Score().setPoints(40);
        match.getPlayer1Score().setAdvantage(true);

        matchScoreCalculationService.updateScore(match, player2.getId());

        Assertions.assertFalse(match.getPlayer2Score().isAdvantage());
    }

    @Test
    void givenMatchWithPlayer1Has5GamesAndPlayer2Has0Games_whenPlayer1WinsGame_thenPlayer1WinsSet() {
        match.getPlayer1Score().setPoints(40);
        match.getPlayer1Score().setGames(5);

        matchScoreCalculationService.updateScore(match, player1.getId());

        Assertions.assertEquals(1, match.getPlayer1Score().getSets());
    }

    @Test
    void givenMatchWithPlayersHave5Games_whenPlayer1WinsGame_thenPlayer1DoesNotWinSet() {
        match.getPlayer1Score().setPoints(40);
        match.getPlayer1Score().setGames(5);
        match.getPlayer2Score().setGames(5);

        matchScoreCalculationService.updateScore(match, player1.getId());

        Assertions.assertEquals(0, match.getPlayer1Score().getSets());
    }

    @Test
    void givenMatchWithPlayer1Has1Sets_whenPlayer1WinsSet_thenPlayer1WinsMatch() {
        match.getPlayer1Score().setPoints(40);
        match.getPlayer1Score().setGames(5);
        match.getPlayer1Score().setSets(1);

        matchScoreCalculationService.updateScore(match, player1.getId());

        Assertions.assertEquals(player1.getId(), match.getWinner().getId());
    }

    @Test
    void givenMatchWithPlayersHave1Sets_whenPlayer1WinsSet_thenMatchIsNotFinished() {
        match.getPlayer1Score().setPoints(40);
        match.getPlayer1Score().setGames(5);
        match.getPlayer1Score().setSets(1);
        match.getPlayer2Score().setSets(1);

        matchScoreCalculationService.updateScore(match, player1.getId());

        Assertions.assertFalse(match.isFinished());
    }
}
