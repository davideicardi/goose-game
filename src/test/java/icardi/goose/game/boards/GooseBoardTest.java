package icardi.goose.game.boards;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import icardi.goose.game.Dice;
import icardi.goose.game.Player;
import icardi.goose.game.exceptions.DuplicatedPlayerException;
import icardi.goose.game.exceptions.InvalidDiceException;
import icardi.goose.game.exceptions.NotYourTurnException;
import icardi.goose.game.moves.BounceMove;
import icardi.goose.game.moves.GooseMove;
import icardi.goose.game.moves.JumpMove;
import icardi.goose.game.moves.PrankMove;
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
        .turn(duck, Dice.fromValues(1, 1));
    }

    @Test()
    public void shouldProcessTurn()
    throws DuplicatedPlayerException, InvalidDiceException, NotYourTurnException
    {
        TurnResult result = new GooseBoard()
        .addPlayer(donald)
        .addPlayer(duck)
        .turn(donald, Dice.fromValues(1, 1));
        
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
        .turn(donald, Dice.fromValues(1,1)).board
        .turn(duck, Dice.fromValues(2,1)).board
        .turn(donald, Dice.fromValues(3,2)).board
        .turn(duck, Dice.fromValues(2,1));
        
        assertEquals(8, result.board.getPlayerBox(donald).getPosition() );
        assertEquals(7, result.board.getPlayerBox(duck).getPosition() );
        assertEquals(donald, result.board.playerTurn().get() );
    }

    @Test()
    public void shouldWinsOn63()
    throws DuplicatedPlayerException, InvalidDiceException, NotYourTurnException
    {
        TurnResult result = new GooseBoard()
        .addPlayer(donald)
        .addPlayer(duck)
        .movePlayer(donald, 61)
        .turn(donald, Dice.fromValues(1,1));
        
        assertTrue(Board.winner(result.board).get().equals(donald));
        assertEquals(1, result.moves.size());
        assertTrue(result.moves.get(0) instanceof RollsMove);
    }

    @Test()
    public void shouldNotWinsOnOtherBoxes()
    throws DuplicatedPlayerException, InvalidDiceException, NotYourTurnException
    {
        Board result = new GooseBoard()
        .addPlayer(donald)
        .addPlayer(duck)
        .movePlayer(donald, 55)
        .turn(donald, Dice.fromValues(1,1)).board;
        
        assertTrue(!Board.winner(result).isPresent());
    }

    @Test()
    public void shouldBounceAtEnd()
    throws DuplicatedPlayerException, InvalidDiceException, NotYourTurnException
    {
        TurnResult result = new GooseBoard()
        .addPlayer(donald)
        .addPlayer(duck)
        .movePlayer(donald, 60)
        .turn(donald, Dice.fromValues(2,2));
        
        assertEquals(62, result.board.getPlayerBox(donald).getPosition() );
        assertTrue(!Board.winner(result.board).isPresent());
        assertEquals(2, result.moves.size());
        assertTrue(result.moves.get(0) instanceof RollsMove);
        assertTrue(result.moves.get(1) instanceof BounceMove);
    }

    @Test()
    public void shouldJumpTo12IfLandingOnTheBridge()
    throws DuplicatedPlayerException, InvalidDiceException, NotYourTurnException
    {
        TurnResult result = new GooseBoard()
        .addPlayer(donald)
        .addPlayer(duck)
        .movePlayer(donald, 4)
        .turn(donald, Dice.fromValues(1,1));
        
        assertEquals(12, result.board.getPlayerBox(donald).getPosition() );
        assertEquals(2, result.moves.size());
        assertTrue(result.moves.get(0) instanceof RollsMove);
        assertTrue(result.moves.get(1) instanceof JumpMove);
    }

    @Test()
    public void shouldMoveAgainIfLandingOnTheGoose()
    throws DuplicatedPlayerException, InvalidDiceException, NotYourTurnException
    {
        TurnResult result = new GooseBoard()
        .addPlayer(donald)
        .addPlayer(duck)
        .movePlayer(donald, 3)
        .turn(donald, Dice.fromValues(1,1));
        
        assertEquals(7, result.board.getPlayerBox(donald).getPosition() );
        assertEquals(2, result.moves.size());
        assertTrue(result.moves.get(0) instanceof RollsMove);
        assertTrue(result.moves.get(1) instanceof GooseMove);
    }

    @Test()
    public void shouldMoveAgainIfLandingOnTheGooseAndPerform2Jumps()
    throws DuplicatedPlayerException, InvalidDiceException, NotYourTurnException
    {
        TurnResult result = new GooseBoard()
        .addPlayer(donald)
        .addPlayer(duck)
        .movePlayer(donald, 10)
        .turn(donald, Dice.fromValues(2,2));
        
        assertEquals(22, result.board.getPlayerBox(donald).getPosition() );
        assertEquals(3, result.moves.size());
        assertTrue(result.moves.get(0) instanceof RollsMove);
        assertTrue(result.moves.get(1) instanceof GooseMove);
        assertTrue(result.moves.get(2) instanceof GooseMove);
    }

    @Test()
    public void shouldMoveOthersPlayersBackIfInTheSameDestination()
    throws DuplicatedPlayerException, InvalidDiceException, NotYourTurnException
    {
        TurnResult result = new GooseBoard()
        .addPlayer(donald)
        .addPlayer(duck)
        .movePlayer(donald, 15)
        .movePlayer(duck, 17)
        .turn(donald, Dice.fromValues(1,1));
        
        assertEquals(17, result.board.getPlayerBox(donald).getPosition() );
        assertEquals(15, result.board.getPlayerBox(duck).getPosition() );
        assertEquals(2, result.moves.size());
        assertTrue(result.moves.get(0) instanceof RollsMove);
        assertTrue(result.moves.get(1) instanceof PrankMove);
    }
}
