package Game_2048;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
public class Tests {

//    BOARD TESTS
//    Constructor Tests for B
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

    @Test
    public void hasEmpty_min_true() {
        Board board = new Board();
        assertEquals(board.hasEmpty(), true);
    }

    @Test
    public void hasEmpty_max_true() {
        Board board = new Board(10);
        assertEquals(board.hasEmpty(), true);
    }

    @Test
    public void hasEmpty_min_false() {
        Board board = new Board();
        for (int a = 0; a < board.get_boardSize(); a++) {
            for (int b = 0; b < board.get_boardSize(); b++) {
                board.setTile(a,b,new Tile());
            }
        }
        assertEquals(board.hasEmpty(), false);
    }

    @Test
    public void hasEmpty_max_false() {
        Board board = new Board(10);
        for (int a = 0; a < board.get_boardSize(); a++) {
            for (int b = 0; b < board.get_boardSize(); b++) {
                board.setTile(a,b,new Tile());
            }
        }
        assertEquals(board.hasEmpty(), false);
    }

    @Test
    public void getTile_min() {
        Board board = new Board(10);
        assertNull(board.getTile(0, 0));
    }

    @Test
    public void getTile_max() {
        Board board = new Board(10);
        assertNull(board.getTile(9,9));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getTile_OOB_min() {
        Board board = new Board(10);
        assertNull(board.getTile(-1,9));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getTile_OOB_max() {
        Board board = new Board(10);
        assertNull(board.getTile(2,10));
    }

    @Test
    public void setTile_min() {
        Board board = new Board(10);
        Tile t =  new Tile();
        board.setTile(0, 0, t);
        assertEquals(board.getTile(0, 0), t);
    }

    @Test
    public void setTile_max() {
        Board board = new Board(10);
        Tile t =  new Tile();
        board.setTile(9, 9, t);
        assertEquals(board.getTile(9, 9), t);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setTile_min_OOB() {
        Board board = new Board(10);
        Tile t =  new Tile();
        board.setTile(0, -1, t);
        assertEquals(board.getTile(0, -1), t);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setTile_max_OOB() {
        Board board = new Board(10);
        Tile t =  new Tile();
        board.setTile(10, 5, t);
        assertEquals(board.getTile(10, 5), t);
    }

    @Test
    public void getValue_min() {
        Board board = new Board(10);
        Tile t =  new Tile(8);
        board.setTile(0, 0, t);
        assertEquals(board.getValue(0, 0), 8);
    }

    @Test
    public void getValue_max() {
        Board board = new Board(10);
        Tile t =  new Tile(8);
        board.setTile(9, 9, t);
        assertEquals(board.getValue(9, 9), 8);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getValue_min_OOB() {
        Board board = new Board(10);
        Tile t =  new Tile(8);
        board.setTile(0, 0, t);
        assertEquals(board.getValue(-1, 0), 8);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getValue_max_OOB() {
        Board board = new Board(10);
        Tile t =  new Tile(8);
        board.setTile(9, 7, t);
        assertEquals(board.getValue(10, 7), 8);
    }

//    TILE TESTS
//
//

    @Test
    public void tile() {
        Tile t = new Tile();
        assertEquals(t.getValue(), 2);
    }

    @Test
    public void tile2() {
        Tile t = new Tile(8);
        assertEquals(t.getValue(), 8);
    }

    @Test(expected = IllegalArgumentException.class)
    public void tile_NP2() {
        Tile t = new Tile(6);
    }

    @Test
    public void getValue() {
        Tile t = new Tile(8);
        assertEquals(t.getValue(), 8);
    }

    @Test
    public void getValue2() {
        Tile t = new Tile(2048);
        assertEquals(t.getValue(), 2048);
    }

    @Test
    public void setValue() {
        Tile t = new Tile(16);
        t.setValue(2048);
        assertEquals(t.getValue(), 2048);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setValue_min_OOB() {
        Tile t = new Tile();
        t.setValue(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setValue_max_OOB() {
        Tile t = new Tile();
        t.setValue(2049);
    }

    @Test
    public void power2_1() {
        assertTrue(Tile.power2(16));
    }

    @Test
    public void power2_2() {
        assertTrue(Tile.power2(2048));
    }

    @Test
    public void power2_3() {
        assertFalse(Tile.power2(10));
    }

    @Test
    public void power2_4() {
        assertFalse(Tile.power2(0));
    }
}
