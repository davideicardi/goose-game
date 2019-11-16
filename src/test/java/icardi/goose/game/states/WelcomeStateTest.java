package icardi.goose.game.states;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import icardi.goose.game.commands.VoidCommand;
import icardi.goose.game.inputs.GameInput;

public class WelcomeStateTest 
{
    WelcomeState target = new WelcomeState();

    @Test
    public void shouldRender()
    {
        assertTrue( target.render().contains("Welcome") );
    }

    @Test
    public void shouldGoToNoPlayerState()
    {
        assertTrue( target.process(() -> VoidCommand.value) instanceof NoPlayerState );
    }

    @Test
    public void shouldNotAskForNewCommands()
    {
        GameInput mockInput = mock(GameInput.class);
        target.process(mockInput);
        verify(mockInput, never()).waitForCommand();
    }
}
