package icardi.goose.game.boxes;

public class BridgeBox implements Box {
    private final int bridgeTo;
    private final int position;

    public BridgeBox(final int position, final int bridgeTo) {
        super();
        this.bridgeTo = bridgeTo;
        this.position = position;
    }

    public int getBridgeTo() {
        return bridgeTo;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "The Bridge";
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
        BridgeBox other = (BridgeBox)o;
        // field comparison
        return position == other.position
        && bridgeTo == other.bridgeTo;
    }
}