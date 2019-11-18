package icardi.goose.game.exceptions;

public class NotYourTurnException extends Exception {
    private static final long serialVersionUID = 1L;

    public NotYourTurnException(String msg) {
        super(msg);
    }
}