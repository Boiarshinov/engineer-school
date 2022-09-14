package casino.domain;

public class Bet {
    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 6;
    private final int amount;
    private final int score;

    public static Bet createBet(int amount, int score) throws CasinoGameException {
        if (score < MIN_VALUE) {
            throw new CasinoGameException("Score can not be less than " + MIN_VALUE);
        }
        if (score > MAX_VALUE) {
            throw new CasinoGameException("Score can not be more than " + MAX_VALUE);
        }
        return new Bet(amount, score);
    }

    private Bet(int amount, int score) {
        this.amount = amount;
        this.score = score;
    }

    public int getAmount() {
        return amount;
    }

    public int getScore() {
        return score;
    }
}