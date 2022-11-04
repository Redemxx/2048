package Game_2048;

import java.util.Base64;

public class Board {
    private static final int boardSize_min = 4;
    private static final int boardSize_max = 10;
    private LinkedList<LinkedList<Tile>> board;
    private int boardSize;

    public Board() {
        this.boardSize = 4;
        instantiateBoard();
    }

    public Board(int size) {
        if (!(boardSize_min <= size && size <= boardSize_max))
            throw new IllegalArgumentException("Board size must be between 4-10");

        this.boardSize = size;
        instantiateBoard();
    }

    private void instantiateBoard() {
        board = new LinkedList<LinkedList<Tile>>();
        for (int x = 0; x < boardSize; x++) {
            LinkedList<Tile> fill_in = new LinkedList<Tile>();
            for (int y = 0; y < boardSize; y++) {
                fill_in.add(null);
            }
            board.add(fill_in);
        }
    }

    public boolean hasEmpty() {
        for (int a = 0; a < boardSize; a++) {
            for (int b = 0; b < boardSize; b++) {
                if (getTile(a,b) == null) {
                    return true;
                }
            }
        }
        return false;
    }

    private void checkPosition(int row, int col) {
        if (row < 0 || col < 0 || row >= boardSize || col >= boardSize)
            throw new IllegalArgumentException("Location must be within the bounds of the board");
    }

    public Tile getTile(int row, int col) {
        checkPosition(row, col);
        return board.get(row).get(col);
    }

    public void setTile(int row, int col, Tile t) {
        checkPosition(row, col);
        board.get(row).set(col, t);
    }

    public int getValue(int row, int col) {
        checkPosition(row, col);

        Tile t = getTile(row, col);

        if (t == null)
            return -1;

        return t.getValue();
    }
}

