package casino.domain;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Game {

    private final Set<Player> players = new HashSet<>();
    private final Map<Player, Bet> playerBets = new ConcurrentHashMap<>();

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

    public Map<Player, Bet> getBets() {
        //todo return copy
        return playerBets;
    }

    public void addBet(Player player, Bet bet) {
        playerBets.put(player, bet);
    }
}
