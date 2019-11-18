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
        StartBox other = (StartBox)o;
        // field comparison
        return position == other.position;
    }
}