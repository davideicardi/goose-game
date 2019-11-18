package icardi.goose.game.exceptions;

public class DuplicatedPlayerException extends Exception {
    private static final long serialVersionUID = 1L;

    public DuplicatedPlayerException(String msg) {
        super(msg);
    }
}