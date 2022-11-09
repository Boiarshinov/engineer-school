package casino.domain;

import java.util.function.Function;

public class Casino {

    private final Function<Casino, Game> gameFactory;

    private int chipsAmount;

    public Casino() {
        this(casino -> new Game(casino));
    }

    public Casino(Function<Casino, Game> gameFactory) {
        this.gameFactory = gameFactory;
    }

    public Game newGame() {
        return gameFactory.apply(this);
    }

    public int getChipsAmount() {
        return chipsAmount;
    }

    public void addRevenue(int casinoDelta) {
        chipsAmount += casinoDelta;
    }
}
