package icardi.goose.game.boxes;

public class StartBox implements Box {
    private final int position;

    public StartBox(final int position) {
        super();
        this.position = position;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "Start";
    }
}