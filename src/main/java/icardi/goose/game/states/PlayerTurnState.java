package icardi.goose.game.states;

import java.util.Objects;

import icardi.goose.game.Player;
import icardi.goose.game.commands.ExitCommand;
import icardi.goose.game.commands.GameCommand;
import icardi.goose.game.commands.MoveCommand;
import icardi.goose.game.inputs.GameInput;

public class PlayerTurnState implements GameState {

    private Player player1;
    private Player player2;
    private boolean isPlayer1Turn;

    public PlayerTurnState(Player player1, Player player2, boolean isPlayer1Turn) {
        super();
        this.player1 = player1;
        this.player2 = player2;
        this.isPlayer1Turn = isPlayer1Turn;
    }

    @Override
    public String render() {
        String playersStatus = String.format(
            "%s\n%s`",
            renderPlayer(player1, isPlayer1Turn),
            renderPlayer(player2, !isPlayer1Turn)
            );

        String helpString = "move by typing `move {playerName}";
        return String.format("---------------\n%s\n---------------\n %s", playersStatus, helpString);
    }

    @Override
    public GameState process(GameInput input) {
        GameCommand command = input.waitForCommand();
        
        final String INVALID_DICE_VALUE = "Invalid dice value, must be between 1 and 6";
        final String IT_IS_NOT_YOUR_TURN = "It's not your turn";

        if (command instanceof MoveCommand) {
            MoveCommand apc = (MoveCommand)command;

            if (!apc.getName().equalsIgnoreCase(getActivePlayer())) {
                return new ErrorState(IT_IS_NOT_YOUR_TURN, this); 
            }
            if (apc.getDice1() < 1 || apc.getDice1() > 6 || apc.getDice2() < 1 || apc.getDice2() > 6) {
                return new ErrorState(INVALID_DICE_VALUE, this); 
            }

            return new PlayerMovedState(player1, player2, apc);
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
        PlayerTurnState other = (PlayerTurnState)o;
        // field comparison
        return Objects.equals(player1, other.player1)
        && Objects.equals(player2, other.player2)
        && isPlayer1Turn == other.isPlayer1Turn;
    }

    private String getActivePlayer() {
        return isPlayer1Turn ? player1.getName() : player2.getName();
    }

    private String renderPlayer(Player player, boolean isYourTurn) {
        final String IS_YOUR_TURN = "is your turn 🎲";

        return String.format(
            "%s%s (%s) %s",
            " ".repeat(player.getPosition()),
            player.getName(),
            player.getPosition(),
            isYourTurn ? IS_YOUR_TURN : ""
            );
    }

}