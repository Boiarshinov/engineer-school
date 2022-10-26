package casino.domain;

public class Game {


    void add(Player player) {
        player.join(this);
    }
}
