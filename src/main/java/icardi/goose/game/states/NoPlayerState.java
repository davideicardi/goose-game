package icardi.goose.game.states;

import icardi.goose.game.Player;
import icardi.goose.game.commands.AddPlayerCommand;
import icardi.goose.game.commands.GameCommand;

public class NoPlayerState implements GameState {

    @Override
    public String render()
    {
        return " Insert the first player by typing: `add player {yourName}`";
    }

    @Override
    public GameState processCommand(GameCommand command)
    {
        if (command instanceof AddPlayerCommand) {
            AddPlayerCommand apc = (AddPlayerCommand)command;
            return new Player1AddedState(new Player(apc.getName()));
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