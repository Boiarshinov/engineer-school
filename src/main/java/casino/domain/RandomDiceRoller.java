package casino.domain;

import java.util.Random;

public class RandomDiceRoller implements DiceRoller {

    private static final Random RANDOM = new Random();

    @Override
    public int roll() {
        return 1 + RANDOM.nextInt(6);
    }
}
