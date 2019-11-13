package icardi.goose.game.states;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import icardi.goose.game.Player;
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
        assertTrue( target.render().contains("Clark (0) is your turn") );
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
        GameState returnState = target.processCommand(new MoveCommand("Peter", 1, 2));
        assertTrue( returnState instanceof ErrorState );

        assertTrue( ((ErrorState)returnState).getError().equals("It's not your turn") );
        assertTrue( ((ErrorState)returnState).getRollbackState().equals(target) );
    }

    @Test
    public void shouldNotAllowToMoveWithWrongValues()
    {
        final String INVALID_DICE_VALUE = "Invalid dice value, must be between 1 and 6";

        assertTrue(
            ((ErrorState)target.processCommand(new MoveCommand("Clark", 0, 2)))
            .getError().equals(INVALID_DICE_VALUE)
            );
        assertTrue(
            ((ErrorState)target.processCommand(new MoveCommand("Clark", 1, -4)))
            .getError().equals(INVALID_DICE_VALUE)
            );
        assertTrue(
            ((ErrorState)target.processCommand(new MoveCommand("Clark", 7, 2)))
            .getError().equals(INVALID_DICE_VALUE)
            );
    }

    @Test
    public void shouldMovePlayersWithMoveCommandMultipleTimes()
    {
        PlayerMovedState expectedStep1 = new PlayerMovedState(
            new Player("Clark", 0),
            new Player("Peter", 0),
            new MoveCommand("Clark", 1, 2));
        GameState state1 = target.processCommand(new MoveCommand("Clark", 1, 2));
        assertTrue(state1.equals(expectedStep1));

        PlayerMovedState expectedStep2 = new PlayerMovedState(
            new Player("Clark", 3),
            new Player("Peter", 0),
            new MoveCommand("Peter", 5, 6));
        GameState state2 = state1.processCommand(new MoveCommand("Peter", 5, 6));
        assertTrue(state2.equals(expectedStep2));

        PlayerMovedState expectedStep3 = new PlayerMovedState(
            new Player("Clark", 3),
            new Player("Peter", 11),
            new MoveCommand("Clark", 1, 1));
        GameState state3 = state2.processCommand(new MoveCommand("Clark", 1, 1));
        assertTrue(state3.equals(expectedStep3));
    }

    @Test
    public void shouldGoToErrorStateForAnInvalidCommand()
    {
        GameState returnState = target.processCommand(VoidCommand.value);
        assertTrue( returnState instanceof ErrorState );

        PlayerTurnState expectedRollbackState = new PlayerTurnState(
            new Player("Clark", 0),
            new Player("Peter", 0),
            true);
        assertTrue( ((ErrorState)returnState).getRollbackState().equals(expectedRollbackState) );
    }
}
