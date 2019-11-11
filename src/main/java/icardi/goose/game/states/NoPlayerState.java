package icardi.goose.game.states;

import icardi.goose.game.Player;
import icardi.goose.game.commands.AddPlayerCommand;
import icardi.goose.game.commands.GameCommand;

public class NoPlayerState implements GameState {

    @Override
    public String render()
    {
        return " Insert the first player by typing: add player {yourName}";
    }

    @Override
    public GameState processCommand(GameCommand command)
    {
        if (command instanceof AddPlayerCommand) {
            AddPlayerCommand apc = (AddPlayerCommand)command;
            return new Player1AddedState(new Player(apc.getName()));
        }
        return new ErrorState("Invalid operation", this);
    }
}