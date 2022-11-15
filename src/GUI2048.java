import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class GUI2048 extends JPanel implements KeyListener, ActionListener, FocusListener {
    private GameController game;
    private JFrame gui;
    private JMenuBar menus;
    private JMenu fileMenu;
    private JMenuItem fileMenu_reset;
    private JMenuItem fileMenu_quit;
    private JButton[][] button_board;
    private int gameSize;
    private int win_int;
    private boolean keyPressed;

    public GUI2048() {
        super();
        gameSize = getSizeInput("Enter size of board:", 4, 4, 10);
        win_int = 0;
        while (!Tile.power2(win_int))
            win_int = getSizeInput("Enter winning number:", 2048, 2048, Integer.MAX_VALUE);

        game = new GameController(gameSize, win_int);
        generatePanel();
        keyPressed = false;
//        addKeyListener(this);
    }


    private int getSizeInput(String message, int normal, int minBound, int maxBound) {
        int input_size = 0;
        try {
            while (input_size < minBound || input_size > maxBound) {
                String s = JOptionPane.showInputDialog(null,
                        message);
                if (s == null) {
                    System.exit(0);
                }
                input_size = Integer.parseInt(s);
            }
        }catch (Exception e) {
            return normal;
        }
        return input_size;
    }

    private void gameMessage(String message) {
        try {
            JOptionPane.showMessageDialog(null, message);
        }catch (Exception e) {
        }

        game.reset();
        game.newTile();
        updateGUI();
    }

    private void generatePanel() {
        gui = new JFrame("2048");
        gui.setLayout(new GridBagLayout());
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        fileMenu = new JMenu("File");
        fileMenu_reset = new JMenuItem("Reset");
        fileMenu_reset.addActionListener(this);
        fileMenu_quit = new JMenuItem("Quit");
        fileMenu_quit.addActionListener(this::actionPerformedQuit);
        fileMenu.add(fileMenu_reset);
        fileMenu.add(fileMenu_quit);

        JMenuBar menus = new JMenuBar();
        menus.add(fileMenu);

        this.setLayout(new GridLayout(gameSize, gameSize));
        this.setPreferredSize(new Dimension(680, 680));

        button_board = new JButton[gameSize][gameSize];
        int border_size = gameSize < 7 ? 10: 4;
        Border button_border = BorderFactory.createMatteBorder(border_size,border_size,border_size,border_size,
                new Color(161, 145, 130));
        for (int row = 0; row < gameSize; row++) {
            for (int col = 0; col < gameSize; col++) {
                JButton button = new JButton("");
                button.setMargin(new Insets(1,1,1,1));
                button.setFont(new Font("Arial", Font.BOLD, gameSize < 7 ? 40:22));
                button.setBorder(button_border);
                button.setFocusable(false);
                button_board[row][col] = button;
                this.add(button_board[row][col]);
            }
        }
        updateGUI();

        gui.getContentPane().add(this);
        gui.setSize(756, 756);
        gui.setJMenuBar(menus);
        gui.setVisible(true);

        setFocusable(true);
        requestFocus();
        addKeyListener(this);
        addFocusListener(this);
    }

    @Override
    public void focusGained(FocusEvent e) {
        addKeyListener(this);
    }

    @Override
    public void focusLost(FocusEvent e) {
        removeKeyListener(this);
    }

    private void updateGUI() {
        Board board = game.getBoard();
        for (int row = 0; row < gameSize; row++) {
            for (int col = 0; col < gameSize; col++) {
                int val = board.getValue(row,col);
                String stVal = (val != -1) ? String.valueOf(val) : "";
                button_board[row][col].setText(stVal);

                Color button_color;
                Color font_color = new Color(249, 246, 242);
                switch (val) {
                    case -1 -> button_color = new Color(194, 179, 165);
                    case 2 -> {
                        button_color = new Color(238, 228, 218);
                        font_color = new Color(119, 110, 101);
                    }
                    case 4 -> {
                        button_color = new Color(237, 224, 200);
                        font_color = new Color(119, 110, 101);
                    }
                    case 8 -> button_color = new Color(242, 177, 121);
                    case 16 -> button_color = new Color(245, 150, 99);
                    case 32 -> button_color = new Color(246, 125, 95);
                    case 64 -> button_color = new Color(246, 93, 59);
                    case 128 -> button_color = new Color(237, 206, 114);
                    case 256 -> button_color = new Color(237, 204, 97);
                    case 512 -> button_color = new Color(237, 200, 80);
                    case 1024 -> button_color = new Color(237, 196, 63);
                    case 2048 -> button_color = new Color(237, 192, 46);
                    case 4096 -> button_color = new Color(153, 11, 181);
                    default -> button_color = new Color(37, 33, 38);
                }

                button_board[row][col].setForeground(font_color);
                button_board[row][col].setBackground(button_color);
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        if (game.getStatus() == GameStatus.LOST || game.getStatus() == GameStatus.WON)
            return;

        if (!keyPressed) {
            keyPressed = true;
            int in = e.getKeyCode();
            boolean ran_move = false;
            switch (in) {
                case 87, 38 -> {
                    game.move(GameController.Directions.UP);
                    ran_move = true;
                }
                case 68, 39 -> {
                    game.move(GameController.Directions.RIGHT);
                    ran_move = true;
                }
                case 83, 40 -> {
                    game.move(GameController.Directions.DOWN);
                    ran_move = true;
                }
                case 65, 37 -> {
                    game.move(GameController.Directions.LEFT);
                    ran_move = true;
                }
            }

            if (!ran_move)
                return;

            if(game.getBoard().hasEmpty()){
                game.newTile();
            }

            updateGUI();

            if(game.getStatus() == GameStatus.WON){
//                System.out.println("CONGRATULATIONS!\n\n");
                gameMessage("You won! Congratulations.");
            } else if (game.getStatus() == GameStatus.LOST) {
//                System.out.println("Better luck next time.\n\n");
                gameMessage("You lost, try again!");
            }
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        keyPressed = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.reset();
        game.newTile();
        updateGUI();
    }

    public void actionPerformedQuit(ActionEvent e) {
        System.exit(0);
    }

    @Override
    public boolean isFocusTraversable ( ) {
        return true;
    }


    public static void main(String[] args) {
        GUI2048 gui = new GUI2048();
    }
}
