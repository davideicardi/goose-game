package icardi.goose.game.boards;

import java.util.List;
import java.util.Objects;

import icardi.goose.game.moves.Move;

public class TurnResult {
    public final Board board;
    public final List<Move> moves;

    public TurnResult(Board board, List<Move> moves) {
        super();

        this.board = board;
        this.moves = moves;
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
        TurnResult other = (TurnResult)o;
        // field comparison
        return Objects.equals(board, other.board)
        && Objects.equals(moves, other.moves);
    }
}