package casino.domain;

@FunctionalInterface
public interface DiceRoller {

    /**
     * @return from 1 to 6
     */
    int roll();
}
