package icardi.goose.game.states;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import icardi.goose.game.commands.StartCommand;
import icardi.goose.game.commands.VoidCommand;

public class WelcomeStateTest 
{
    WelcomeState target = new WelcomeState();

    @Test
    public void shouldRender()
    {
        assertTrue( target.render().contains("Welcome") );
    }

    @Test
    public void shouldGoToNoPlayerStateWhenReceivingStart()
    {
        assertTrue( target.processCommand(new StartCommand()) instanceof NoPlayerState );
    }

    @Test
    public void shouldGoToErrorStateForAnInvalidCommand()
    {
        GameState returnState = target.processCommand(VoidCommand.value);
        assertTrue( returnState instanceof ErrorState );
        assertTrue( ((ErrorState)returnState).getRollbackState() == target );
    }
}
