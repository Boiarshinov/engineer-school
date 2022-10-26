package casino.domain;

public class Player {

    private Game currentGame;

    public boolean isInGame() {
        return currentGame != null;
    }

    public void join(Game game) {
        this.currentGame = game;
    }
}
