package icardi.goose.game.states;

import static org.mockito.Mockito.*;

import icardi.goose.game.Game;
import icardi.goose.game.Player;
import icardi.goose.game.boards.Board;
import icardi.goose.game.boards.GooseBoard;

public abstract class StateTestBase {

    public Game gameMock() {
        return mock(Game.class);
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