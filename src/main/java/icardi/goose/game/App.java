package icardi.goose.game;

import icardi.goose.game.boards.Board;
import icardi.goose.game.boards.GooseBoard;
import icardi.goose.game.inputs.ConsoleInput;
import icardi.goose.game.outputs.ConsoleOutput;
import icardi.goose.game.states.WelcomeState;

public class App 
{
    public static void main( String[] args )
    {
        Board board = new GooseBoard();
        WelcomeState initialState = new WelcomeState(board);
        
        GooseGame game = new GooseGame(
            new ConsoleInput(),
            new ConsoleOutput()            
            );

        game.start(initialState);
    }
}
