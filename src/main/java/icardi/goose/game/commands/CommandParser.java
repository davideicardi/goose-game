package icardi.goose.game.commands;

import java.util.Optional;
import java.util.function.Supplier;

public interface CommandParser {
    public Optional<GameCommand> tryParse(String line);

    public static CommandParser staticParser(String name, Supplier<GameCommand> supplier) {
        return (line) -> {
            if (line.equalsIgnoreCase(name)) {
                return Optional.of(supplier.get());
            }

            return Optional.empty();
        };
    }
}