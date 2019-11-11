package icardi.goose.game.states;

import icardi.goose.game.commands.GameCommand;

public class VoidState implements GameState {

    @Override
    public String render()
    {
        return "<void>";
    }

    @Override
    public GameState processCommand(GameCommand command)
    {
        return this;
    }

}