package icardi.goose.game;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class PlayerTest 
{
    @Test
    public void shouldStartFrom1()
    {
        Player target = new Player("davide");

        assertEquals(1, target.getPosition());
    }

    @Test
    public void shouldMove()
    {
        Player target = new Player("davide");

        assertEquals(6, target.move(5).getPosition());
        assertEquals(4, target.move(5).move(-2).getPosition());
    }
}
