package icardi.goose.game.states;

import static org.mockito.Mockito.*;

import java.util.Optional;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import icardi.goose.game.Game;
import icardi.goose.game.Player;
import icardi.goose.game.commands.ExitCommand;
import icardi.goose.game.commands.MoveCommand;
import icardi.goose.game.commands.VoidCommand;

public class PlayerTurnStateTest extends StateTestBase
{
    PlayerTurnState target = new PlayerTurnState(
        boardWithPlayers(new Player("peter"), new Player("clark"))
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
        Player peter = new Player("peter");
        Player clark = new Player("clark");
        PlayerTurnState target = new PlayerTurnState(
            boardWithPlayers(new Player("peter"), new Player("clark"))
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
        GameState returnState = target.process(gameMock(), () -> new MoveCommand("clark", 1, 2));
        assertTrue( returnState instanceof ErrorState );

        assertTrue( ((ErrorState)returnState).getError().equals("It's not your turn") );
        assertTrue( ((ErrorState)returnState).getRollbackState().equals(target) );

        GameState returnState2 = target.process(gameMock(), () -> new MoveCommand("not-existing", 1, 2));
        assertTrue( returnState2 instanceof ErrorState );
    }

    @Test
    public void shouldNotAllowToMoveWithWrongValues()
    {
        final String INVALID_DICE_VALUE = "Invalid dice value, must be between 1 and 6";

        assertTrue(
            ((ErrorState)target.process(gameMock(), () -> new MoveCommand("peter", 0, 2)))
            .getError().equals(INVALID_DICE_VALUE)
            );
        assertTrue(
            ((ErrorState)target.process(gameMock(), () -> new MoveCommand("peter", 1, -4)))
            .getError().equals(INVALID_DICE_VALUE)
            );
        assertTrue(
            ((ErrorState)target.process(gameMock(), () -> new MoveCommand("peter", 7, 2)))
            .getError().equals(INVALID_DICE_VALUE)
            );
    }

    @Test
    public void shouldMovePlayerWithMoveCommand()
    {
        PlayerMovedState expectedStep = new PlayerMovedState(
            boardWithPlayers(new Player("peter"), new Player("clark")),
            new MoveCommand("peter", 1, 2));

        GameState state = target.process(gameMock(), () -> new MoveCommand("peter", 1, 2));

        assertTrue(state.equals(expectedStep));
    }


    @Test
    public void shouldGoToExitStateWhenReceivingExitCommand()
    {
        ExitState expected = new ExitState();
        assertTrue( target.process(mock(Game.class), () -> new ExitCommand()).equals(expected));
    }

    @Test
    public void shouldGoToErrorStateForAnInvalidCommand()
    {
        GameState returnState = target.process(mock(Game.class), () -> VoidCommand.value);
        assertTrue( returnState instanceof ErrorState );
        assertTrue( ((ErrorState)returnState).getRollbackState().equals(target) );
    }

}
