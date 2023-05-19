package chessgame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Board extends JFrame implements MouseListener {
    // Initialise arrays and variables to hold panels and images of the board
    private ChessLabel[][] labels;
    private boolean held = false;
    private String symbol, piece;
    private boolean isWhiteTurn;
    private int selRow, selCol;
    private int wScore, bScore;
    private int count = 0;

    Container contentPane = getContentPane();
    GridLayout gridLayout = new GridLayout(8, 8);

    public Board() {
        isWhiteTurn = true;
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
        setTitle("Material Chess");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
        } // row

        revalidate(); // Revalidate the container to reflect the changes
        repaint(); // Repaint the container to update the GUI
    } // update()

    void check() {
        int wPieces = 0, bPieces = 0;
        for (int row = 0; row < labels.length; row++) {
            for (int col = 0; col < labels[0].length; col++) {
                if (labels[row][col].type != null && Character.isUpperCase(labels[row][col].type.charAt(0)) && !labels[row][col].type.equals("P")) {
                    wPieces++; // Check if there are any white pieces left
                }
                if (labels[row][col].type != null && Character.isLowerCase(labels[row][col].type.charAt(0)) && !labels[row][col].type.equals("p")) {
                    bPieces++; // Check if there are any black pieces left
                }
            }
        } // row

        if (wPieces == 0 || bPieces == 0) {
            end();
        }
    }

    void end() {
        wScore = 0;
        bScore = 0;
        for (int row = 0; row < labels.length; row++) {
            for (int col = 0; col < labels[0].length; col++) {
                if (labels[row][col].type != null) {
                    if (labels[row][col].type.equals("R")) {
                        wScore += 5;
                    }
                    if (labels[row][col].type.equals("B")) {
                        wScore += 3;
                    }
                    if (labels[row][col].type.equals("N")) {
                        wScore += 3;
                    }
                    if (labels[row][col].type.equals("Q")) {
                        wScore += 9;
                    }
                    if (labels[row][col].type.equals("K")) {
                        wScore += 10;
                    }
                    if (labels[row][col].type.equals("P")) {
                        wScore++;
                    }
                    if (labels[row][col].type.equals("r")) {
                        bScore += 5;
                    }
                    if (labels[row][col].type.equals("b")) {
                        bScore += 3;
                    }
                    if (labels[row][col].type.equals("n")) {
                        bScore += 3;
                    }
                    if (labels[row][col].type.equals("q")) {
                        bScore += 9;
                    }
                    if (labels[row][col].type.equals("k")) {
                        bScore += 10;
                    }
                    if (labels[row][col].type.equals("p")) {
                        bScore++;
                    }
                } // Rook: 5, Bishop: 3, Knight: 3, Queen: 9, King: 10, Pawn: 1
            }
        } // row

        if (wScore > bScore) {
            System.out.println("White wins!");
            System.out.println("White: " + wScore);
            System.out.println("Black: " + bScore);
            System.out.println();
        } else if (wScore < bScore) {
            System.out.println("Black wins!");
            System.out.println("White: " + wScore);
            System.out.println("Black: " + bScore);
            System.out.println();
        } else {
            System.out.println("Tie");
            System.out.println("White: " + wScore);
            System.out.println("Black: " + bScore);
            System.out.println();
        }
    } // end()

    @Override
    public void mouseClicked(MouseEvent e) {
        // Initial coordinates of mouse, reversed to follow 2d array format
        int destRow = e.getY()/100;
        int destCol = e.getX()/100;

        if (held) {
            if (labels[selRow][selCol].isValidMove(destRow, destCol, labels)) {
                labels[destRow][destCol] = new ChessLabel(symbol, piece);
                labels[selRow][selCol] = new ChessLabel(" ", null);
                if (labels[destRow][destCol].type.equals("P") && destRow == 0) {
                    labels[destRow][destCol] = new ChessLabel("\u2655", "Q");
                }
                if (labels[destRow][destCol].type.equals("p") && destRow == 7) {
                    labels[destRow][destCol] = new ChessLabel("\u265B", "q");
                }
                isWhiteTurn = !isWhiteTurn;
                count++;
            } // Checks if move is valid based on type of chess piece
            if (count == 100) {
                end();
            } // Ends game after 100 moves
            held = false;
            update();
            check();
        } else {
            if (isWhiteTurn && labels[destRow][destCol].type != null && Character.isUpperCase(labels[destRow][destCol].type.charAt(0))) {
                if (!labels[destRow][destCol].tag.equals(" ")) { // Checks if what's selected is empty
                    symbol = labels[destRow][destCol].tag;
                    piece = labels[destRow][destCol].type;
                    selRow = destRow;
                    selCol = destCol;
                    held = true;
                    update();
                }
            } // Check if correct turn
            if (!isWhiteTurn && labels[destRow][destCol].type != null && Character.isLowerCase(labels[destRow][destCol].type.charAt(0))) {
                if (!labels[destRow][destCol].tag.equals(" ")) { // Checks if what's selected is empty
                    symbol = labels[destRow][destCol].tag;
                    piece = labels[destRow][destCol].type;
                    selRow = destRow;
                    selCol = destCol;
                    held = true;
                    update();
                }
            } // Check if correct turn
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
