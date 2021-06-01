package mnk;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        final List<Player> players = List.of(new HumanPlayer(), new RandomPlayer(), new RandomPlayer());
        final Game game = new Game(false, players);
        int n = 4, m = 4, k = 2;
        final Board board = new MNKBoard(n, m, k, players.size());
        int result = game.play(board);
        System.out.println(result);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(board.getPosition().getCell(i, j));
            }
            System.out.println();
        }
    }
}
