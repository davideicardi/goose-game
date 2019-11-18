package icardi.goose.game.moves;

import icardi.goose.game.Player;
import icardi.goose.game.boxes.Box;

public interface Move {
    Player getPlayer();
    Box getDestination();
}