package icardi.goose.game.states;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import icardi.goose.game.Game;
import icardi.goose.game.Player;
import icardi.goose.game.commands.AddPlayerCommand;
import icardi.goose.game.commands.ExitCommand;
import icardi.goose.game.commands.VoidCommand;

public class WaitingForPlayersStateTest extends StateTestBase
{
    WaitingForPlayersState target = new WaitingForPlayersState(emptyBoard());

    @Test
    public void shouldRender()
    {
        assertTrue( target.render().contains("add player") );
    }

    @Test
    public void shouldGoToWaitingForPlayersStateWhenReceivingAddPlayerCommand()
    {
        WaitingForPlayersState expected = new WaitingForPlayersState(
            boardWithPlayers(new Player("peter"))
        );

        GameState nextState = target.process(gameMock(), () -> new AddPlayerCommand("peter"));

        assertTrue(nextState.equals(expected));
    }

    @Test
    public void shouldGoToPlayerTurnStateWhenReceiving2AddPlayerCommand()
    {
        WaitingForPlayersState expected = new WaitingForPlayersState(
            boardWithPlayers(new Player("peter"), new Player("clark"))
        );

        GameState nextState = target
        .process(gameMock(), () -> new AddPlayerCommand("peter"))
        .process(gameMock(), () -> new AddPlayerCommand("clark"));

        assertTrue(nextState.equals(expected));
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
