package icardi.goose.game.states;

import java.util.Objects;

import icardi.goose.game.commands.GameCommand;

public class ErrorState implements GameState {

    public static final String INVALID_OPERATION = "Invalid operation";

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
        return String.format(" %s\n%s", error, rollbackState.render());
    }

    @Override
    public GameState processCommand(GameCommand command)
    {
        return rollbackState.processCommand(command);
    }

    public GameState getRollbackState() {
        return rollbackState;
    }

    public String getError() {
        return this.error;
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
        ErrorState other = (ErrorState)o;
        // field comparison
        return Objects.equals(error, other.error)
        && Objects.equals(rollbackState, other.rollbackState);
    }

}