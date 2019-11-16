package icardi.goose.game.boards;

public class GooseGameBoard implements Board {

    private Box[] boxes = {
        new StartBox(), // Start (1)
        new BlankBox(),
        new BlankBox(),
        new BlankBox(),
        new GooseBox(),  // The Goose (5)
        new BridgeBox(), // The Bridge (6)
        new BlankBox(),
        new BlankBox(),
        new GooseBox(),  // The Goose (9)
        new BlankBox(), // 10
        new BlankBox(),
        new BlankBox(),
        new BlankBox(),
        new GooseBox(),  // The Goose (14)
        new BlankBox(),
        new BlankBox(),
        new BlankBox(),
        new GooseBox(),  // The Goose (18)
        new BlankBox(),
        new BlankBox(), // 20
        new BlankBox(),
        new BlankBox(),
        new GooseBox(),  // The Goose (23)
        new BlankBox(),
        new BlankBox(),
        new BlankBox(),
        new GooseBox(),  // The Goose (27)
        new BlankBox(),
        new BlankBox(),
        new BlankBox(), // 30
        new BlankBox(),
        new BlankBox(),
        new BlankBox(),
        new BlankBox(),
        new BlankBox(),
        new BlankBox(),
        new BlankBox(),
        new BlankBox(),
        new BlankBox(),
        new BlankBox(), // 40
        new BlankBox(),
        new BlankBox(),
        new BlankBox(),
        new BlankBox(),
        new BlankBox(),
        new BlankBox(),
        new BlankBox(),
        new BlankBox(),
        new BlankBox(),
        new BlankBox(), // 50
        new BlankBox(),
        new BlankBox(),
        new BlankBox(),
        new BlankBox(),
        new BlankBox(),
        new BlankBox(),
        new BlankBox(),
        new BlankBox(),
        new BlankBox(),
        new BlankBox(), // 60
        new BlankBox(),
        new BlankBox(),
        new FinishBox() // 63
    };

    @Override
    public Box getBox(int index) {
        return null;
    }

    @Override
    public int size() {
        return boxes.length;
    }
}