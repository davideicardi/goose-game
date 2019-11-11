package icardi.goose.game.states;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import icardi.goose.game.Player;
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

    // @Test
    // public void shouldGoToNoPlayerStateWhenReceivingAddPlayerCommand()
    // {
    //     Player1AddedState expected = new Player1AddedState(new Player("peter"));
    //     assertTrue( target.processCommand(new AddPlayerCommand("peter")).equals(expected));
    // }

    @Test
    public void shouldGoToErrorStateForAnInvalidCommand()
    {
        GameState returnState = target.processCommand(VoidCommand.value);
        assertTrue( returnState instanceof ErrorState );
        assertTrue( ((ErrorState)returnState).getRollbackState() == target );
    }
}
