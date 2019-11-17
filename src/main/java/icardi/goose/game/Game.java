package icardi.goose.game;

import icardi.goose.game.inputs.GameInput;
import icardi.goose.game.outputs.GameOutput;

public interface Game {
    GameInput input();
    GameOutput output();
}
