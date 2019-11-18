package icardi.goose.game.states;

import java.util.Objects;

import icardi.goose.game.Dice;
import icardi.goose.game.Game;
import icardi.goose.game.Player;
import icardi.goose.game.boards.Board;
import icardi.goose.game.boards.TurnResult;
import icardi.goose.game.commands.GameCommand;
import icardi.goose.game.commands.MoveCommand;
import icardi.goose.game.commands.RollsAndMoveCommand;
import icardi.goose.game.exceptions.InvalidDiceException;
import icardi.goose.game.exceptions.NotYourTurnException;
import icardi.goose.game.moves.Move;

public class PlayerTurnState implements GameState {

    private final Board board;

    public PlayerTurnState(Board board) {
        super();
        this.board = board;
    }

    public Board getBoard() {
        return this.board;
    }

    @Override
    public String render() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("---------------\n");

        board.getPlayers().forEach(player -> {
            boolean isYourTurn = Board.isPlayerTurn(board, player);
            int playerPosition = board.getPlayerBox(player).getPosition();

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
        
        try {
            if (command instanceof MoveCommand) {
                MoveCommand apc = (MoveCommand)command;
    
                Player movedPlayer = new Player(apc.getName());

                Dice[] dices = new Dice[] { Dice.fromValue(apc.getDice1()), Dice.fromValue(apc.getDice2()) };
                
                return doTurn(game, movedPlayer, dices);
            } if (command instanceof RollsAndMoveCommand) {
                RollsAndMoveCommand apc = (RollsAndMoveCommand)command;
    
                Player movedPlayer = new Player(apc.getName());

                Dice[] dices = new Dice[] { Dice.roll(), Dice.roll() };
                
                return doTurn(game, movedPlayer, dices);
            }
    
            return GameState.processDefault(command, this);
        } catch (InvalidDiceException ex) {
            return new ErrorState(ex.getMessage(), this);
        } catch (NotYourTurnException ex) {
            return new ErrorState(ex.getMessage(), this);
        }
    }

    private GameState doTurn(Game game, Player movedPlayer, Dice[] dices) throws NotYourTurnException {
        TurnResult result = board.turn(movedPlayer, dices);

        for (Move move : result.moves) {
            game.output().display(move.toString());
        }
   
        return new PlayerTurnState(result.board);
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