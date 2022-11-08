package casino.domain;

import java.util.Objects;

public class Bet {
    private final int chips;
    private final int number;

    private Bet(int chips, int number) {
        this.chips = chips;
        this.number = number;
    }

    public static Bet bet(int chips, int number) {
        if (number < 1 || number > 6) {
            throw new IllegalArgumentException("Bet number should be between 1 and 6. but was: " + number);
        }
        return new Bet(chips, number);
    }

    public int getChips() {
        return chips;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bet bet = (Bet) o;
        return chips == bet.chips && number == bet.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(chips, number);
    }
}
