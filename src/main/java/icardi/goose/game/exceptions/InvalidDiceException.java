package icardi.goose.game.exceptions;

public class InvalidDiceException extends Exception {
    private static final long serialVersionUID = 1L;

    public InvalidDiceException(String msg) {
        super(msg);
    }
}