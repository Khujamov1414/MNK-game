package mnk;

import java.io.PrintStream;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;

    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    private int nextInt() {
        while (true) {
            if (in.hasNextInt()) {
                return in.nextInt();
            } else if (!in.hasNext()) {
                return -1;
            }
            in.next();
            out.println("Invalid input,please try again!");
        }
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            out.println("Current position");
            out.println(position);
            out.println("Move " + cell.toString());
            out.println("Please enter your move");
            final Move move = new Move(nextInt(), nextInt(), cell);
            // if ctrl + d
            if (move.getColumn() == -1) {
                return move;
            }
            if (position.isValid(move)) {
                return move;
            }
            out.println("Move invalid");
        }
    }
}
