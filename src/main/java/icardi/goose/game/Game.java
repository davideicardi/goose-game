package icardi.goose.game;

import icardi.goose.game.commands.ExitCommand;
import icardi.goose.game.commands.GameCommand;
import icardi.goose.game.inputs.GameInput;
import icardi.goose.game.outputs.GameOutput;
import icardi.goose.game.states.GameState;
import icardi.goose.game.states.VoidState;

public class Game {
    private GameState _state = new VoidState();
    private GameInput _input;
    private GameOutput _output;

    public Game(GameInput input, GameOutput output) {
        super();

        _input = input;
        _output = output;
    }

    public void start(GameState state) {
        transition(state);

        while (true) {
            _output.display(_state.render());

            GameCommand cmd = _input.waitForCommand();

            if (cmd instanceof ExitCommand) {
                _output.display("Thank you");
                break;
            }

            GameState nextState = _state.processCommand(cmd);

            if (nextState != _state) {
                transition(nextState);
            }
        }
    }

    private void transition(GameState state) {
        _state = state;
    }
}