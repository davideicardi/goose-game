package icardi.goose.game.commands;

import java.util.Optional;

public class Parser {
    private CommandParser[] _parsers = {
        StartCommand.parser(),
        ExitCommand.parser(),
        AddPlayerCommand.parser(),
        MoveCommand.parser()
    };

    public GameCommand parse(String line) {
        for (CommandParser parser : _parsers) {
            Optional<GameCommand> cmd = parser.tryParse(line);
            if (cmd.isPresent()) {
                return cmd.get();
            }
        }

        return VoidCommand.value;
    }
}