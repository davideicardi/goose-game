package icardi.goose.game.states;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.mockito.stubbing.OngoingStubbing;

import icardi.goose.game.Game;
import icardi.goose.game.Player;
import icardi.goose.game.boards.Board;
import icardi.goose.game.boards.GooseBoard;
import icardi.goose.game.commands.GameCommand;
import icardi.goose.game.commands.VoidCommand;
import icardi.goose.game.inputs.GameInput;
import icardi.goose.game.outputs.GameOutput;

public abstract class StateTestBase {

    private GameInput input = mock(GameInput.class);
    private GameOutput output = mock(GameOutput.class);
    private Game game = mock(Game.class);

    @Before
    public void beforeEachTest() 
    {
        reset(input);
        reset(output);
        reset(game);

        when(game.input()).thenReturn(input);
        when(game.output()).thenReturn(output);
        when(input.waitForCommand()).thenReturn(VoidCommand.value);
    }

    public GameInput input() {
        return input;
    }

    public GameOutput output() {
        return output;
    }

    public Game game() {
        return game;
    }

    public void setupCommand(GameCommand ...commands) {
        OngoingStubbing<GameCommand> stubbing = when(input.waitForCommand());

        for (GameCommand cmd : commands) {
            stubbing = stubbing.thenReturn(cmd);
        }
    }

    public Board emptyBoard() {
        return new GooseBoard();
    }

    public Board boardWithPlayers(Player ...players) {
        Board board = new GooseBoard();

        for (Player player : players) {
            board = board.addPlayer(player);
        }

        return board;
    }
}