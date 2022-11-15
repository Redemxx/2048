public class Board {
    private static final int boardSize_min = 4;
    private static final int boardSize_max = 10;
    private LinkedList<LinkedList<Tile>> board;
    private int boardSize;

//    Properties
    public int get_boardSize() {
        return boardSize;
    }

//    Constructors
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

    public void removeTile(int row, int col) {
        checkPosition(row, col);
        board.get(row).set(col, null);
    }

    public int getValue(int row, int col) {
        checkPosition(row, col);

        Tile t = getTile(row, col);

        if (t == null)
            return -1;

        return t.getValue();
    }

    public String printBoard() {
        String st = "";
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                st += getValue(row, col) + "\t";
            }
            st += "\n";
        }
        System.out.println(st);
        return st;
    }

}

