package icardi.goose.game.commands;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ExitCommandTest 
{
    @Test
    public void shouldParseValidInput()
    {
        CommandParser target = ExitCommand.parser();

        assertTrue( target.tryParse("exit").orElseThrow() instanceof ExitCommand );
        assertTrue( target.tryParse("EXIT").orElseThrow() instanceof ExitCommand );
    }

    @Test
    public void shouldNotParseInvalidInput()
    {
        CommandParser target = ExitCommand.parser();

        assertTrue( target.tryParse("").isEmpty() );
        assertTrue( target.tryParse("other").isEmpty() );
    }
}
