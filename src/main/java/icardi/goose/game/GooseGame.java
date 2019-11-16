package icardi.goose.game;

import icardi.goose.game.boards.Board;
import icardi.goose.game.inputs.GameInput;
import icardi.goose.game.outputs.GameOutput;
import icardi.goose.game.states.ExitState;
import icardi.goose.game.states.GameState;

public class GooseGame implements Game {
    private final GameInput input;
    private final GameOutput output;
    private final Board board;

    public GooseGame(GameInput input, GameOutput output, Board board) {
        super();

        this.input = input;
        this.output = output;
        this.board = board;
    }

    public void start(GameState state) {
        while (true) {
            output.display(state.render());

            if (state instanceof ExitState) {
                break;
            }

            state = state.process(this, input);
        }
    }

    @Override
    public Board board() {
        return board;
    }
}