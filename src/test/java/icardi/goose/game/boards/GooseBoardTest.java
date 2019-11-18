package icardi.goose.game.boards;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import icardi.goose.game.Dice;
import icardi.goose.game.Player;
import icardi.goose.game.exceptions.DuplicatedPlayerException;
import icardi.goose.game.exceptions.InvalidDiceException;
import icardi.goose.game.exceptions.NotYourTurnException;
import icardi.goose.game.moves.RollsMove;

public class GooseBoardTest 
{
    private Player donald = new Player("donald");
    private Player duck = new Player("duck");

    @Test()
    public void shouldAllowAddPlayers() throws DuplicatedPlayerException
    {
        Board target = new GooseBoard()
        .addPlayer(donald)
        .addPlayer(duck);

        assertEquals(2, target.getPlayers().size());
    }

    @Test(expected = DuplicatedPlayerException.class)
    public void shouldNotAllowDuplicatedPlayer() throws DuplicatedPlayerException
    {
        new GooseBoard()
        .addPlayer(new Player("donald"))
        .addPlayer(new Player("donald"));
    }

    @Test(expected = NotYourTurnException.class)
    public void shouldNotProcessIfIsNotYourTurn()
    throws DuplicatedPlayerException, InvalidDiceException, NotYourTurnException
    {
        new GooseBoard()
        .addPlayer(donald)
        .addPlayer(duck)
        .turn(duck, new Dice[] { Dice.fromValue(1), Dice.fromValue(1) });
    }

    @Test()
    public void shouldProcessTurn()
    throws DuplicatedPlayerException, InvalidDiceException, NotYourTurnException
    {
        TurnResult result = new GooseBoard()
        .addPlayer(donald)
        .addPlayer(duck)
        .turn(donald, new Dice[] { Dice.fromValue(1), Dice.fromValue(1) });
        
        assertEquals(3, result.board.getPlayerBox(donald).getPosition() );
        assertEquals(1, result.board.getPlayerBox(duck).getPosition() );
        assertEquals(duck, result.board.playerTurn().get() );
        assertEquals(1, result.moves.size());
        assertTrue(result.moves.get(0) instanceof RollsMove);
    }

    @Test()
    public void shouldProcessMultipleTurns()
    throws DuplicatedPlayerException, InvalidDiceException, NotYourTurnException
    {
        TurnResult result = new GooseBoard()
        .addPlayer(donald)
        .addPlayer(duck)
        .turn(donald, new Dice[] { Dice.fromValue(1), Dice.fromValue(1) }).board
        .turn(duck, new Dice[] { Dice.fromValue(2), Dice.fromValue(1) }).board
        .turn(donald, new Dice[] { Dice.fromValue(1), Dice.fromValue(1) }).board
        .turn(duck, new Dice[] { Dice.fromValue(2), Dice.fromValue(1) });
        
        assertEquals(5, result.board.getPlayerBox(donald).getPosition() );
        assertEquals(7, result.board.getPlayerBox(duck).getPosition() );
        assertEquals(donald, result.board.playerTurn().get() );
    }
}
