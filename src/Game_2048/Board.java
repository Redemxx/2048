package Game_2048;

import java.util.Base64;

public class Board {
    private static final int boardSize_min = 4;
    private static final int boardSize_max = 10;

    private int boardSize;

    public Board() {
        this.boardSize = 4;
    }

    public Board(int size) {
        if (!(boardSize_min <= size && size <= boardSize_max))
            throw new IllegalArgumentException("Board size must be between 4-10");

        this.boardSize = size;
    }

    public boolean hasEmpty() {
        // TODO:
        // Check if there is an empty spot in the linkedlist
        return false;
    }

    private void checkPosition(int row, int col) {
        if (row < 0 || col < 0 || row >= boardSize || col >= boardSize)
            throw new IllegalArgumentException("Location must be within the bounds of the board");
    }

    public Tile getTile(int row, int col) {
        checkPosition(row, col);

        // TODO:
        // Get tile from position\
        return new Tile();
    }

    public void setTile(int row, int col, Tile t) {
        checkPosition(row, col);

        // TODO:
        // Set tile at location
    }

    public int getValue(int row, int col) {
        checkPosition(row, col);

        // TODO:
        // Get value at position
        // Return -1 if position has no tile

        return 0;
    }
}

