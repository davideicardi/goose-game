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
    public void ParseMoveCommand()
    {
        assertTrue( target.parse("move davide 1, 4").equals(new MoveCommand("davide", 1, 4)) );

        assertTrue( target.parse("move davide 0, 4").equals(VoidCommand.value) );
        assertTrue( target.parse("move davide 4").equals(VoidCommand.value) );
        assertTrue( target.parse("move davide 1, 8").equals(VoidCommand.value) );
    }

    @Test
    public void ParseRollsAndMoveCommand()
    {
        assertTrue( target.parse("move davide").equals(new RollsAndMoveCommand("davide")) );
    }


    @Test
    public void ParseExitCommand()
    {
        assertTrue( target.parse("exit") instanceof ExitCommand );
        assertTrue( target.parse("EXIT") instanceof ExitCommand );
    }

}
