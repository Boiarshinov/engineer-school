package casino.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {
    private Player player = new Player();

    @Test
    void newPlayerIsNotInTheGame() {
        assertFalse(player.isInGame());
    }

    @Test
    void playerCanJoinTheGame() {
        new Game().add(player);

        assertTrue(player.isInGame());
    }

    @Test
    void playerCanLeaveTheGame() {
        Game game = new Game();

        game.add(player);
        game.remove(player);

        assertFalse(player.isInGame());
    }
}
