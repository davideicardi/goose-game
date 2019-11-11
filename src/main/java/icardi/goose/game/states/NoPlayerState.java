package icardi.goose.game.states;

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
        return new ErrorState("Invalid operation", this);
    }
}