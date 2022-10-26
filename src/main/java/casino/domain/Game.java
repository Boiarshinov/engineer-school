package casino.domain;

public class Game {


    void add(Player player) {
        player.join(this);
    }

    public void remove(Player player) {
        player.leave();
    }
}
