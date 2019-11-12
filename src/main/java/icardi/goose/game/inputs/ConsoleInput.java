package icardi.goose.game.inputs;

import icardi.goose.game.commands.GameCommand;
import icardi.goose.game.commands.Parser;

public class ConsoleInput implements GameInput
{
    private Parser parser = new Parser();
    @Override
    public GameCommand waitForCommand()
    {
        String line = System.console().readLine();

        return parser.parse(line);
    }
}