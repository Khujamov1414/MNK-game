package mnk;

import java.util.List;

public class Game {
    private final boolean log;
    private final List<Player> players;

    public Game(final boolean log, final List<Player> players) {
        this.log = log;
        this.players = players;
    }

    public int play(Board board) {
        if (players.size() != board.getPlayerNum()) {
            System.out.println("Wrong player's number");
            return -2;
        }
        while (true) {
            int i = 0;
            for (Player player : players) {
                final int result = move(board, player, i++);
                if (result != -2) {
                    return result;
                }
            }
        }
    }

    private int move(final Board board, final Player player, final int no) {
        final Move move = player.move(board.getPosition(), board.getCell());
        final Result result = board.makeMove(move);
        log("Player " + no + " move: " + move);
        log("Position:\n" + board);
        if (result == Result.WIN) {
            log("Player " + no + " won");
            return no;
        } else if (result == Result.LOSE) {
            log("Player " + no + " lose");
            return no > 0 ? no - 1 : players.size() - 1;
        } else if (result == Result.DRAW) {
            log("Draw");
            return -1;
        } else {
            return -2;
        }
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }
}