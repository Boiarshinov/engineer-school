package casino.domain;

import java.util.Objects;

public class Player {

    private Game currentGame;

    public boolean isInGame() {
        return currentGame != null;
    }

    public void join(Game game) {
        this.currentGame = game;
    }

    public void leave() {
        this.currentGame = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(currentGame, player.currentGame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentGame);
    }
}
