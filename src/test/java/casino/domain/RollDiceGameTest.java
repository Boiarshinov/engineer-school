package casino.domain;


import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RollDiceGameTest {

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
        winner.bet(new Bet(basicBet, winningScore));

        game.play();

        assertEquals(basicBet * 6, winner.getAvailableChips());
    }

    @Test
    void losersLoseTheirBets() throws CasinoGameException {
        int basicBet = 1;
        int winningScore = 6;
        RollDiceGame game = new RollDiceGame(() -> winningScore);

        List<Player> players = IntStream.range(0, 5).mapToObj(i -> new Player()).collect(Collectors.toList());
        for (Player player : players) {
            player.buy(basicBet);
            player.joins(game);
            player.bet(new Bet(basicBet, 1));
        }
        Player winner = new Player();
        winner.buy(basicBet);
        winner.joins(game);
        winner.bet(new Bet(basicBet, winningScore));

        game.play();

        assertEquals(basicBet * 6, winner.getAvailableChips());
    }
}