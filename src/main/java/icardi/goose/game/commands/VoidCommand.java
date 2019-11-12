package icardi.goose.game.commands;

public class VoidCommand implements GameCommand {

    protected VoidCommand() {
        super();
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

	public static final VoidCommand value = new VoidCommand();
}