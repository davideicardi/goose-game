package icardi.goose.game.states;

import icardi.goose.game.inputs.GameInput;

public class VoidState implements GameState {

    @Override
    public String render()
    {
        return "<void>";
    }

    @Override
    public GameState process(GameInput input) {
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