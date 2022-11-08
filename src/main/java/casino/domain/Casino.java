package casino.domain;

import java.util.function.Supplier;

public class Casino {

    private final Supplier<Game> gameSupplier;

    public Casino() {
        this(() -> new Game());
    }

    public Casino(Supplier<Game> gameSupplier) {
        this.gameSupplier = gameSupplier;
    }

    public Game newGame() {
        return gameSupplier.get();
    }

    public int getChipsAmount() {
        return 0;
    }
}
