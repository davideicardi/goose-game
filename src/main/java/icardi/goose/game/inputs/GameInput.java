package icardi.goose.game.inputs;

import icardi.goose.game.commands.GameCommand;

@FunctionalInterface
public interface GameInput
{
    GameCommand waitForCommand();
}