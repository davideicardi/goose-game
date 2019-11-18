package icardi.goose.game.states;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import icardi.goose.game.Dice;
import icardi.goose.game.Player;
import icardi.goose.game.boards.Board;
import icardi.goose.game.boards.TurnResult;
import icardi.goose.game.boxes.BlankBox;
import icardi.goose.game.boxes.FinishBox;
import icardi.goose.game.boxes.StartBox;
import icardi.goose.game.exceptions.InvalidDiceException;
import icardi.goose.game.moves.Move;
import icardi.goose.game.moves.RollsMove;

public class PlayerMovedStateTest extends StateTestBase
{
    private Player peter = new Player("peter");
    private Player clark = new Player("clark");

    private TurnResult createResult(Move ...moveArgs) throws InvalidDiceException {
        Board board = boardWithPlayers(peter, clark)
        .movePlayer(peter, 4)
        .changeTurn(Optional.of(clark));
        List<Move> moves = Arrays.asList(moveArgs);
        return new TurnResult(board, moves);
    }

    @Test
    public void shouldRenderMove() throws InvalidDiceException
    {
        Dice dice1 = Dice.fromValue(1);
        Dice dice2 = Dice.fromValue(2);
        List<Dice> dices = Arrays.asList(dice1, dice2);
        PlayerMovedState target = new PlayerMovedState(
            createResult(new RollsMove(peter, dices, new StartBox(1), new BlankBox(4)))
            );
        
        String rendered = target.render();

        assertTrue( rendered, rendered.contains("peter rolls 1, 2. peter moves from Start to 4") );
    }

    @Test
    public void shouldRenderMoveToTheEnd() throws InvalidDiceException
    {
        Dice dice1 = Dice.fromValue(1);
        Dice dice2 = Dice.fromValue(2);
        List<Dice> dices = Arrays.asList(dice1, dice2);
        PlayerMovedState target = new PlayerMovedState(
            createResult(new RollsMove(peter, dices, new BlankBox(60), new FinishBox(63)))
            );
        
        String rendered = target.render();

        assertTrue( rendered, rendered.contains("peter rolls 1, 2. peter moves from 60 to 63") );
    }

    @Test
    public void shouldGoToPlayerTurnStateIfThereIsNoWinner() throws InvalidDiceException
    {
        Dice dice1 = Dice.fromValue(1);
        Dice dice2 = Dice.fromValue(2);
        List<Dice> dices = Arrays.asList(dice1, dice2);
        PlayerMovedState target = new PlayerMovedState(
            createResult(new RollsMove(peter, dices, new StartBox(1), new BlankBox(4)))
            );

        GameState returnState = target.process(game());
        assertTrue( returnState instanceof PlayerTurnState );
    }

    @Test
    public void shouldGoToPlayerWinsStateIfThereIsAWinner() throws InvalidDiceException
    {
        Board board = boardWithPlayers(peter, clark)
        .movePlayer(peter, 63);
        List<Move> moves = Arrays.asList();
        TurnResult turnResult = new TurnResult(board, moves);
        
        PlayerMovedState target = new PlayerMovedState(
            turnResult
            );

        GameState returnState = target.process(game());
        assertTrue( returnState instanceof PlayerWinsState );
    }

}
