import java.lang.reflect.GenericArrayType;
import java.util.Random;

public class GameController {
    private Board board;
    private GameStatus status;
    private int win_value;
    private Random random;

    public GameController() {
        this.board = new Board();
        this.win_value = 2048;
        this.status = GameStatus.IN_PROGRESS;
        this.random = new Random();
        newTile();
    }

    public GameController(int size, int win_value) {
        this.board = new Board(size);
        this.win_value = win_value;
        this.status = GameStatus.IN_PROGRESS;
        this.random = new Random();
        newTile();
    }

    // Getters
    public GameStatus getStatus() {
        return status;
    }

    public Board getBoard() {
        return board;
    }

    public void newTile() {
        boolean found = false;
        while (!found) {
            int x = random.nextInt(board.get_boardSize());
            int y = random.nextInt(board.get_boardSize());
            int val = board.getValue(x, y);

            if (val == -1) {
                board.setTile(x, y, new Tile(random.nextInt(0,10) <7 ? 2:4));
                found = true;
            }
        }

        checkLoss();
    }

    public void reset() {
        this.board = new Board(this.board.get_boardSize());
        this.status = GameStatus.IN_PROGRESS;
    }

    private void checkWin() {
        int size = board.get_boardSize();

        for (int a = 0; a < size; a++) {
            for (int b = 0; b < size; b++) {
                if (board.getValue(a, b) == win_value)
                    status = GameStatus.WON;
            }
        }
    }

    private void checkLoss() {
        boolean playable = false;
        int size = board.get_boardSize();

        for (int a = 0; a < size; a++) {
            for (int b = 0; b < size; b++) {
                if (findSimilarNeighbors(a, b)) {
                    playable = true;
                    return;
                }
            }
        }

        status = playable ? status:GameStatus.LOST;
    }

    private boolean findSimilarNeighbors(int r, int c) {
        int size = board.get_boardSize();
        int value = board.getValue(r, c);

        if (r >= 1 && ( value == board.getValue(r-1, c) || -1 == board.getValue(r-1, c) ))
            return true;
        if (c >= 1 && ( value == board.getValue(r, c-1) || -1 == board.getValue(r, c-1) ))
            return true;
        if (r < size-1 && ( value == board.getValue(r+1, c) || -1 == board.getValue(r+1, c) ))
            return true;
        return c < size-1 && (value == board.getValue(r, c + 1) || -1 == board.getValue(r, c + 1));
    }

    public enum Directions {UP, RIGHT, DOWN, LEFT};

    public void move(Directions direction) {
        int size = board.get_boardSize() - 2;
        int start_row = 0;
        int start_col = 0;
        int delta_row = 0;
        int delta_col = 0;
        switch (direction) {
            case UP -> {
                start_row = 1;
                start_col = 0;
                delta_col = 1;
                delta_row = 0;
                break;
            }
            case RIGHT -> {
                start_row = 0;
                start_col = size;
                delta_col = 0;
                delta_row = 1;
                break;
            }
            case DOWN -> {
                start_row = size;
                start_col = 0;
                delta_col = 1;
                delta_row = 0;
                break;
            }
            case LEFT -> {
                start_row = 0;
                start_col = 1;
                delta_col = 0;
                delta_row = 1;
                break;
            }
        }

        for (int d = 0; d < board.get_boardSize(); d++) {
            recurseMove(start_row, start_col, direction);
            start_row += delta_row;
            start_col += delta_col;
        }

        this.checkWin();
        this.checkLoss();
    }
    public void recurseMove(int row, int col, Directions direction) {
        int delta_row = 0;
        int delta_column = 0;
        switch (direction) {
            case UP -> {
                delta_row = 1;
                if (row == 0) {
                    recurseMove(row + delta_row, col + delta_column, direction);
                    return;
                }
                break;
            }
            case RIGHT -> {
                delta_column = -1;
                if (col == board.get_boardSize()-1) {
                    recurseMove(row + delta_row, col + delta_column, direction);
                    return;
                }
                break;
            }
            case DOWN -> {
                delta_row = -1;
                if (row == board.get_boardSize()-1) {
                    recurseMove(row + delta_row, col + delta_column, direction);
                    return;
                }
                break;
            }
            case LEFT -> {
                delta_column = 1;
                if (col == 0) {
                    recurseMove(row + delta_row, col + delta_column, direction);
                    return;
                }
                break;
            }
        }

        if (row >= board.get_boardSize() || row < 0|| col >= board.get_boardSize() || col < 0)
            return;

        if (board.getValue(row, col) == -1) {
            recurseMove(row + delta_row, col + delta_column, direction);
            return;
        }

        if (board.getValue(row - delta_row, col - delta_column) == -1) {
            board.setTile(row - delta_row, col - delta_column, board.getTile(row, col));
            board.removeTile(row, col);
            recurseMove(row - delta_row, col - delta_column, direction);
            return;
        }

        if (board.getValue(row - delta_row, col - delta_column) == board.getValue(row, col)) {
            board.setTile(row - delta_row, col - delta_column,
                    new Tile(board.getValue(row - delta_row, col - delta_column) * 2));
            board.removeTile(row, col);

            // The one line that makes it collapse all the way:
            recurseMove(row - delta_row, col - delta_column, direction);
            return;
        }

        if (board.getValue(row - delta_row, col - delta_column) != board.getValue(row, col)) {
            recurseMove(row + delta_row, col + delta_column, direction);
            return;
        }
    }



}
