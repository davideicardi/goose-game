package icardi.goose.game.states;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import icardi.goose.game.Game;
import icardi.goose.game.boards.Board;
import icardi.goose.game.commands.VoidCommand;
import icardi.goose.game.inputs.GameInput;

public class WelcomeStateTest 
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
        assertTrue( target.process(mock(Game.class), () -> VoidCommand.value) instanceof WaitingForPlayersState );
    }

    @Test
    public void shouldNotAskForNewCommands()
    {
        GameInput mockInput = mock(GameInput.class);
        target.process(mock(Game.class), mockInput);
        verify(mockInput, never()).waitForCommand();
    }
}
