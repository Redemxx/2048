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

    public GameController(int size) {
        this.board = new Board(size);
        this.win_value = 2048;
        this.status = GameStatus.IN_PROGRESS;
        this.random = new Random();
        newTile();
    }

}
