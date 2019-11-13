package icardi.goose.game.commands;

import java.util.Objects;

public class MoveCommand implements GameCommand {
    private String name;
    private int dice1;
    private int dice2;

    public MoveCommand(String name, int dice1, int dice2) {
        super();
        this.name = name;
        this.dice1 = dice1;
        this.dice2 = dice2;
    }

    public int getDice1() {
        return dice1;
    }

    public int getDice2() {
        return dice2;
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
        MoveCommand other = (MoveCommand)o;
        // field comparison
        return Objects.equals(name, other.name)
        && dice1 == other.dice1
        && dice2 == other.dice2;
    }

    public static CommandParser parser() {
        return CommandParser.regexParser
        (
            "^move (\\w+)\\s+([1-6]),\\s*([1-6])$",
            (matcher) -> new MoveCommand(
                matcher.group(1),
                Integer.parseInt(matcher.group(2)),
                Integer.parseInt(matcher.group(3))
                )
        );
    }
}