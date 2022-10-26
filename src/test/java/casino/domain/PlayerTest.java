package casino.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void playerCantLeaveGameNotParticipatingIn() {
        Game runningGame = new Game();

        runningGame.add(player);

        Game fakeGame = new Game();
        assertThrows(RuntimeException.class, () -> fakeGame.remove(player));
    }
}
