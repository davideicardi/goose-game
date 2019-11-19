package icardi.goose.game.moves;

import java.util.Objects;

import icardi.goose.game.Player;
import icardi.goose.game.boxes.Box;

public class PrankMove implements Move {
    private final Player player;
    private final Box from;
    private final Box to;

    public PrankMove(final Player player, final Box from, final Box to) {
        super();
        this.player = player;
        this.from = from;
        this.to = to;
    }

    public Box getFrom() {
        return from;
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
            "On %s there is %s, who returns to %s 🤡.",
            from.toString(),
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
        PrankMove other = (PrankMove)o;
        // field comparison
        return Objects.equals(player, other.player)
        && Objects.equals(to, other.to);
    }}