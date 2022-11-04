package Game_2048;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
public class Tests {

    @Test
    public void constructor1() {
        Board board = new Board();
        assertEquals(board.get_boardSize(), 4);
    }

    @Test
    public void constructor2_min() {
        Board board = new Board(4);
        assertEquals(board.get_boardSize(), 4);
    }

    @Test
    public void constructor2_max() {
        Board board = new Board(10);
        assertEquals(board.get_boardSize(), 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor2_OOBlow() {
        Board board = new Board(3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor2_OOBhigh() {
        Board board = new Board(11);
    }

}
