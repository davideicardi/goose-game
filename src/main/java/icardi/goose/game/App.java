package icardi.goose.game;

import icardi.goose.game.inputs.ConsoleInput;
import icardi.goose.game.outputs.ConsoleOutput;
import icardi.goose.game.states.WelcomeState;

public class App 
{
    public static void main( String[] args )
    {
        Game game = new Game(
            new ConsoleInput(),
            new ConsoleOutput()
            );

        game.start(new WelcomeState());
    }
}
