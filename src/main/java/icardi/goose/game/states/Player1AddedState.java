package icardi.goose.game.states;

import java.util.Objects;

import icardi.goose.game.Player;
import icardi.goose.game.commands.GameCommand;

public class Player1AddedState implements GameState {

    private Player player1;

    public Player1AddedState(Player player1) {
        super();
        this.player1 = player1;
    }

    @Override
    public String render()
    {
        return String.format(
            "👨  players: %s\n Insert the second player by typing: add player {yourName}",
            player1.getName()
            );
    }

    @Override
    public GameState processCommand(GameCommand command)
    {
        return new ErrorState("Invalid operation", this);
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
        Player1AddedState other = (Player1AddedState)o;
        // field comparison
        return Objects.equals(player1, other.player1);
    }
}