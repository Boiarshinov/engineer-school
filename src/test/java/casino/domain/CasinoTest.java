package casino.domain;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CasinoTest {

    private static final int CHIPS_AMOUNT = 500;
    private static final int BET_NUMBER = 3;

    @Test
    void playerBuyChips() {
        var player = createPlayerWithChips();

        assertEquals(CHIPS_AMOUNT, player.getChipsAmount());
    }

    @Test
    void playerBet() {
        var player = createPlayerWithChips();
        var game = new Game();
        game.add(player);

        player.bet(CHIPS_AMOUNT, BET_NUMBER);

        assertEquals(0, player.getChipsAmount());

        Map<Player, Bet> bets = game.getBets();
        Bet bet = bets.get(player);
        assertEquals(Bet.bet(CHIPS_AMOUNT, BET_NUMBER), bet);
    }

    private static Player createPlayerWithChips() {
        Player player = new Player();
        Casino casino = new Casino();

        int chipsAmount = 500;

        player.buy(casino, chipsAmount);

        return player;
    }
}
