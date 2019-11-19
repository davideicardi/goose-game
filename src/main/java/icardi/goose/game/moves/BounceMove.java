package icardi.goose.game.moves;

import java.util.Objects;

import icardi.goose.game.Player;
import icardi.goose.game.boxes.Box;

public class BounceMove implements Move {
    private final Player player;
    private final Box from;
    private final Box to;

    public BounceMove(final Player player, final Box from, final Box to) {
        super();
        this.player = player;
        this.from = from;
        this.to = to;
    }

    public Box getTo() {
        return to;
    }

    public Box getFrom() {
        return from;
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
        return String.format(
            "%s bounces! %s returns to %s.",
            player.getName(),
            player.getName(),
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
        BounceMove other = (BounceMove)o;
        // field comparison
        return Objects.equals(player, other.player)
        && Objects.equals(from, other.from)
        && Objects.equals(to, other.to);
    }}