package icardi.goose.game.states;

import icardi.goose.game.Game;

public class ExitState implements GameState {

    @Override
    public String render() {
        return "Thank you for playing!";
    }

    @Override
    public GameState process(Game game) {
        return this;
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