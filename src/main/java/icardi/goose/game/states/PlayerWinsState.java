package icardi.goose.game.states;

import icardi.goose.game.Game;
import icardi.goose.game.Player;

public class PlayerWinsState implements GameState {

    private final Player player;

    public PlayerWinsState(Player player) {
        super();
        this.player = player;
    }

    @Override
    public String render() {
        return String.format("🏆 %s Wins!!", player.getName());
    }

    @Override
    public GameState process(Game game) {
        return new ExitState();
    }

    @Override
    public boolean equals(Object o) {
        // self check
        if (this == o) {
            return true;
        }
        // null check
        if (o == null) {
            return false;
        }
        // type check and cast
        if (getClass() != o.getClass()) {
            return false;
        }

        return true;
    }
}