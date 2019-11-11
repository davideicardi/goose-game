package icardi.goose.game.commands;

import java.util.Objects;

public class AddPlayerCommand implements GameCommand {
    private String name;

    public AddPlayerCommand(String name) {
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
        AddPlayerCommand other = (AddPlayerCommand)o;
        // field comparison
        return Objects.equals(name, other.name);
    }

    public static CommandParser parser() {
        return CommandParser.regexParser
        ("^add player (\\w+)$",
        (matcher) -> new AddPlayerCommand(matcher.group(1)));
    }
}