package icardi.goose.game.states;

import icardi.goose.game.Game;
import icardi.goose.game.boards.Board;
import icardi.goose.game.inputs.GameInput;

public class WelcomeState implements GameState {

    private final Board board;

    public WelcomeState(Board board) {
        super();
        this.board = board;
    }

    @Override
    public String render() {
        return 
        "🦆  Welcome to goose-game 🦆\n" +
         " type `exit` to close game in any moment.";
    }

    @Override
    public GameState process(Game game, GameInput input) {
        return new WaitingForPlayersState(board);
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