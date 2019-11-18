package icardi.goose.game.boards;

import java.util.List;
import java.util.Optional;

import icardi.goose.game.Dice;
import icardi.goose.game.Player;
import icardi.goose.game.boxes.Box;
import icardi.goose.game.exceptions.DuplicatedPlayerException;
import icardi.goose.game.exceptions.NotYourTurnException;

public interface Board {
    Board addPlayer(Player player) throws DuplicatedPlayerException;
    List<Player> getPlayers();

    TurnResult turn(Player player, List<Dice> dices) throws NotYourTurnException;

    Board movePlayer(Player player, int position);
    Box getPlayerBox(Player player);

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