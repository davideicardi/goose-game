package icardi.goose.game.states;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import icardi.goose.game.Game;
import icardi.goose.game.Player;
import icardi.goose.game.commands.ExitCommand;
import icardi.goose.game.commands.MoveCommand;
import icardi.goose.game.commands.VoidCommand;

public class PlayerTurnStateTest 
{
    PlayerTurnState target = new PlayerTurnState(
        new Player("Clark"),
        new Player("Peter"),
        true
        );

    @Test
    public void shouldRender()
    {
        assertTrue( target.render().contains("Clark (1) is your turn") );
        assertTrue( target.render().contains("move by typing") );
    }

    @Test
    public void shouldRenderPosition()
    {
        PlayerTurnState target = new PlayerTurnState(
            new Player("Clark", 6),
            new Player("Peter", 7),
            false
            );

        assertTrue( target.render().contains("Clark (6)") );
        assertTrue( target.render().contains("Peter (7) is your turn") );
    }

    @Test
    public void shouldNotAllowToMoveTheWrongPlayer()
    {
        GameState returnState = target.process(mock(Game.class), () -> new MoveCommand("Peter", 1, 2));
        assertTrue( returnState instanceof ErrorState );

        assertTrue( ((ErrorState)returnState).getError().equals("It's not your turn") );
        assertTrue( ((ErrorState)returnState).getRollbackState().equals(target) );
    }

    @Test
    public void shouldNotAllowToMoveWithWrongValues()
    {
        final String INVALID_DICE_VALUE = "Invalid dice value, must be between 1 and 6";

        assertTrue(
            ((ErrorState)target.process(mock(Game.class), () -> new MoveCommand("Clark", 0, 2)))
            .getError().equals(INVALID_DICE_VALUE)
            );
        assertTrue(
            ((ErrorState)target.process(mock(Game.class), () -> new MoveCommand("Clark", 1, -4)))
            .getError().equals(INVALID_DICE_VALUE)
            );
        assertTrue(
            ((ErrorState)target.process(mock(Game.class), () -> new MoveCommand("Clark", 7, 2)))
            .getError().equals(INVALID_DICE_VALUE)
            );
    }

    @Test
    public void shouldMovePlayer1WithMoveCommand()
    {
        PlayerTurnState currentTarget = new PlayerTurnState(
            new Player("Clark"),
            new Player("Peter"),
            true
            );
    
        PlayerMovedState expectedStep = new PlayerMovedState(
            new Player("Clark"),
            new Player("Peter"),
            new MoveCommand("Clark", 1, 2));

        GameState state = currentTarget.process(mock(Game.class), () -> new MoveCommand("Clark", 1, 2));

        assertTrue(state.equals(expectedStep));
    }

    @Test
    public void shouldMovePlayer2WithMoveCommand()
    {
        PlayerTurnState currentTarget = new PlayerTurnState(
            new Player("Clark", 5),
            new Player("Peter", 6),
            false
            );

        PlayerMovedState expectedStep = new PlayerMovedState(
            new Player("Clark", 5),
            new Player("Peter", 6),
            new MoveCommand("Peter", 1, 2));

        GameState state = currentTarget.process(mock(Game.class), () -> new MoveCommand("Peter", 1, 2));

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

    @Test
    public void shouldGoToErrorStateIfMoveCommandIsNotForTheActivePlayer()
    {
        PlayerTurnState currentTarget = new PlayerTurnState(
            new Player("Clark", 5),
            new Player("Peter", 6),
            true
            );

        GameState returnState = currentTarget.process(mock(Game.class), () -> new MoveCommand("Peter", 1, 2));
        assertTrue( returnState instanceof ErrorState );
        assertTrue( ((ErrorState)returnState).getError().equals("It's not your turn") );
        assertTrue( ((ErrorState)returnState).getRollbackState().equals(currentTarget) );
    }

    @Test
    public void shouldGoToErrorStateIfDiceValuesAreNotValid()
    {
        PlayerTurnState currentTarget = new PlayerTurnState(
            new Player("Clark", 5),
            new Player("Peter", 6),
            true
            );

        GameState returnState = currentTarget.process(mock(Game.class), () -> new MoveCommand("Clark", 8, 2));
        assertTrue( returnState instanceof ErrorState );
        assertTrue( ((ErrorState)returnState).getError().equals("Invalid dice value, must be between 1 and 6") );
        assertTrue( ((ErrorState)returnState).getRollbackState().equals(currentTarget) );
    }
}
