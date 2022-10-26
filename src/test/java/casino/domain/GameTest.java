package casino.domain;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static casino.domain.TestDataHelper.createPlayerWithChips;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameTest {

    @Test
    void playerCannotBetMoreThanOneTimeInOneGameRound() {
        Player player = createPlayerWithChips();
        Game game = new Game();
        game.add(player);

        player.bet(1, 2);
        assertThrows(CasinoException.class,
            () -> player.bet(1, 2));
    }

    @Test
    void playerCanLeaveGameBeforePlay() {
        Player player = createPlayerWithChips();
        Game game = new Game();
        game.add(player);
        player.bet(1, 2);

        game.remove(player);

        assertFalse(player.isInGame());
    }

    @Test
    void onlyPositiveBetChipsAmount() {
        Player player = createPlayerWithChips();
        Game game = new Game();
        game.add(player);
        var casinoException = assertThrows(IllegalArgumentException.class,
            () -> player.bet(0, 2));
        assertEquals("Bet should have only positive chips amount, but was: 0", casinoException.getMessage());
    }

    //TODO
    @Disabled
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    void onlyBetOnNumbersFromOneToSix(int betNumber) {
        Player player = createPlayerWithChips();
        Game game = new Game();
        game.add(player);
        var casinoException = assertThrows(IllegalArgumentException.class,
                () -> player.bet(10, betNumber));
        assertEquals("Bet should have only positive chips amount, but was: 0", casinoException.getMessage());
    }
}
