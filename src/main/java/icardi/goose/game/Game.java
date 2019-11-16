package icardi.goose.game;

import icardi.goose.game.inputs.GameInput;
import icardi.goose.game.outputs.GameOutput;
import icardi.goose.game.states.ExitState;
import icardi.goose.game.states.GameState;
import icardi.goose.game.states.VoidState;

public class Game {
    private GameState _state = new VoidState();
    private final GameInput _input;
    private final GameOutput _output;

    public Game(GameInput input, GameOutput output) {
        super();

        _input = input;
        _output = output;
    }

    public void start(GameState state) {
        transition(state);

        while (true) {
            _output.display(_state.render());

            if (_state instanceof ExitState) {
                break;
            }

            GameState nextState = _state.process(_input);

            transition(nextState);
        }
    }

    private void transition(GameState state) {
        _state = state;
    }
}