package icardi.goose.game.states;

import java.util.Objects;

import icardi.goose.game.Player;
import icardi.goose.game.commands.GameCommand;

public class Player2AddedState implements GameState {

    private Player player1;
    private Player player2;

    public Player2AddedState(Player player1, Player player2) {
        super();
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    public String render()
    {
        return String.format(
            "👨👨  players: %s, %s\n Press enter to start the game",
            player1.getName(),
            player2.getName()
            );
    }

    @Override
    public GameState processCommand(GameCommand command)
    {
        return new ErrorState(ErrorState.INVALID_OPERATION, this);
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
        Player2AddedState other = (Player2AddedState)o;
        // field comparison
        return Objects.equals(player1, other.player1)
        && Objects.equals(player2, other.player2);
    }
}