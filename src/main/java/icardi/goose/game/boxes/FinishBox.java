package icardi.goose.game.boxes;

public class FinishBox implements Box {
    private final int position;

    public FinishBox(final int position) {
        super();
        this.position = position;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return String.valueOf(getPosition());
    }
}