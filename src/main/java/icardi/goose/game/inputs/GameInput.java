package icardi.goose.game.inputs;

import icardi.goose.game.commands.GameCommand;

public interface GameInput
{
    GameCommand waitForCommand();
}