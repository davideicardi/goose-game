package icardi.goose.game.boards;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import icardi.goose.game.Player;

public interface Board {
    Box getBox(int position);
    int boxCount();

    Board addPlayer(Player player);
    List<Player> getPlayers();

    Board movePlayer(Player player, int position);
    int getPosition(Player player);
    Map<Player, Integer> getPositions();

    Optional<Player> playerTurn();
    Board changeTurn(Optional<Player> player);

    public static boolean isPlayerTurn(Board board, Player player) {
        return board.playerTurn()
        .map(activePlayer -> activePlayer.equals(player))
        .orElse(false);
    }

    public static Optional<Player> nextPlayer(Board board) {
        if (board.getPlayers().isEmpty()) {
            return Optional.empty();
        }

        int currentPlayer = board.playerTurn()
        .map(activePlayer -> board.getPlayers().indexOf(activePlayer))
        .orElse(0);

        if (currentPlayer < 0) {
            currentPlayer = -1;
        }
        
        int nextPlayer = currentPlayer + 1;
        if (nextPlayer >= board.getPlayers().size()) {
            nextPlayer = 0;
        }

        return Optional.of(board.getPlayers().get(nextPlayer));
    }
}