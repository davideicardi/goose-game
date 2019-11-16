package icardi.goose.game.states;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import icardi.goose.game.Game;
import icardi.goose.game.commands.VoidCommand;

public class ErrorStateTest 
{
    @Test
    public void shouldRender()
    {
        GameState rollbackState = mock(GameState.class);
        when(rollbackState.render()).thenReturn("Some state");
        ErrorState target = new ErrorState("My error", rollbackState);
    
        assertTrue( target.render().contains("My error") );
        assertTrue( target.render().contains("Some state") );
    }

    @Test
    public void shouldForwardCommandToTheRollbackState()
    {
        GameState rollbackState = mock(GameState.class);
        when(rollbackState.render()).thenReturn("Some state");
        ErrorState target = new ErrorState("My error", rollbackState);

        target.process(mock(Game.class), () -> VoidCommand.value);

        verify(rollbackState).process(any(), any());
    }
}
