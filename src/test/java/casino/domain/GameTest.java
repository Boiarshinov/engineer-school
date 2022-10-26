package casino.domain;

import org.junit.jupiter.api.Test;

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

    private static Player createPlayerWithChips() {
        Player player = new Player();
        Casino casino = new Casino();

        int chipsAmount = 500;

        player.buy(casino, chipsAmount);

        return player;
    }
}
