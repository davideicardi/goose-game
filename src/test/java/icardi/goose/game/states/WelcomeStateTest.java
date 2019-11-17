package icardi.goose.game.states;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import icardi.goose.game.boards.Board;
import icardi.goose.game.commands.VoidCommand;

public class WelcomeStateTest extends StateTestBase
{
    WelcomeState target = new WelcomeState(mock(Board.class));

    @Test
    public void shouldRender()
    {
        assertTrue( target.render().contains("Welcome") );
    }

    @Test
    public void shouldGoToWaitingForPlayersState()
    {
        setupCommand(VoidCommand.value);
        assertTrue( target.process(game()) instanceof WaitingForPlayersState );
    }

    @Test
    public void shouldNotAskForNewCommands()
    {
        target.process(game());
        verify(input(), never()).waitForCommand();
    }
}
