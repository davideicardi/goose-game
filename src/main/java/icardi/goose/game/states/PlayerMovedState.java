package icardi.goose.game.states;

import java.util.Objects;
import java.util.Optional;

import icardi.goose.game.Game;
import icardi.goose.game.Player;
import icardi.goose.game.boards.Board;
import icardi.goose.game.boards.TurnResult;
import icardi.goose.game.moves.Move;

public class PlayerMovedState implements GameState {

    private final TurnResult turnResult;

    public PlayerMovedState(final TurnResult turnResult) {
        super();
        this.turnResult = turnResult;
    }

    public TurnResult getTurnResult() {
        return turnResult;
    }

    @Override
    public String render() {
        final StringBuilder builder = new StringBuilder();
        for (final Move move : turnResult.moves) {
            builder.append(move.toString());
            builder.append(" ");
        }

        return builder.toString();
    }

    @Override
    public GameState process(final Game game) {
        Optional<Player> winner = Board.winner(turnResult.board);
        return winner
        .map(p -> (GameState)new PlayerWinsState(p))
        .orElse(new PlayerTurnState(turnResult.board));
    }

    @Override
    public boolean equals(final Object o) {
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
        final PlayerMovedState other = (PlayerMovedState) o;
        // field comparison
        return Objects.equals(turnResult, other.turnResult);
    }
}