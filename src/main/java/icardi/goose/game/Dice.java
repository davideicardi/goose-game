package icardi.goose.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import icardi.goose.game.exceptions.InvalidDiceException;

public class Dice
{
    private final int value;

    private Dice(final int value) {
        super();

        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public boolean equals(final Object o) {
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
        final Dice other = (Dice) o;
        // field comparison
        return this.value == other.value;
    }

    @Override
    public int hashCode() {
        return this.value;
    }

    public static Dice fromValue(final int value) throws InvalidDiceException {
        if (value < 1 || value > 6) {
            throw new InvalidDiceException("Invalid dice value, must be between 1 and 6");
        }

        return new Dice(value);
    }

    private static Random rnd = new Random();
    public static Dice roll() {
        return new Dice(rnd.nextInt(6) + 1);
    }

    public static int totals(List<Dice> dices) {
        int total = 0;
        for (Dice dice : dices) {
            total += dice.value;
        }
        return total;
    }

    public static List<Dice> fromValues(int ...values) throws InvalidDiceException {
        List<Dice> result = new ArrayList<Dice>();

        for (int value : values) {
            result.add(Dice.fromValue(value));
        }

        return result;
    }
}