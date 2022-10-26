package casino.domain;


import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static casino.domain.Bet.createBet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class RollDiceGameTest {

    private static final int WINNING_SCORE = 6;
    private static final int LOOSING_SCORE = 2;

    @Test
    void onlySixPlayersCanJoinToGame() throws CasinoGameException {
        RollDiceGame game = new RollDiceGame();

        List<Player> players = IntStream.range(0, 6).mapToObj(i -> new Player()).collect(Collectors.toList());
        for (Player player : players) {
            player.joins(game);
        }

        Player limitedPlayer = new Player();
        assertThrows(CasinoGameException.class, () -> limitedPlayer.joins(game));
    }
    
    @Test
    void winnerGetFiveMoreBets() throws CasinoGameException {
        int basicBet = 1;
        int winningScore = 6;
        RollDiceGame game = new RollDiceGame(() -> winningScore);

        Player winner = new Player();
        winner.buy(basicBet);
        winner.joins(game);
        winner.bet(createBet(basicBet, winningScore));

        game.play();

        assertEquals(basicBet * 6, winner.getAvailableChips());
    }

    @Test
    void losersLoseTheirBets() throws CasinoGameException {
        int basicBet = 1;
        RollDiceGame game = new RollDiceGame(() -> WINNING_SCORE);

        List<Player> players = IntStream.range(0, 5).mapToObj(i -> new Player()).collect(Collectors.toList());
        for (Player player : players) {
            player.buy(basicBet);
            player.joins(game);
            player.bet(createBet(basicBet, 1));
        }
        Player winner = new Player();
        winner.buy(basicBet);
        winner.joins(game);
        winner.bet(createBet(basicBet, WINNING_SCORE));

        game.play();

        assertEquals(basicBet * 6, winner.getAvailableChips());
    }

    @Test
    public void playCausesPlayerToWinWithSixBets() throws CasinoGameException {
        var betAmount = 1;
        var winner = spy(Player.class);
        RollDiceGame game = new RollDiceGame(() -> WINNING_SCORE);
        winner.buy(betAmount);
        winner.joins(game);
        winner.bet(createBet(betAmount, WINNING_SCORE));

        game.play();

        verify(winner).win(betAmount * 6);
    }

    @Test
    public void playCausesPlayerToLoseTheirBets() throws CasinoGameException {
        var betAmount = 1;
        var loser = spy(Player.class);
        RollDiceGame game = new RollDiceGame(() -> WINNING_SCORE);
        loser.buy(betAmount);
        loser.joins(game);
        loser.bet(createBet(betAmount, LOOSING_SCORE));

        game.play();

        verify(loser).lose();
    }

    @Test
    public void playCausesManyPlayersToWinWithSixBets() throws CasinoGameException {
        var betAmount = 1;
        var winner1 = spy(Player.class);
        var winner2 = spy(Player.class);
        RollDiceGame game = new RollDiceGame(() -> WINNING_SCORE);
        winner1.buy(betAmount);
        winner1.joins(game);
        winner1.bet(createBet(betAmount, WINNING_SCORE));
        winner2.buy(betAmount);
        winner2.joins(game);
        winner2.bet(createBet(betAmount, WINNING_SCORE));

        game.play();

        verify(winner1).win(betAmount * 6);
        verify(winner2).win(betAmount * 6);
    }

    @Test
    void winnerHaveSixBetsAfterGame() {
        var betAmount = 1;
        int initialChips = betAmount;
        var winner = new Player();
        RollDiceGame game = new RollDiceGame(() -> WINNING_SCORE);
        winner.buy(initialChips);
        winner.joins(game);
        winner.bet(createBet(betAmount, WINNING_SCORE));

        game.play();

        assertEquals(initialChips * 6, winner.getAvailableChips());
    }

    @Test
    void loserLoseHisBetAfterGame() {
        var betAmount = 1;
        int initialChips = betAmount;
        var loser = new Player();
        RollDiceGame game = new RollDiceGame(() -> WINNING_SCORE);
        loser.buy(initialChips);
        loser.joins(game);
        loser.bet(createBet(betAmount, LOOSING_SCORE));

        game.play();

        assertEquals(initialChips - betAmount, loser.getAvailableChips());
    }

    @Test
    void winnersHaveSixBetsAfterGame() {
        RollDiceGame game = new RollDiceGame(() -> WINNING_SCORE);

        var betAmount = 1;
        int initialChips = betAmount;
        var winner = new Player();
        winner.buy(initialChips);
        winner.joins(game);
        winner.bet(createBet(betAmount, WINNING_SCORE));

        game.play();

        assertEquals(initialChips * 6, winner.getAvailableChips());
    }
}