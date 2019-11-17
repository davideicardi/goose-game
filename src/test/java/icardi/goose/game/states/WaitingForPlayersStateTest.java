package icardi.goose.game.states;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

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

        setupCommand(new AddPlayerCommand("peter"));
        GameState nextState = target.process(game());

        assertTrue(nextState.equals(expected));
    }

    @Test
    public void shouldGoToPlayerTurnStateWhenReceiving2AddPlayerCommand()
    {
        WaitingForPlayersState expected = new WaitingForPlayersState(
            boardWithPlayers(new Player("peter"), new Player("clark"))
        );

        setupCommand(new AddPlayerCommand("peter"),new AddPlayerCommand("clark"));

        GameState nextState = target
        .process(game())
        .process(game());

        assertTrue(nextState.equals(expected));
    }

    @Test
    public void shouldGoToExitStateWhenReceivingExitCommand()
    {
        ExitState expected = new ExitState();
        setupCommand(new ExitCommand());
        assertTrue( target.process(game()).equals(expected));
    }

    @Test
    public void shouldGoToErrorStateForAnInvalidCommand()
    {
        setupCommand(VoidCommand.value);
        GameState returnState = target.process(game());
        assertTrue( returnState instanceof ErrorState );
        assertTrue( ((ErrorState)returnState).getRollbackState() == target );
    }
}
