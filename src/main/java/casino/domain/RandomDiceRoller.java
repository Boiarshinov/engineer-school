package casino.domain;

import java.security.SecureRandom;
import java.util.Random;

public class RandomDiceRoller implements DiceRoller {

    private final Random random = new SecureRandom();

    @Override
    public int roll() {
        return random.nextInt(6) + 1;
    }
}
