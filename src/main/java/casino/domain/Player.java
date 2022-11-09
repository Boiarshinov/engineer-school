package casino.domain;

import java.util.Objects;

public class Player {

    private Game currentGame;

    private int chipsAmount = 0;

    public boolean isInGame() {
        return currentGame != null;
    }

    public void join(Game game) {
        this.currentGame = game;
    }

    public void leave() {
        this.currentGame = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(currentGame, player.currentGame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentGame);
    }

    public void buy(Casino casino, int chipsAmount) {
        addChips(chipsAmount);
    }

    public void addChips(int amount) {
        this.chipsAmount += amount;
    }

    public int getChipsAmount() {
        return chipsAmount;
    }

    public void bet(int chipsAmount, int betNumber) {
        if (chipsAmount > this.chipsAmount) {
            throw new CasinoException("Player cannot bet more chips than he has");
        }
        Bet bet = Bet.bet(chipsAmount, betNumber);
        currentGame.addBet(this, bet);
        this.chipsAmount -= chipsAmount;
    }
}
