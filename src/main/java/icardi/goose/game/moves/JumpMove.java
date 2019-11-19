package icardi.goose.game.moves;

import java.util.Objects;

import icardi.goose.game.Player;
import icardi.goose.game.boxes.Box;

public class JumpMove implements Move {
    private final Player player;
    private final Box to;

    public JumpMove(final Player player, final Box to) {
        super();
        this.player = player;
        this.to = to;
    }

    public Box getTo() {
        return to;
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
            "%s jumps to %s.",
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
        JumpMove other = (JumpMove)o;
        // field comparison
        return Objects.equals(player, other.player)
        && Objects.equals(to, other.to);
    }}