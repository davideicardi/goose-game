package icardi.goose.game.commands;

public class StartCommand implements GameCommand {
    public static CommandParser parser() {
        return CommandParser.staticParser("start", () -> new StartCommand());
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