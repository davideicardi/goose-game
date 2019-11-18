package icardi.goose.game.moves;

import java.util.List;
import java.util.Objects;

import icardi.goose.game.Dice;
import icardi.goose.game.Player;
import icardi.goose.game.boxes.Box;

public class RollsMove implements Move {
    private final Player player;
    private final List<Dice> dices;
    private final Box from;
    private final Box to;

    public RollsMove(final Player player, final List<Dice> dices, final Box from, final Box to) {
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

    public List<Dice> getDices() {
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
        String[] dicesStr = dices.stream()
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

    @Override
    public boolean equals(Object o) {
        // self check
        if (this == o) {
            return true;
        }
        // null check
        if (o == null) {
            return false;
        }
        // type check and cast
        if (getClass() != o.getClass()) {
            return false;
        }
        RollsMove other = (RollsMove)o;
        // field comparison
        return Objects.equals(player, other.player)
        && Objects.equals(dices, other.dices)
        && Objects.equals(from, other.from)
        && Objects.equals(to, other.to);
    }}