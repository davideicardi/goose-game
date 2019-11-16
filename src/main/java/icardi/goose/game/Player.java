package icardi.goose.game;

import java.util.Objects;

public class Player
{
    private final String name;
    private final int position;

    public Player(String name) {
        super();

        this.name = name;
        this.position = 1;
    }

    public Player(String name, int position) {
        super();

        this.name = name;
        this.position = position;
    }

	public String getName() {
		return this.name;
    }
    
    public int getPosition() {
		return this.position;
    }

    public Player move(int delta) {
        return new Player(name, position + delta);
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
        Player other = (Player)o;
        // field comparison
        return Objects.equals(this.name, other.name)
        && this.position == other.position;
    }
}