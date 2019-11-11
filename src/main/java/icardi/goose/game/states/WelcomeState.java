package icardi.goose.game.states;

import icardi.goose.game.commands.GameCommand;
import icardi.goose.game.commands.StartCommand;

public class WelcomeState implements GameState {

    @Override
    public String render() {
        return "==Welcome to goose-game==\n" + 
         " type `start` and press ENTER to start a game, `exit` to close game.";
    }

    @Override
    public GameState processCommand(GameCommand command) {
        if (command instanceof StartCommand) {
            return new NoPlayerState();
        }
        return new ErrorState("Invalid operation", this);
    }
}