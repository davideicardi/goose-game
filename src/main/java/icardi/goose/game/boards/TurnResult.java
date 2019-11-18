package icardi.goose.game.boards;

import java.util.List;

import icardi.goose.game.moves.Move;

public class TurnResult {
    public final Board board;
    public final List<Move> moves;

    public TurnResult(Board board, List<Move> moves) {
        super();

        this.board = board;
        this.moves = moves;
    }
}