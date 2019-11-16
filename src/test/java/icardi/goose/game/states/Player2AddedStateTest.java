package icardi.goose.game.states;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import icardi.goose.game.Player;
import icardi.goose.game.commands.VoidCommand;
import icardi.goose.game.inputs.GameInput;

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
    }

    @Test
    public void shouldGoToPlayerTurnState()
    {
        PlayerTurnState expected = new PlayerTurnState(
            new Player("Clark"),
            new Player("Peter"),
            true);
        assertTrue( target.process(() -> VoidCommand.value).equals(expected));
    }

    @Test
    public void shouldNotAskForNewCommands()
    {
        GameInput mockInput = mock(GameInput.class);
        target.process(mockInput);
        verify(mockInput, never()).waitForCommand();
    }
}
