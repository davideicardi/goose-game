package icardi.goose.game;

import static org.mockito.Mockito.*;

import org.junit.Test;
import org.mockito.InOrder;

import icardi.goose.game.boards.Board;
import icardi.goose.game.inputs.GameInput;
import icardi.goose.game.outputs.GameOutput;
import icardi.goose.game.states.ExitState;
import icardi.goose.game.states.GameState;

public class GooseGameTest 
{
    @Test
    public void shouldProcessStatesAndExit()
    {
        // Prepare
        GameInput input = mock(GameInput.class);
        GameOutput output = mock(GameOutput.class);

        GooseGame target = new GooseGame(input, output, mock(Board.class));

        GameState firstState = mock(GameState.class);
        GameState secondState = mock(GameState.class);
        when(firstState.process(any(), any())).thenReturn(secondState);
        when(secondState.process(any(), any())).thenReturn(new ExitState());
        
        when(firstState.render()).thenReturn("some string");
        when(secondState.render()).thenReturn("another string");

        // Act
        target.start(firstState);

        // Verify
        InOrder orderVerifier = inOrder(firstState, secondState, output);

        // It should process the first state
        orderVerifier.verify(firstState).render();
        orderVerifier.verify(output).display("some string");
        orderVerifier.verify(firstState).process(any(), any());
        // It should process the second state
        orderVerifier.verify(secondState).render();
        orderVerifier.verify(output).display("another string");
        orderVerifier.verify(secondState).process(any(), any());
        // It should exit
        orderVerifier.verify(output).display("Thank you for playing!");
    }
}
