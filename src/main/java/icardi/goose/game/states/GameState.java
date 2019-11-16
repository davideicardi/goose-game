package icardi.goose.game.states;

import icardi.goose.game.commands.GameCommand;
import icardi.goose.game.Game;
import icardi.goose.game.commands.ExitCommand;
import icardi.goose.game.inputs.GameInput;

public interface GameState
{
    String render();

    GameState process(Game game, GameInput input);

    static GameState processCmd(GameCommand command, GameState currentState) {
        if (command instanceof ExitCommand) {
            return new ExitState();
        }

        return new ErrorState(ErrorState.INVALID_OPERATION, currentState);
    }
}