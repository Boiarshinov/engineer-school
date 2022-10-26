package casino.domain;

import org.junit.jupiter.api.Test;

import static casino.domain.TestDataHelper.createPlayerWithChips;
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

        assertFalse(game.getPlayers().contains(player));
    }
}
