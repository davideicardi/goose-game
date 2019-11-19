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
        return String.format("%s, The Goose 🦢", getPosition());
    }


    @Override
    public boolean equals(Object o) {
        // self check
        if (this == o) {
            return true;
        }
        // null check
        if (o == null) {
            return false;
        }
        // type check and cast
        if (getClass() != o.getClass()) {
            return false;
        }
        GooseBox other = (GooseBox)o;
        // field comparison
        return position == other.position;
    }
}