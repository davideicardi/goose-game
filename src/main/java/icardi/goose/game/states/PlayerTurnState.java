package icardi.goose.game.states;

import java.util.Objects;

import icardi.goose.game.Game;
import icardi.goose.game.Player;
import icardi.goose.game.boards.Board;
import icardi.goose.game.commands.GameCommand;
import icardi.goose.game.commands.MoveCommand;

public class PlayerTurnState implements GameState {

    private final Board board;

    public PlayerTurnState(Board board) {
        super();
        this.board = board;
    }

    @Override
    public String render() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("---------------\n");

        board.getPlayers().forEach(player -> {
            boolean isYourTurn = Board.isPlayerTurn(board, player);
            int playerPosition = board.getPosition(player);

            stringBuilder.append(renderPlayer(player, playerPosition, isYourTurn));
            stringBuilder.append("\n");
        });

        stringBuilder.append("---------------\n");
        stringBuilder.append(" move by typing `move {playerName}`");

        return stringBuilder.toString();
    }

    @Override
    public GameState process(Game game) {
        GameCommand command = game.input().waitForCommand();
        
        final String INVALID_DICE_VALUE = "Invalid dice value, must be between 1 and 6";
        final String IT_IS_NOT_YOUR_TURN = "It's not your turn";

        if (command instanceof MoveCommand) {
            MoveCommand apc = (MoveCommand)command;

            Player movedPlayer = new Player(apc.getName());
            if (!Board.isPlayerTurn(board, movedPlayer)) {
                return new ErrorState(IT_IS_NOT_YOUR_TURN, this); 
            }

            if (apc.getDice1() < 1 || apc.getDice1() > 6 || apc.getDice2() < 1 || apc.getDice2() > 6) {
                return new ErrorState(INVALID_DICE_VALUE, this); 
            }

            return new PlayerMovedState(board, apc);
        }

        return GameState.processCmd(command, this);
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
        PlayerTurnState other = (PlayerTurnState)o;
        // field comparison
        return Objects.equals(board, other.board);
    }

    private String renderPlayer(Player player, int position, boolean isYourTurn) {
        final String IS_YOUR_TURN = "is your turn 🎲";

        return String.format(
            "%s%s (%s) %s",
            " ".repeat(position),
            player.getName(),
            position,
            isYourTurn ? IS_YOUR_TURN : ""
            );
    }

}