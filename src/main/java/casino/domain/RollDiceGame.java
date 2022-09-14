package casino.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class RollDiceGame {
    private HashMap<Player, Bet> playersBets = new HashMap<Player, Bet>();

    private final Set<Player> players = new HashSet<>();

    private final DiceRoller diceRoller;

    public RollDiceGame() {
        this.diceRoller = new RandomDiceRoller();
    }

    public RollDiceGame(DiceRoller diceRoller) {
        this.diceRoller = diceRoller;
    }

    public void addBet(Player player, Bet bet) {
        playersBets.put(player, bet);
    }

    public void play() throws CasinoGameException {
        int winningScore = diceRoller.roll();

        for (Player player : playersBets.keySet()) {
            Bet bet = playersBets.get(player);
            if (bet.getScore() == winningScore) {
                player.win(bet.getAmount() * 6);
            } else {
                player.lose();
            }
        }
        playersBets.clear();
    }

    public void leave(Player player) throws CasinoGameException {
        if (!playersBets.containsKey(player)) {
            return;
        }

        playersBets.remove(player);
        players.remove(player);
    }

    public HashMap<Player, Bet> getPlayersBets() {
        return playersBets;
    }

    public void addPlayer(Player player) throws CasinoGameException {
        if (players.size() >= 6) {
            throw new CasinoGameException("Dice Game can not have more than 6 players");
        }
        players.add(player);
    }
}
