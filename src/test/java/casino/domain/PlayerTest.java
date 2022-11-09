package casino.domain;

import org.junit.jupiter.api.Test;

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
        TestDataHelper.defaultInitials((casino, game, player) -> {
            game.remove(player);

            assertFalse(player.isInGame());
        });
    }

    @Test
    void playerCantLeaveGameNotParticipatingIn() {
        TestDataHelper.defaultInitials((casino, game, player) -> {
            Game anotherGame = casino.newGame();
            assertThrows(CasinoException.class, () -> anotherGame.remove(player));
        });
    }
}
