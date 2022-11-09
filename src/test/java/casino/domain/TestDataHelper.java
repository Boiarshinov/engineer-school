package casino.domain;

public class TestDataHelper {

    public static final int CHIPS_AMOUNT = 500;
    public static final int BET_NUMBER = 3;
    public static final int BET_AMOUNT = 1;
    public static final int LOSE_BET_NUMBER = 2;

    public static void defaultInitials(TestCase testCase) {
        Casino casino = new Casino((casino1) -> new Game(casino1, () -> BET_NUMBER));
        Game game = casino.newGame();
        Player player = new Player();
        player.buy(casino, CHIPS_AMOUNT);
        game.add(player);

        testCase.run(casino, game, player);
    }

    @FunctionalInterface
    public interface TestCase {
        void run(Casino casino, Game game, Player player);
    }
}
