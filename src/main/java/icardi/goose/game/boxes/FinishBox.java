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
        FinishBox other = (FinishBox)o;
        // field comparison
        return position == other.position;
    }
}