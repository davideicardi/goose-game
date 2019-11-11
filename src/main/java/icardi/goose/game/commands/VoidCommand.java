package icardi.goose.game.commands;

public class VoidCommand implements GameCommand {

    protected VoidCommand() {
        super();
    }

	public static final VoidCommand value = new VoidCommand();
}