package icardi.goose.game;

import java.util.Objects;

public class Player
{
    private String _name;

    public Player(String name) {
        super();

        _name = name;
    }

	public String getName() {
		return _name;
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
        return Objects.equals(_name, other.getName());
    }
}