package mnk;

import java.util.List;

public enum Cell {
    X, O, V, H, E;
    public static final List<Cell> CELLS = List.of(X, O, H, V);

    public String toString() {
        if (this == Cell.H) {
            return "-";
        }
        if (this == Cell.V) {
            return "|";
        }
        return super.toString();
    }
}