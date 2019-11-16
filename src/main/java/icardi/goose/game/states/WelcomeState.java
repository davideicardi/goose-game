package icardi.goose.game.states;

import icardi.goose.game.inputs.GameInput;

public class WelcomeState implements GameState {

    @Override
    public String render() {
        return 
        "🦆  Welcome to goose-game 🦆\n" +
         " type `exit` to close game in any moment.";
    }

    @Override
    public GameState process(GameInput input) {
        return new NoPlayerState();
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