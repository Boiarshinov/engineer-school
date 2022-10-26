package casino.domain;

public class Bet {
    private final int chips;
    private final int number;

    public Bet(int chips, int number) {
        this.chips = chips;
        this.number = number;
    }

    public int getChips() {
        return chips;
    }

    public int getNumber() {
        return number;
    }
}
