package icardi.goose.game.states;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ErrorStateTest extends StateTestBase
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
    public void shouldForwardProcessToTheRollbackState()
    {
        GameState rollbackState = mock(GameState.class);
        when(rollbackState.render()).thenReturn("Some state");
        ErrorState target = new ErrorState("My error", rollbackState);

        target.process(game());

        verify(rollbackState).process(any());
    }
}
