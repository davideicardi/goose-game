package icardi.goose.game.commands;

public class StartCommand implements GameCommand {
    public static CommandParser parser() {
        return CommandParser.staticParser("start", () -> new StartCommand());
    }
}