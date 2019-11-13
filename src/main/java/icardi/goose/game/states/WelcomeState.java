package icardi.goose.game.states;

import icardi.goose.game.commands.GameCommand;
import icardi.goose.game.commands.StartCommand;

public class WelcomeState implements GameState {

    @Override
    public String render() {
        return 
        "🦆  Welcome to goose-game 🦆\n" +
         " type `start` and press ENTER to start a game, `exit` to close game in any moment.";
    }

    @Override
    public GameState processCommand(GameCommand command) {
        if (command instanceof StartCommand) {
            return new NoPlayerState();
        }
        return new ErrorState(ErrorState.INVALID_OPERATION, this);
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