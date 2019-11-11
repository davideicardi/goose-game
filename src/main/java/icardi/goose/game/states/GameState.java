package icardi.goose.game.states;

import icardi.goose.game.commands.GameCommand;

public interface GameState
{
    String render();

    GameState processCommand(GameCommand command);
}