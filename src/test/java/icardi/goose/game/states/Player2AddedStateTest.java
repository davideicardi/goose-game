package icardi.goose.game.states;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import icardi.goose.game.Player;
import icardi.goose.game.commands.MoveCommand;
import icardi.goose.game.commands.VoidCommand;

public class Player2AddedStateTest 
{
    Player2AddedState target = new Player2AddedState(
        new Player("Clark"),
        new Player("Peter")
        );

    @Test
    public void shouldRender()
    {
        assertTrue( target.render().contains("players: Clark, Peter") );
        assertTrue( target.render().contains("Clark (0) is your turn") );
        assertTrue( target.render().contains("move by typing") );
    }

    @Test
    public void shouldGoToPlayerMovedStateWhenReceivingMoveCommand()
    {
        PlayerMovedState expected = new PlayerMovedState(
            new Player("Clark", 0),
            new Player("Peter", 0),
            new MoveCommand("Clark", 1, 2));
        assertTrue( target.processCommand(new MoveCommand("Clark", 1, 2)).equals(expected));
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
