package mnk;

import java.util.Arrays;
import java.util.Map;

public class MNKBoard implements Board, Position {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.H, '-',
            Cell.V, '|',
            Cell.E, '.'
    );

    private final Cell[][] cells;
    private Cell turn;
    private final int m, n, k;
    private final int playerNum;
    private int empty;

    public MNKBoard(int m, int n, int k, int playerNum) {
        this.m = m;
        this.n = n;
        this.k = k;
        this.playerNum = playerNum;
        cells = new Cell[m][n];
        empty = m * n;
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }


    @Override
    public Position getPosition() {
        return new PlayerPosition(this);
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    private int countLine(Move move, int dr, int dc) {
        int ans = 0;
        int row = move.getRow();
        int column = move.getColumn();
        while (isValid(row, column) && move.getValue() == cells[row][column]) {
            ans++;
            row += dr;
            column += dc;
        }
        return ans;
    }

    private boolean checkWin(Move move, int dr, int dc) {
        return countLine(move, dr, dc) + countLine(move, -dr, -dc) - 1 >= k;
    }

    @Override
    public int getPlayerNum() {
        return playerNum;
    }

    private Cell nextTurn(Cell turn) {
        return Cell.CELLS.get((Cell.CELLS.indexOf(turn) + 1) % playerNum);
    }

    @Override
    public Result makeMove(Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }
        cells[move.getRow()][move.getColumn()] = move.getValue();
        if (checkWin(move, 1, 0) || checkWin(move, 0, 1)
                || checkWin(move, 1, 1) || checkWin(move, 1, -1)) {
            return Result.WIN;
        }
        if (--empty == 0) {
            return Result.DRAW;
        }
        turn = nextTurn(turn);
        return Result.UNKNOWN;
    }


    public boolean isValid(int row, int column) {
        return 0 <= row && row < m
                && 0 <= column && column < n;
    }

    @Override
    public boolean isValid(Move move) {
        return isValid(move.getRow(), move.getColumn())
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == move.getValue();
    }

    @Override
    public int getRows() {
        return m;
    }

    @Override
    public int getColumns() {
        return n;
    }

    @Override
    public int getK() {
        return k;
    }

    @Override
    public Cell getCell(int r, int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                sb.append(cell);
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
