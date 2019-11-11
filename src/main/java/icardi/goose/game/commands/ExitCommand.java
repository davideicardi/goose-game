package icardi.goose.game.commands;

public class ExitCommand implements GameCommand {
    public static CommandParser parser() {
        return CommandParser.staticParser("exit", () -> new ExitCommand());
    }
}