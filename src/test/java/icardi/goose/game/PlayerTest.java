package icardi.goose.game;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class PlayerTest 
{
    @Test
    public void shouldCreateAPlayer()
    {
        Player target = new Player("davide");

        assertEquals("davide", target.getName());
    }
}
