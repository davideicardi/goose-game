package icardi.goose.game.boards;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.HashMap;

import icardi.goose.game.Dice;
import icardi.goose.game.Player;
import icardi.goose.game.boxes.BlankBox;
import icardi.goose.game.boxes.Box;
import icardi.goose.game.boxes.BridgeBox;
import icardi.goose.game.boxes.FinishBox;
import icardi.goose.game.boxes.GooseBox;
import icardi.goose.game.boxes.StartBox;
import icardi.goose.game.exceptions.DuplicatedPlayerException;
import icardi.goose.game.exceptions.NotYourTurnException;
import icardi.goose.game.moves.BounceMove;
import icardi.goose.game.moves.JumpMove;
import icardi.goose.game.moves.Move;
import icardi.goose.game.moves.RollsMove;

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

    protected GooseBoard(List<Player> players, Map<Player, Integer> positions, int activePlayer) {
        super();

        this.players = players;
        this.positions = positions;
        this.activePlayer = activePlayer;
    }

    @Override
    public Board addPlayer(Player player) throws DuplicatedPlayerException {
        if (players.contains(player)) {
            throw new DuplicatedPlayerException(String.format("%s: already existing player", player.getName()));
        }

        List<Player> newPlayers = new ArrayList<>(players);
        newPlayers.add(player);

        return new GooseBoard(newPlayers, positions, activePlayer);
    }

    @Override
    public List<Player> getPlayers() {
        return players;
    }

    private Box getBox(int position) {
        if (position > boxes.length) {
            return boxes[boxes.length - 1];
        }
        
        return boxes[position - 1];
    }

    @Override
    public Board movePlayer(Player player, int position) {
        Map<Player, Integer> newPositions = new HashMap<>(positions);
        newPositions.put(player, position);

        return new GooseBoard(players, newPositions, activePlayer);
    }

    @Override
    public Box getPlayerBox(Player player) {
        if (positions.containsKey(player)) {
            return getBox(positions.get(player));
        }

        return getBox(1);
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
        return new GooseBoard(players, positions, activePlayer);
    }

    @Override
    public TurnResult turn(Player player, List<Dice> dices) throws NotYourTurnException {
        if (!Board.isPlayerTurn(this, player)) {
            throw new NotYourTurnException(String.format("%s: it's not your turn", player.getName()));
        }

        int delta = Dice.totals(dices);

        Box from = getPlayerBox(player);
        int expectedToPosition = from.getPosition() + delta;
        Box to = getBox(expectedToPosition);

        Move rollsMove = new RollsMove(player, dices, from, to);
        List<Move> moves = generateMoves(rollsMove, expectedToPosition);

        Board newBoard = applyMoves(moves)
        .changeTurn(Board.nextPlayer(this));

        return new TurnResult(newBoard, moves);
    }

    private List<Move> generateMoves(Move move, int expectedToPosition) {
        List<Move> moves = new ArrayList<>();
        moves.add(move);

        // Bounce
        int actualToPosition = move.getDestination().getPosition();
        int bouncesMoves = expectedToPosition - actualToPosition;
        if (bouncesMoves > 0) { // bounce
            Box bounceTo = getBox(actualToPosition - bouncesMoves);
            moves.add(new BounceMove(move.getPlayer(), move.getDestination(), bounceTo));
        }

        // Bridge
        Box landingBox = moves.get(moves.size() - 1).getDestination();
        if (landingBox instanceof BridgeBox) {
            BridgeBox bridgeBox = (BridgeBox)landingBox;
            int expectedJumpTo = bridgeBox.getBridgeTo();
            Move jumpMove = new JumpMove(move.getPlayer(), getBox(expectedJumpTo));
            List<Move> subMoves = generateMoves(jumpMove, expectedJumpTo);
            moves.addAll(subMoves);
        }

        return moves;
    }

    private Board applyMoves(List<Move> moves) {
        Board currentBoard = this;
        for (Move move : moves) {
            currentBoard = currentBoard
            .movePlayer(move.getPlayer(), move.getDestination().getPosition());
        }

        return currentBoard;
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
        GooseBoard other = (GooseBoard) o;

        // field comparison
        return Objects.equals(players, other.players) && Objects.equals(positions, other.positions)
                && activePlayer == other.activePlayer;
    }

    private Box[] boxes = { new StartBox(1), new BlankBox(2), new BlankBox(3), new BlankBox(4), new GooseBox(5),
            new BridgeBox(6, 12), new BlankBox(7), new BlankBox(8), new GooseBox(9), new BlankBox(10), new BlankBox(11),
            new BlankBox(12), new BlankBox(13), new GooseBox(14), new BlankBox(15), new BlankBox(16), new BlankBox(17),
            new GooseBox(18), new BlankBox(19), new BlankBox(20), new BlankBox(21), new BlankBox(22), new GooseBox(23),
            new BlankBox(24), new BlankBox(25), new BlankBox(26), new GooseBox(27), new BlankBox(28), new BlankBox(29),
            new BlankBox(30), new BlankBox(31), new BlankBox(32), new BlankBox(33), new BlankBox(34), new BlankBox(35),
            new BlankBox(36), new BlankBox(37), new BlankBox(38), new BlankBox(39), new BlankBox(40), new BlankBox(41),
            new BlankBox(42), new BlankBox(43), new BlankBox(44), new BlankBox(45), new BlankBox(46), new BlankBox(47),
            new BlankBox(48), new BlankBox(49), new BlankBox(50), new BlankBox(51), new BlankBox(52), new BlankBox(53),
            new BlankBox(54), new BlankBox(55), new BlankBox(56), new BlankBox(57), new BlankBox(58), new BlankBox(59),
            new BlankBox(60), new BlankBox(61), new BlankBox(62), new FinishBox(63) };

}