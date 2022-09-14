package casino.domain;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlayerTest {

    @Test
    public void playerCanJoin() throws CasinoGameException {
        RollDiceGame game = new RollDiceGame();
        Player player = new Player();

        player.joins(game);

        assertEquals(game, player.activeGame());
    }

    @Test
    public void playerCantJoinTwice() throws CasinoGameException {
        RollDiceGame firstGame = new RollDiceGame();
        Player player = new Player();
        RollDiceGame secondGame = new RollDiceGame();

        player.joins(firstGame);

        assertEquals(firstGame, player.activeGame());

        assertThrows(CasinoGameException.class, () -> player.joins(secondGame));
    }

    @Test
    public void playerCanBuyChip() throws CasinoGameException {
        RollDiceGame game = new RollDiceGame();
        Player player = new Player();
        player.joins(game);

        player.buy(1);

        assertEquals(1, player.getAvailableChips());
    }

    @Test
    public void playerCantBuyNegativeChips() throws CasinoGameException {
        RollDiceGame game = new RollDiceGame();
        Player player = new Player();
        player.joins(game);

        assertThrows(CasinoGameException.class, () -> player.buy(-1));
    }

    @Test
    public void amountOfBoughtChipsEqualsToPlayerDelta() throws CasinoGameException {
        RollDiceGame game = new RollDiceGame();
        Player player = new Player();
        player.joins(game);
        int initialChipsCount = player.getAvailableChips();

        player.buy(10);
        int chipsDelta = player.getAvailableChips() - initialChipsCount;

        assertEquals(10, chipsDelta);
    }
}