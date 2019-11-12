package icardi.goose.game.commands;

public class ExitCommand implements GameCommand {
    public static CommandParser parser() {
        return CommandParser.staticParser("exit", () -> new ExitCommand());
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
}