package casino.domain;

import org.junit.jupiter.api.Test;

import static casino.domain.TestDataHelper.BET_AMOUNT;
import static casino.domain.TestDataHelper.BET_NUMBER;
import static casino.domain.TestDataHelper.CHIPS_AMOUNT;
import static casino.domain.TestDataHelper.defaultInitials;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    void newPlayerIsNotInTheGame() {
        Player player = new Player();

        assertFalse(player.isInGame());
    }

    @Test
    void playerCanJoinTheGame() {
        Casino casino = new Casino();
        Game game = casino.newGame();
        Player player = new Player();

        game.add(player);

        assertTrue(player.isInGame());
    }

    @Test
    void playerCanLeaveTheGame() {
        defaultInitials((casino, game, player) -> {
            game.remove(player);

            assertFalse(player.isInGame());
        });
    }

    @Test
    void playerCantLeaveGameNotParticipatingIn() {
        defaultInitials((casino, game, player) -> {
            Game anotherGame = casino.newGame();
            assertThrows(CasinoException.class, () -> anotherGame.remove(player));
        });
    }

    @Test
    void playerCantBetMoreChipsThanHeHas() {
        Casino casino = new Casino();
        Game game = casino.newGame();
        Player player = new Player();
        player.buy(casino, CHIPS_AMOUNT);
        game.add(player);

        assertThatThrownBy(() -> player.bet(player.getChipsAmount() + 1, BET_NUMBER))
            .isInstanceOf(CasinoException.class)
            .hasMessageContaining("Player cannot bet more chips than he has");
    }
}
