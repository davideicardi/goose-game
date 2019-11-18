package icardi.goose.game.boxes;

public class GooseBox implements Box {
    private final int position;

    public GooseBox(final int position) {
        super();
        this.position = position;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return String.format("%s, The Goose", getPosition());
    }

}