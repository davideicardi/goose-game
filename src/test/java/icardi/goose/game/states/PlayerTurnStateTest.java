package icardi.goose.game.states;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import icardi.goose.game.Dice;
import icardi.goose.game.Player;
import icardi.goose.game.boards.Board;
import icardi.goose.game.boards.TurnResult;
import icardi.goose.game.boxes.BlankBox;
import icardi.goose.game.boxes.StartBox;
import icardi.goose.game.commands.ExitCommand;
import icardi.goose.game.commands.MoveCommand;
import icardi.goose.game.commands.RollsAndMoveCommand;
import icardi.goose.game.commands.VoidCommand;
import icardi.goose.game.exceptions.InvalidDiceException;
import icardi.goose.game.moves.Move;
import icardi.goose.game.moves.RollsMove;

public class PlayerTurnStateTest extends StateTestBase
{
    private Player peter = new Player("peter");
    private Player clark = new Player("clark");

    PlayerTurnState target = new PlayerTurnState(
        boardWithPlayers(peter, clark)
        );

    @Test
    public void shouldRender()
    {
        String rendered = target.render();

        assertTrue( rendered, rendered.contains("peter (1) is your turn") );
        assertTrue( rendered, rendered.contains("move by typing") );
    }

    @Test
    public void shouldRenderPositionAndTurn()
    {
        PlayerTurnState target = new PlayerTurnState(
            boardWithPlayers(peter, clark)
            .movePlayer(peter, 6)
            .movePlayer(clark, 8)
            .changeTurn(Optional.of(clark))
            );

        String rendered = target.render();
        assertTrue( rendered, rendered.contains("peter (6)") );
        assertTrue( rendered, rendered.contains("clark (8) is your turn") );
    }

    @Test
    public void shouldNotAllowToMoveTheWrongPlayer()
    {
        setupCommand(new MoveCommand("clark", 1, 2));
        GameState returnState = target.process(game());
        assertTrue( returnState instanceof ErrorState );

        assertTrue( ((ErrorState)returnState).getError().equals("clark: it's not your turn") );
        assertTrue( ((ErrorState)returnState).getRollbackState().equals(target) );

        setupCommand(new MoveCommand("not-existing", 1, 2));
        GameState returnState2 = target.process(game());
        assertTrue( returnState2 instanceof ErrorState );
    }

    @Test
    public void shouldNotAllowToMoveWithWrongValues()
    {
        final String INVALID_DICE_VALUE = "Invalid dice value, must be between 1 and 6";

        setupCommand(new MoveCommand("peter", 0, 2));
        assertTrue(
            ((ErrorState)target.process(game()))
            .getError().equals(INVALID_DICE_VALUE)
            );

        setupCommand(new MoveCommand("peter", 1, -4));
        assertTrue(
            ((ErrorState)target.process(game()))
            .getError().equals(INVALID_DICE_VALUE)
            );

        setupCommand(new MoveCommand("peter", 7, 2));
        assertTrue(
            ((ErrorState)target.process(game()))
            .getError().equals(INVALID_DICE_VALUE)
            );
    }

    @Test
    public void shouldMovePlayerWithMoveCommand() throws InvalidDiceException
    {
        Dice dice1 = Dice.fromValue(1);
        Dice dice2 = Dice.fromValue(2);
        List<Dice> dices = Arrays.asList(dice1, dice2);
        Board expectedBoard = boardWithPlayers(peter, clark)
        .movePlayer(peter, 4)
        .changeTurn(Optional.of(clark));
        List<Move> expectedMoves = Arrays.asList(new RollsMove(peter, dices, new StartBox(1), new BlankBox(4)));
        PlayerMovedState expectedStep = new PlayerMovedState(
            new TurnResult(expectedBoard, expectedMoves)
        );

        setupCommand(new MoveCommand("peter", dice1.getValue(), dice2.getValue()));
        GameState state = target.process(game());

        assertTrue(state.equals(expectedStep));
    }

    @Test
    public void shouldMovePlayerWithRollsAndMoveCommand()
    {
        setupCommand(new RollsAndMoveCommand("peter"));
        GameState state = target.process(game());

        assertTrue(state instanceof PlayerMovedState);
        PlayerMovedState movedState = (PlayerMovedState)state;
        Board board = movedState.getTurnResult().board;
        assertEquals(clark, board.playerTurn().get());
        assertTrue(board.getPlayerBox(peter).getPosition() > 1);
    }

    @Test
    public void shouldGoToExitStateWhenReceivingExitCommand()
    {
        ExitState expected = new ExitState();
        setupCommand(new ExitCommand());
        assertTrue( target.process(game()).equals(expected) );
    }

    @Test
    public void shouldGoToErrorStateForAnInvalidCommand()
    {
        setupCommand(VoidCommand.value);
        GameState returnState = target.process(game());
        assertTrue( returnState instanceof ErrorState );
        assertTrue( ((ErrorState)returnState).getRollbackState().equals(target) );
    }

}
