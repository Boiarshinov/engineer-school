package casino.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class PlayerTest {

    @Test
    void newPlayerIsNotInTheGame() {
        var player = new Player();

        assertFalse(player.isInGame());
    }

    private static class Player {

        public boolean isInGame() {
            return true;
        }
    }
}
