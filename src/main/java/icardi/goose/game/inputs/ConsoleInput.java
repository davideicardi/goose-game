package icardi.goose.game.inputs;

import java.util.Optional;

import icardi.goose.game.commands.AddPlayerCommand;
import icardi.goose.game.commands.CommandParser;
import icardi.goose.game.commands.ExitCommand;
import icardi.goose.game.commands.GameCommand;
import icardi.goose.game.commands.StartCommand;
import icardi.goose.game.commands.VoidCommand;

public class ConsoleInput implements GameInput
{
    private CommandParser[] _parsers = {
        StartCommand.parser(),
        ExitCommand.parser(),
        AddPlayerCommand.parser()
    };

    @Override
    public GameCommand waitForCommand()
    {
        String line = System.console().readLine();

        return parseCommand(line);
    }
    
    private GameCommand parseCommand(String line) {
        for (CommandParser parser : _parsers) {
            Optional<GameCommand> cmd = parser.tryParse(line);
            if (cmd.isPresent()) {
                return cmd.get();
            }
        }

        return VoidCommand.value;
    }
}