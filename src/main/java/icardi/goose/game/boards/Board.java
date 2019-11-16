package icardi.goose.game.boards;

public interface Board {
    Box getBox(int index);
    int size();
}