package icardi.goose.game.moves;

import java.util.Arrays;

import icardi.goose.game.Dice;
import icardi.goose.game.Player;
import icardi.goose.game.boxes.Box;

public class RollsMove implements Move {
    private final Player player;
    private final Dice[] dices;
    private final Box from;
    private final Box to;

    public RollsMove(final Player player, final Dice[] dices, final Box from, final Box to) {
        super();
        this.player = player;
        this.dices = dices;
        this.from = from;
        this.to = to;
    }

    public Box getTo() {
        return to;
    }

    public Box getFrom() {
        return from;
    }

    public Dice[] getDices() {
        return dices;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public Box getDestination() {
        return getTo();
    }

    @Override
    public String toString() {
        String[] dicesStr = Arrays.stream(dices)
        .map(x -> x.getValue())
        .map(String::valueOf)
        .toArray(String[]::new);

        return String.format(
            "%s rolls %s. %s moves from %s to %s",
            player.getName(),
            String.join(", ", dicesStr),
            player.getName(),
            from.toString(),
            to.toString()
            );
    }
}