package icardi.goose.game.states;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import icardi.goose.game.Player;
import icardi.goose.game.commands.AddPlayerCommand;
import icardi.goose.game.commands.ExitCommand;
import icardi.goose.game.commands.VoidCommand;

public class Player1AddedStateTest 
{
    Player1AddedState target = new Player1AddedState(new Player("Clark"));

    @Test
    public void shouldRender()
    {
        assertTrue( target.render().contains("players: Clark") );
        assertTrue( target.render().contains("add player") );
    }

    @Test
    public void shouldGoToPlayer2AddedStateWhenReceivingAddPlayerCommand()
    {
        Player2AddedState expected = new Player2AddedState(
            new Player("Clark"),
            new Player("Peter"));
        assertTrue( target.process(() -> new AddPlayerCommand("Peter")).equals(expected));
    }

    @Test
    public void shouldGoToExitStateWhenReceivingExitCommand()
    {
        ExitState expected = new ExitState();
        assertTrue( target.process(() -> new ExitCommand()).equals(expected));
    }

    @Test
    public void shouldGoToErrorStateWhenReceivingADuplicatedPlayer()
    {
        ErrorState expected = new ErrorState("Clark: already existing player", target);
        GameState result = target.process(() -> new AddPlayerCommand("Clark"));
        assertTrue( result.equals(expected));
    }

    @Test
    public void shouldGoToErrorStateForAnInvalidCommand()
    {
        GameState returnState = target.process(() -> VoidCommand.value);
        assertTrue( returnState instanceof ErrorState );
        assertTrue( ((ErrorState)returnState).getRollbackState() == target );
    }
}
