package icardi.goose.game.commands;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ParserTest 
{
    private Parser target = new Parser();

    @Test
    public void ParseInvalid()
    {
        assertTrue( target.parse("").equals(VoidCommand.value) );
        assertTrue( target.parse("other").equals(VoidCommand.value) );
    }

    @Test
    public void ParseAddPlayerCommand()
    {
        assertTrue( target.parse("add player davide").equals(new AddPlayerCommand("davide")) );
        assertTrue( target.parse("ADD Player peter_parker").equals(new AddPlayerCommand("peter_parker")) );

        assertTrue( target.parse("add davide").equals(VoidCommand.value) );
        assertTrue( target.parse("player davide").equals(VoidCommand.value) );
        assertTrue( target.parse("add player add player davide").equals(VoidCommand.value) );
        assertTrue( target.parse("add player davide icardi").equals(VoidCommand.value) );
    }

    @Test
    public void ParseExitCommand()
    {
        assertTrue( target.parse("exit") instanceof ExitCommand );
        assertTrue( target.parse("EXIT") instanceof ExitCommand );
    }

    @Test
    public void ParseStartCommand()
    {
        assertTrue( target.parse("start") instanceof StartCommand );
        assertTrue( target.parse("START") instanceof StartCommand );
    }

}
