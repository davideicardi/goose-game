package icardi.goose.game.states;

import icardi.goose.game.Game;
import icardi.goose.game.Player;
import icardi.goose.game.boards.Board;
import icardi.goose.game.commands.AddPlayerCommand;
import icardi.goose.game.commands.GameCommand;

public class WaitingForPlayersState implements GameState {

    private final Board board;

    public WaitingForPlayersState(Board board) {
        super();
        this.board = board;
    }

    @Override
    public String render()
    {
        return String.format(
            "👨  Number of players: %s\nInsert a player by typing: `add player {yourName}`",
            this.board.getPlayers().size()
        );
    }

    @Override
    public GameState process(Game game) {
        // Here I can easily support more players
        if (board.getPlayers().size() == 2) {
            return new PlayerTurnState(board);
        }

        GameCommand command = game.input().waitForCommand();

        if (command instanceof AddPlayerCommand) {
            AddPlayerCommand apc = (AddPlayerCommand)command;
            Board newBoard = board.addPlayer(new Player(apc.getName()));
            return new WaitingForPlayersState(newBoard);
        }

        return GameState.processCmd(command, this);
    }

    @Override
    public boolean equals(Object o) {
        // self check
        if (this == o) {
            return true;
        }
        // null check
        if (o == null) {
            return false;
        }
        // type check and cast
        if (getClass() != o.getClass()) {
            return false;
        }

        return true;
    }
}