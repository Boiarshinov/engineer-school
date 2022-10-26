package casino.domain;

import java.util.HashSet;
import java.util.Set;

public class Game {

    private final Set<Player> players = new HashSet<>();

    void add(Player player) {
        player.join(this);
        players.add(player);
    }

    public void remove(Player player) {
        if (!players.contains(player)) {
            throw new CasinoException("There is no such player in the game!: " + player);
        }

        player.leave();
        players.remove(player);
    }
}
