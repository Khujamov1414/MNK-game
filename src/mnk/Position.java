package mnk;

public interface Position {
    boolean isValid(Move move);
    int getRows();
    int getColumns();
    int getK();

    Cell getCell(int r, int c);
}