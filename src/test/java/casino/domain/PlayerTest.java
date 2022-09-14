package casino.domain;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    public void playerCanBetOnNumbersFromOneToSix(int betValue) throws CasinoGameException {
        RollDiceGame game = new RollDiceGame();
        Player player = new Player();
        player.joins(game);
        player.buy(1);

        player.bet(Bet.createBet(1, betValue));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 7})
    public void playerCanNotBetOnNumbersOutsideRangeOneToSix(int betValue) throws CasinoGameException {
        RollDiceGame game = new RollDiceGame();
        Player player = new Player();
        player.joins(game);
        player.buy(1);

        assertThrows(CasinoGameException.class, () -> player.bet(Bet.createBet(1, betValue)));
    }
}