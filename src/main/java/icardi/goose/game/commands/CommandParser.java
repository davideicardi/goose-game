package icardi.goose.game.commands;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.*;

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

    public static CommandParser regexParser(String pattern, Function<Matcher, GameCommand> supplier) {
        return (line) -> {
            Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(line);  
            if (m.matches()) {
                return Optional.of(supplier.apply(m));
            }

            return Optional.empty();
        };
    }
}