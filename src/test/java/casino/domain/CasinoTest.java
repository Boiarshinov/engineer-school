package casino.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CasinoTest {

    @Test
    void playerBuyChips() {
        Player player = new Player();
        Casino casino = new Casino();

        int chipsAmount = 500;

        player.buy(casino, chipsAmount);

        assertEquals(chipsAmount, player.getChipsAmount());
    }
}
