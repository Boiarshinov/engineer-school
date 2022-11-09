package casino.domain;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static casino.domain.TestDataHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CasinoTest {

    @Test
    void playerBuyChips() {
        defaultInitials((casino, game, player) ->
            assertEquals(CHIPS_AMOUNT, player.getChipsAmount()));
    }

    @Test
    void playerBet() {
        defaultInitials((casino, game, player) -> {
            player.bet(CHIPS_AMOUNT, BET_NUMBER);

            Map<Player, Bet> bets = game.getBets();
            Bet bet = bets.get(player);
            assertEquals(Bet.bet(CHIPS_AMOUNT, BET_NUMBER), bet);
        });
    }

    @Test
    void casinoLoseChipsWhenPlayerBuyThem() {
        Casino casino = new Casino();
        Player player = new Player();
        int initialCasinoChips = casino.getChipsAmount();

        int playerBought = CHIPS_AMOUNT;
        player.buy(casino, playerBought);

        assertEquals(initialCasinoChips - playerBought, casino.getChipsAmount());
    }
}
