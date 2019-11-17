package icardi.goose.game.states;

import java.util.Objects;

import icardi.goose.game.Game;
import icardi.goose.game.Player;
import icardi.goose.game.boards.Board;
import icardi.goose.game.commands.MoveCommand;
import icardi.goose.game.inputs.GameInput;

public class PlayerMovedState implements GameState {

    private final Board board;
    private final MoveCommand moveCommand;

    public PlayerMovedState(Board board, MoveCommand moveCommand) {
        super();
        this.board = board;
        this.moveCommand = moveCommand;
    }

    @Override
    public String render()
    {
        Player activePlayer = getMovedPlayer();

        return String.format(
            "%s rolls %s, %s. %s moves from %s to %s",
            activePlayer.getName(),
            moveCommand.getDice1(),
            moveCommand.getDice2(),
            activePlayer.getName(),
            board.getPosition(activePlayer),
            calcPosition()
            );
    }

    @Override
    public GameState process(Game game, GameInput input) {
        return calcNextState();
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
        PlayerMovedState other = (PlayerMovedState)o;
        // field comparison
        return Objects.equals(board, other.board)
        && Objects.equals(moveCommand, other.moveCommand);
    }

    private Player getMovedPlayer() {
        return new Player(moveCommand.getName());
    }

    private int calcPosition() {
        int oldPosition = board.getPosition(getMovedPlayer());
        int newPosition = oldPosition + moveCommand.total();
        return newPosition;
    }

    private GameState calcNextState() {
        Player movedPlayer = getMovedPlayer();

        Board newBoard = board
        .movePlayer(movedPlayer, calcPosition())
        .changeTurn(Board.nextPlayer(board));

        return new PlayerTurnState(newBoard);
    }
}