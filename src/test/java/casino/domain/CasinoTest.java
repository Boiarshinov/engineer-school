package casino.domain;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static casino.domain.TestDataHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CasinoTest {

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
}
