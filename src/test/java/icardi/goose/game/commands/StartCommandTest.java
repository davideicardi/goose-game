package icardi.goose.game.commands;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StartCommandTest 
{
    @Test
    public void shouldParseValidInput()
    {
        CommandParser target = StartCommand.parser();

        assertTrue( target.tryParse("start").orElseThrow() instanceof StartCommand );
        assertTrue( target.tryParse("START").orElseThrow() instanceof StartCommand );
    }

    @Test
    public void shouldNotParseInvalidInput()
    {
        CommandParser target = StartCommand.parser();

        assertTrue( target.tryParse("").isEmpty() );
        assertTrue( target.tryParse("other").isEmpty() );
    }
}
