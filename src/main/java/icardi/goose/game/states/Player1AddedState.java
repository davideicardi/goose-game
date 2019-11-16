package icardi.goose.game.states;

import java.util.Objects;

import icardi.goose.game.Player;
import icardi.goose.game.commands.AddPlayerCommand;
import icardi.goose.game.commands.GameCommand;
import icardi.goose.game.inputs.GameInput;

public class Player1AddedState implements GameState {

    private final Player player1;

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
    public GameState process(GameInput input) {
        GameCommand command = input.waitForCommand();
        
        if (command instanceof AddPlayerCommand) {
            AddPlayerCommand apc = (AddPlayerCommand)command;
            Player player2 = new Player(apc.getName());

            if (player1.equals(player2)) {
                return new ErrorState(
                    String.format("%s: already existing player", player1.getName()), this);
            }

            return new Player2AddedState(this.player1, player2);
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
        Player1AddedState other = (Player1AddedState)o;
        // field comparison
        return Objects.equals(player1, other.player1);
    }
}