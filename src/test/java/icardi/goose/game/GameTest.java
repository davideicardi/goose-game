package icardi.goose.game;

import static org.mockito.Mockito.*;

import org.junit.Test;
import org.mockito.InOrder;

import icardi.goose.game.commands.ExitCommand;
import icardi.goose.game.commands.VoidCommand;
import icardi.goose.game.inputs.GameInput;
import icardi.goose.game.outputs.GameOutput;
import icardi.goose.game.states.GameState;

public class GameTest 
{
    @Test
    public void shouldHandleStateMachine()
    {
        GameInput input = mock(GameInput.class);
        when(input.waitForCommand())
            .thenReturn(VoidCommand.value)
            .thenReturn(new ExitCommand());
        GameOutput output = mock(GameOutput.class);

        Game target = new Game(input, output);

        GameState firstState = mock(GameState.class);
        GameState secondState = mock(GameState.class);

        when(firstState.processCommand(any())).thenReturn(secondState);
        when(firstState.render()).thenReturn("some string");
        when(secondState.render()).thenReturn("another string");
        target.start(firstState);

        InOrder orderVerifier = inOrder(firstState, secondState, output);

        // It should process the first state
        orderVerifier.verify(firstState).render();
        orderVerifier.verify(output).display("some string");
        orderVerifier.verify(firstState).processCommand(VoidCommand.value);
        // It should process the second state
        orderVerifier.verify(secondState).render();
        orderVerifier.verify(output).display("another string");
        // It should exit
        orderVerifier.verify(output).display("Thank you");
    }
}
