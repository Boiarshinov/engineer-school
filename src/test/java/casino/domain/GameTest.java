package casino.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static casino.domain.TestDataHelper.BET_AMOUNT;
import static casino.domain.TestDataHelper.BET_NUMBER;
import static casino.domain.TestDataHelper.LOSE_BET_NUMBER;
import static casino.domain.TestDataHelper.defaultInitials;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameTest {

    @Test
    void playerCannotBetMoreThanOneTimeInOneGameRound() {
        defaultInitials(((casino, game, player) -> {
            player.bet(BET_AMOUNT, BET_NUMBER);
            assertThrows(CasinoException.class,
                () -> player.bet(BET_AMOUNT, BET_NUMBER));
        }));
    }

    @Test
    void playerCanLeaveGameBeforePlay() {
        defaultInitials(((casino, game, player) -> {
            player.bet(BET_AMOUNT, BET_NUMBER);

            game.remove(player);

            assertFalse(player.isInGame());
        }));
    }

    @Test
    void onlyPositiveBetChipsAmount() {
        defaultInitials(((casino, game, player) -> {
            assertThatThrownBy(() -> player.bet(0, BET_NUMBER))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Bet should have only positive chips amount");
        }));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    void canBetOnNumbersFromOneToSix(int betNumber) {
        defaultInitials(((casino, game, player) -> {
            player.bet(BET_AMOUNT, betNumber);
        }));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 7})
    void failAtBetOnNumbersOutsideFromOneToSix(int betNumber) {
        defaultInitials(((casino, game, player) -> {
            assertThatThrownBy(() -> player.bet(BET_AMOUNT, betNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Bet number should be between 1 and 6");
        }));
    }

    @Test
    void playerLoseAmountOnBet() {
        defaultInitials(((casino, game, player) -> {
            int initialAmount = player.getChipsAmount();

            player.bet(BET_AMOUNT, BET_NUMBER);

            assertEquals(initialAmount - BET_AMOUNT, player.getChipsAmount());
        }));
    }

    @Test
    void playerGainNothingOnLose() {
        defaultInitials(((casino, game, player) -> {
            player.bet(BET_AMOUNT, LOSE_BET_NUMBER);
            int afterBet = player.getChipsAmount();

            game.round();

            assertEquals(afterBet, player.getChipsAmount());
        }));
    }

    @ParameterizedTest
    @CsvSource(value = {
        "1,6",
        "2,12",
        "3,18"
    })
    void playerGainSixTimesOnWin(int betAmount, int gain) {
        defaultInitials(((casino, game, player) -> {
            player.bet(betAmount, BET_NUMBER);
            int afterBet = player.getChipsAmount();

            game.round();

            assertEquals(afterBet + gain, player.getChipsAmount());
        }));
    }

    @Test
    void casinoGainAllLoseBets() {
        defaultInitials((casino, game, player) -> {
            int playerBetAmount = BET_AMOUNT;
            player.bet(playerBetAmount, LOSE_BET_NUMBER);
            int casinoChipsAmount = casino.getChipsAmount();

            game.round();

            assertEquals(casinoChipsAmount + playerBetAmount, casino.getChipsAmount());
        });
    }

    @Test
    void casinoLoseWhenPlayerWin() {
        defaultInitials((casino, game, player) -> {
            int playerBetAmount = BET_AMOUNT;
            player.bet(playerBetAmount, BET_NUMBER);
            int casinoChipsAmount = casino.getChipsAmount();

            game.round();

            assertEquals(casinoChipsAmount - 6 * playerBetAmount, casino.getChipsAmount());
        });
    }
}
