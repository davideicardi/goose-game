package icardi.goose.game.states;

import java.util.Objects;

import icardi.goose.game.Player;
import icardi.goose.game.commands.MoveCommand;
import icardi.goose.game.inputs.GameInput;

public class PlayerMovedState implements GameState {

    private final Player player1;
    private final Player player2;
    private final MoveCommand moveCommand;

    public PlayerMovedState(Player player1, Player player2, MoveCommand moveCommand) {
        super();
        this.player1 = player1;
        this.player2 = player2;
        this.moveCommand = moveCommand;
    }

    @Override
    public String render()
    {
        Player activePlayer = getMovedPlayer();

        return String.format(
            "%s rolls %s, %s. %s moves from %s to %s",
            activePlayer.getName(),
            moveCommand.getDice1(),
            moveCommand.getDice2(),
            activePlayer.getName(),
            activePlayer.getPosition(),
            calcPosition()
            );
    }

    @Override
    public GameState process(GameInput input) {
        return calcNextState();
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
        PlayerMovedState other = (PlayerMovedState)o;
        // field comparison
        return Objects.equals(player1, other.player1)
        && Objects.equals(player2, other.player2)
        && Objects.equals(moveCommand, other.moveCommand);
    }

    private Player getMovedPlayer() {
        if (isPlayer1Moved()) {
            return player1;
        }
        return player2;
    }

    private int calcPosition() {
        int newPosition = getMovedPlayer().getPosition() + moveCommand.getDice1() + moveCommand.getDice2();
        return newPosition;
    }

    private boolean isPlayer1Moved() {
        return player1.getName().equalsIgnoreCase(moveCommand.getName());
    }

    private GameState calcNextState() {
        int delta = moveCommand.total();

        if (isPlayer1Moved()) {
            return new PlayerTurnState(player1.move(delta), player2, !isPlayer1Moved());
        } else {
            return new PlayerTurnState(player1, player2.move(delta), !isPlayer1Moved());
        }
    }
}