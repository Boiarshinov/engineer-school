package casino.domain;

import one.util.streamex.EntryStream;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Game {

    public static final int WIN_MULTIPLIER = 6;
    private final Set<Player> players = new HashSet<>();
    private final Map<Player, Bet> playerBets = new HashMap<>();

    private final DiceRoller diceRoller;
    private final Casino casino;

    public Game(Casino casino) {
        this(casino, new RandomDiceRoller());
    }

    public Game(Casino casino, DiceRoller diceRoller) {
        this.casino = casino;
        this.diceRoller = diceRoller;
    }

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
        if (playerBets.containsKey(player)) {
            throw new CasinoException("Only one bet at a time allowed per player");
        }
        playerBets.put(player, bet);
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void round() {
        int winNumber = diceRoller.roll();

        EntryStream.of(playerBets)
            .filterValues(bet -> bet.getNumber() == winNumber)
            .forKeyValue((player, bet) -> player.addChips(bet.getChips() * WIN_MULTIPLIER));

        int casinoGain = playerBets.values().stream()
            .filter(bet -> bet.getNumber() != winNumber)
            .map(Bet::getChips)
            .reduce(0, Integer::sum);

        int casinoLoss = playerBets.values().stream()
            .filter(bet -> bet.getNumber() != winNumber)
            .map(Bet::getChips)
            .reduce(0, Integer::sum);

        int casinoDelta = casinoGain - casinoLoss;

    }
}
