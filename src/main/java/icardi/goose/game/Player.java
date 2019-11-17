package icardi.goose.game;

import java.util.Objects;

public class Player
{
    private final String name;

    public Player(String name) {
        super();

        this.name = name;
    }

	public String getName() {
		return this.name;
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
        return Objects.equals(this.name, other.name);
    }

    @Override    
    public int hashCode() {
        return this.name.hashCode();
    } 
}