package mnk;

public class PlayerPosition implements Position {

    private Position position;

    public PlayerPosition(Position position) {
        this.position = position;
    }

    @Override
    public boolean isValid(Move move) {
        return position.isValid(move);
    }

    @Override
    public int getRows() {
        return position.getRows();
    }

    @Override
    public int getColumns() {
        return position.getColumns();
    }

    @Override
    public int getK() {
        return position.getK();
    }

    @Override
    public Cell getCell(int r, int c) {
        return position.getCell(r, c);
    }
    @Override
    public String toString() {
        return position.toString();
    }
}
