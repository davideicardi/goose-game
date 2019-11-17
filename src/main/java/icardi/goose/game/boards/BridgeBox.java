package icardi.goose.game.boards;

public class BridgeBox implements Box {
    private int bridgeTo;

    public BridgeBox(int bridgeTo) {
        super();
        this.bridgeTo = bridgeTo;
    }

    public int getBridgeTo() {
        return bridgeTo;
    }
}