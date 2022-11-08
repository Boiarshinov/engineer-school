package casino.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static casino.domain.TestDataHelper.createPlayerWithChips;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    void canBetOnNumbersFromOneToSix(int betNumber) {
        Player player = createPlayerWithChips();
        Game game = new Game();
        game.add(player);

        player.bet(10, betNumber);
    }

    @Disabled
    @ParameterizedTest
    @ValueSource(ints = {0, 7})
    void failAtBetOnNumbersOutsideFromOneToSix(int betNumber) {
        Player player = createPlayerWithChips();
        Game game = new Game();
        game.add(player);

        assertThatThrownBy(() -> player.bet(10, betNumber))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Bet number should be between 1 and 6");
    }

}
