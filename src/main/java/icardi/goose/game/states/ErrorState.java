package icardi.goose.game.states;

import icardi.goose.game.commands.GameCommand;

public class ErrorState implements GameState {

    private String error;
    private GameState rollbackState;

    public ErrorState(String error, GameState rollbackState) {
        super();
        this.error = error;
        this.rollbackState = rollbackState;
    }

    @Override
    public String render()
    {
        return String.format("%s\n%s", error, rollbackState.render());
    }

    @Override
    public GameState processCommand(GameCommand command)
    {
        return rollbackState.processCommand(command);
    }

    public GameState getRollbackState() {
        return rollbackState;
    }
}