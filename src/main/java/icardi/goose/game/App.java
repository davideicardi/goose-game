package icardi.goose.game;

import icardi.goose.game.boards.GooseGameBoard;
import icardi.goose.game.inputs.ConsoleInput;
import icardi.goose.game.outputs.ConsoleOutput;
import icardi.goose.game.states.WelcomeState;

public class App 
{
    public static void main( String[] args )
    {
        GooseGame game = new GooseGame(
            new ConsoleInput(),
            new ConsoleOutput(),
            new GooseGameBoard()            
            );

        game.start(new WelcomeState());
    }
}
