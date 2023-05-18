package chessgame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Board extends JFrame implements MouseListener {
    // Initialise arrays and variables to hold panels and images of the board
    private ChessLabel[][] labels;
    private boolean held = false;
    private String symbol, piece;
    private int selRow, selCol;

    Container contentPane = getContentPane();
    GridLayout gridLayout = new GridLayout(8, 8);

    public Board() {
        labels = new ChessLabel[][] {
            // black
            {new ChessLabel("\u265C", "r"), new ChessLabel("\u265E", "n"), new ChessLabel("\u265D", "b"), 
            new ChessLabel("\u265B", "q"), new ChessLabel("\u265A", "k"), new ChessLabel("\u265D", "b"), 
            new ChessLabel("\u265E", "n"), new ChessLabel("\u265C", "r")},

            {new ChessLabel("\u265F", "p"), new ChessLabel("\u265F", "p"), new ChessLabel("\u265F", "p"), 
            new ChessLabel("\u265F", "p"), new ChessLabel("\u265F", "p"), new ChessLabel("\u265F", "p"), 
            new ChessLabel("\u265F", "p"), new ChessLabel("\u265F", "p")}, 

            // empty
            {new ChessLabel(" ", null), new ChessLabel(" ", null), new ChessLabel(" ", null), 
            new ChessLabel(" ", null), new ChessLabel(" ", null), new ChessLabel(" ", null), 
            new ChessLabel(" ", null), new ChessLabel(" ", null)},

            {new ChessLabel(" ", null), new ChessLabel(" ", null), new ChessLabel(" ", null), 
            new ChessLabel(" ", null), new ChessLabel(" ", null), new ChessLabel(" ", null), 
            new ChessLabel(" ", null), new ChessLabel(" ", null)},

            {new ChessLabel(" ", null), new ChessLabel(" ", null), new ChessLabel(" ", null), 
            new ChessLabel(" ", null), new ChessLabel(" ", null), new ChessLabel(" ", null), 
            new ChessLabel(" ", null), new ChessLabel(" ", null)},

            {new ChessLabel(" ", null), new ChessLabel(" ", null), new ChessLabel(" ", null), 
            new ChessLabel(" ", null), new ChessLabel(" ", null), new ChessLabel(" ", null), 
            new ChessLabel(" ", null), new ChessLabel(" ", null)},

            // white
            {new ChessLabel("\u2659", "P"), new ChessLabel("\u2659", "P"), new ChessLabel("\u2659", "P"), 
            new ChessLabel("\u2659", "P"), new ChessLabel("\u2659", "P"), new ChessLabel("\u2659", "P"), 
            new ChessLabel("\u2659", "P"), new ChessLabel("\u2659", "P")},
            
            {new ChessLabel("\u2656", "R"), new ChessLabel("\u2658", "N"), new ChessLabel("\u2657", "B"), 
            new ChessLabel("\u2655", "Q"), new ChessLabel("\u2654", "K"), new ChessLabel("\u2657", "B"), 
            new ChessLabel("\u2658", "N"), new ChessLabel("\u2656", "R")}
        };
    } // Board()

    void display() {
        setTitle("Unicode Chess");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addMouseListener(this);

        contentPane.setLayout(gridLayout);
        for (int row = 0; row < labels.length; row++) {
            for (int col = 0; col < labels[0].length; col++) {
                labels[row][col].set(row, col);
                contentPane.add(labels[row][col]);
            }
        } // row

        setSize(800, 800);
        setLocationRelativeTo(null);
        setVisible(true);
    } // display()

    void update() {
        contentPane.removeAll(); // Clear the existing components

        for (int row = 0; row < labels.length; row++) {
            for (int col = 0; col < labels[0].length; col++) {
                labels[row][col].set(row, col);
                if (held && row == selRow && col == selCol) {
                    labels[row][col].setBackground(Color.YELLOW);
                }
                contentPane.add(labels[row][col]);
            }
        }

        revalidate(); // Revalidate the container to reflect the changes
        repaint(); // Repaint the container to update the GUI
    } // update()

    @Override
    public void mouseClicked(MouseEvent e) {
        int destRow = e.getY()/100; // Reversed to follow labels[][] format
        int destCol = e.getX()/100; // Reversed to follow labels[][] format

        if (held) {
            if (labels[selRow][selCol].isValidMove(destRow, destCol, labels)) { // Checks if move is valid based on type of chess piece
                labels[destRow][destCol] = new ChessLabel(symbol, piece);
                labels[selRow][selCol] = new ChessLabel(" ", null);
            }
            held = false;
            update();
        } else {
            if (!labels[destRow][destCol].tag.equals(" ")) { // Checks if what's selected is empty
                symbol = labels[destRow][destCol].tag;
                piece = labels[destRow][destCol].type;
                selRow = destRow; // Initialize variable for later
                selCol = destCol; // Initialize variable for later
                held = true;
                update();
            }
        }
    } // mouseClicked()

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
} // class Board
