package icardi.goose.game.boards;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.HashMap;

import icardi.goose.game.Player;

public class GooseBoard implements Board {

    private final List<Player> players;
    private final Map<Player, Integer> positions;
    private final int activePlayer;

    public GooseBoard() {
        super();

        players = new ArrayList<Player>();
        positions = new HashMap<Player, Integer>();
        activePlayer = 0;
    }

    protected GooseBoard(
        List<Player> players,
        Map<Player, Integer> positions,
        int activePlayer
        ) {
        super();

        this.players = players;
        this.positions = positions;
        this.activePlayer = activePlayer;
    }

    @Override
    public Box getBox(int position) {
        return boxes[position + 1];
    }
	@Override
	public int boxCount() {
		return boxes.length;
	}

	@Override
	public Board addPlayer(Player player) {
        List<Player> newPlayers = new ArrayList<>(players);
        newPlayers.add(player);

		return new GooseBoard(newPlayers, positions, activePlayer);
	}

	@Override
	public List<Player> getPlayers() {
		return players;
	}

	@Override
	public Board movePlayer(Player player, int position) {
        Map<Player, Integer> newPositions = new HashMap<>(positions);
        newPositions.put(player, position);

		return new GooseBoard(players, newPositions, activePlayer);
	}

	@Override
	public int getPosition(Player player) {
        if (positions.containsKey(player)) {
            return positions.get(player);
        }

		return 1;
    }
    
    @Override
    public Map<Player, Integer> getPositions() {
        return new HashMap<>(positions);
    }
    
    @Override
    public Optional<Player> playerTurn() {
        if (activePlayer >= 0 && activePlayer < players.size()) {
            return Optional.of(players.get(activePlayer));
        }

        return Optional.empty();
    }
    @Override
    public Board changeTurn(Optional<Player> player) {
        int activePlayer = player.map(p -> players.indexOf(p)).orElse(0);
        if (activePlayer < 0) {
            activePlayer = 0;
        }
        return new GooseBoard(players, positions, activePlayer );
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
        GooseBoard other = (GooseBoard)o;

        // field comparison
        return Objects.equals(players, other.players)
        && Objects.equals(positions, other.positions)
        && activePlayer == other.activePlayer;
    }

    private Box[] boxes = {
        new StartBox(), // Start (1)
        new BlankBox(),
        new BlankBox(),
        new BlankBox(),
        new GooseBox(),  // The Goose (5)
        new BridgeBox(12), // The Bridge (6)
        new BlankBox(),
        new BlankBox(),
        new GooseBox(),  // The Goose (9)
        new BlankBox(), // 10
        new BlankBox(),
        new BlankBox(),
        new BlankBox(),
        new GooseBox(),  // The Goose (14)
        new BlankBox(),
        new BlankBox(),
        new BlankBox(),
        new GooseBox(),  // The Goose (18)
        new BlankBox(),
        new BlankBox(), // 20
        new BlankBox(),
        new BlankBox(),
        new GooseBox(),  // The Goose (23)
        new BlankBox(),
        new BlankBox(),
        new BlankBox(),
        new GooseBox(),  // The Goose (27)
        new BlankBox(),
        new BlankBox(),
        new BlankBox(), // 30
        new BlankBox(),
        new BlankBox(),
        new BlankBox(),
        new BlankBox(),
        new BlankBox(),
        new BlankBox(),
        new BlankBox(),
        new BlankBox(),
        new BlankBox(),
        new BlankBox(), // 40
        new BlankBox(),
        new BlankBox(),
        new BlankBox(),
        new BlankBox(),
        new BlankBox(),
        new BlankBox(),
        new BlankBox(),
        new BlankBox(),
        new BlankBox(),
        new BlankBox(), // 50
        new BlankBox(),
        new BlankBox(),
        new BlankBox(),
        new BlankBox(),
        new BlankBox(),
        new BlankBox(),
        new BlankBox(),
        new BlankBox(),
        new BlankBox(),
        new BlankBox(), // 60
        new BlankBox(),
        new BlankBox(),
        new FinishBox() // 63
    };

}