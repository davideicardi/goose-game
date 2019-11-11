package icardi.goose.game.outputs;

public class ConsoleOutput implements GameOutput {

    @Override
    public void display(String msg)
    {
        System.out.println( msg );
    }
    
}