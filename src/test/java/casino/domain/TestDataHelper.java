package casino.domain;

public class TestDataHelper {

    public static final int CHIPS_AMOUNT = 500;
    public static final int BET_NUMBER = 3;
    public static final int BET_AMOUNT = 1;
    public static final int LOSE_BET_NUMBER = 2;

    public static Player createPlayerWithChips() {
        Player player = new Player();
        Casino casino = new Casino();

        player.buy(casino, CHIPS_AMOUNT);

        return player;
    }
}
