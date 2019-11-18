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
}