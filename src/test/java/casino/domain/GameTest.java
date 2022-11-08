package casino.domain;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static casino.domain.TestDataHelper.BET_AMOUNT;
import static casino.domain.TestDataHelper.BET_NUMBER;
import static casino.domain.TestDataHelper.createPlayerWithChips;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameTest {

    @Test
    void playerCannotBetMoreThanOneTimeInOneGameRound() {
        Player player = createPlayerWithChips();
        Game game = new Game();
        game.add(player);

        player.bet(BET_AMOUNT, BET_NUMBER);
        assertThrows(CasinoException.class,
            () -> player.bet(BET_AMOUNT, BET_NUMBER));
    }

    @Test
    void playerCanLeaveGameBeforePlay() {
        Player player = createPlayerWithChips();
        Game game = new Game();
        game.add(player);
        player.bet(BET_AMOUNT, BET_NUMBER);

        game.remove(player);

        assertFalse(player.isInGame());
    }

    @Test
    void onlyPositiveBetChipsAmount() {
        Player player = createPlayerWithChips();
        Game game = new Game();
        game.add(player);

        assertThatThrownBy(() -> player.bet(0, BET_NUMBER))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Bet should have only positive chips amount");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    void canBetOnNumbersFromOneToSix(int betNumber) {
        Player player = createPlayerWithChips();
        Game game = new Game();
        game.add(player);

        player.bet(BET_AMOUNT, betNumber);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 7})
    void failAtBetOnNumbersOutsideFromOneToSix(int betNumber) {
        Player player = createPlayerWithChips();
        Game game = new Game();
        game.add(player);

        assertThatThrownBy(() -> player.bet(BET_AMOUNT, betNumber))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Bet number should be between 1 and 6");
    }

    @Test
    void playerLoseAmountOnBet() {
        Player player = createPlayerWithChips();
        Game game = new Game();
        game.add(player);
        int initialAmount = player.getChipsAmount();

        player.bet(BET_AMOUNT, BET_NUMBER);

        assertEquals(initialAmount - BET_AMOUNT, player.getChipsAmount());
    }

    @Disabled
    @Test
    void playerGainNothingOnLose() {
        Player player = createPlayerWithChips();
        Game game = new Game();
        game.add(player);
        player.bet(BET_AMOUNT, BET_NUMBER);
        int afterBet = player.getChipsAmount();

        game.round();

        assertEquals(afterBet, player.getChipsAmount());
    }
}
