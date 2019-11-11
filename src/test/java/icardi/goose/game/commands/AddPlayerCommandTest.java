package icardi.goose.game.commands;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AddPlayerCommandTest 
{
    @Test
    public void shouldParseValidInput()
    {
        CommandParser target = AddPlayerCommand.parser();

        assertTrue( target.tryParse("add player davide").orElseThrow().equals(new AddPlayerCommand("davide")) );
        assertTrue( target.tryParse("ADD Player peter_parker").orElseThrow().equals(new AddPlayerCommand("peter_parker")) );
    }

    @Test
    public void shouldNotParseInvalidInput()
    {
        CommandParser target = AddPlayerCommand.parser();

        assertTrue( target.tryParse("").isEmpty() );
        assertTrue( target.tryParse("other").isEmpty() );
        assertTrue( target.tryParse("add davide").isEmpty() );
        assertTrue( target.tryParse("player davide").isEmpty() );
        assertTrue( target.tryParse("add player add player davide").isEmpty() );
        assertTrue( target.tryParse("add player davide icardi").isEmpty() );
    }
}
