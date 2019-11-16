package icardi.goose.game.states;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import icardi.goose.game.Game;
import icardi.goose.game.Player;
import icardi.goose.game.commands.AddPlayerCommand;
import icardi.goose.game.commands.ExitCommand;
import icardi.goose.game.commands.VoidCommand;

public class NoPlayerStateTest 
{
    NoPlayerState target = new NoPlayerState();

    @Test
    public void shouldRender()
    {
        assertTrue( target.render().contains("add player") );
    }

    @Test
    public void shouldGoToNoPlayerStateWhenReceivingAddPlayerCommand()
    {
        Player1AddedState expected = new Player1AddedState(new Player("peter"));
        assertTrue( target.process(mock(Game.class), () -> new AddPlayerCommand("peter")).equals(expected));
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
        assertTrue( ((ErrorState)returnState).getRollbackState() == target );
    }
}
