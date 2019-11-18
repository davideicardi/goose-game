package icardi.goose.game.commands;

import java.util.Objects;

public class RollsAndMoveCommand implements GameCommand {
    private final String name;

    public RollsAndMoveCommand(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
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
        RollsAndMoveCommand other = (RollsAndMoveCommand)o;
        // field comparison
        return Objects.equals(name, other.name);
    }

    public static CommandParser parser() {
        return CommandParser.regexParser
        (
            "^move (\\w+)$",
            (matcher) -> new RollsAndMoveCommand(
                matcher.group(1)
                )
        );
    }
}