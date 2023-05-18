package chessgame;

import java.awt.*;
import javax.swing.*;

public class ChessLabel extends JLabel {
    Font f = new Font("Ariel", Font.PLAIN, 90);
    Color bgLight = new Color(222, 184, 135);
    Color bgDark = new Color(139, 69, 19);
    int curRow, curCol;
    String tag, type;

    ChessLabel(String s, String t) {
        super(s);
        type = t;
        tag = s;
    } // ChessLabel()

    void set(int row, int col) {
        setFont(f);
        setOpaque(true);
        setBackground((row+col)%2 == 0 ? bgDark : bgLight);
        setHorizontalAlignment(SwingConstants.CENTER);
        curRow = row;
        curCol = col;
    } // set()

    boolean isValidMove(int destRow, int destCol, ChessLabel[][] labels) {
        if (type.equals("R") || type.equals("r")) {
            if (destRow == curRow && destCol == curCol) {
                return false;
            }
            if (destRow == curRow || destCol == curCol) {
                int start, end;
                if (destRow == curRow) {
                    start = Math.min(curCol, destCol) + 1;
                    end = Math.max(curCol, destCol);
                    for (int col = start; col < end; col++) {
                        if (!labels[curRow][col].tag.equals(" ")) {
                            return false; // Obstacle found
                        }
                    }
                } else {
                    start = Math.min(curRow, destRow) + 1;
                    end = Math.max(curRow, destRow);
                    for (int row = start; row < end; row++) {
                        if (!labels[row][curCol].tag.equals(" ")) {
                            return false; // Obstacle found
                        }
                    }
                }
                return true;
            }
            return false;
        } // Rook
    
        if (type.equals("B") || type.equals("b")) {
            if (destRow == curRow && destCol == curCol) {
                return false;
            }
            int rowDif = Math.abs(destRow - curRow);
            int colDif = Math.abs(destCol - curCol);
            if (rowDif == colDif) {
                int rowDir = (destRow > curRow) ? 1 : -1;
                int colDir = (destCol > curCol) ? 1 : -1;
                int row = curRow + rowDir;
                int col = curCol + colDir;
                while (row != destRow && col != destCol) {
                    if (!labels[row][col].tag.equals(" ")) {
                        return false; // Obstacle found
                    }
                    row += rowDir;
                    col += colDir;
                }
                return true;
            }
            return false;
        } // Bishop

        if (type.equals("N") || type.equals("n")) {
            int rowDif = Math.abs(destRow - curRow);
            int colDif = Math.abs(destCol - curCol);
            return (rowDif == 2 && colDif == 1) || (rowDif == 1 && colDif == 2);
        } // Knight

        if (type.equals("Q") || type.equals("q")) {
            if (destRow == curRow && destCol == curCol) {
                return false;
            }
            int rowDif = Math.abs(destRow - curRow);
            int colDif = Math.abs(destCol - curCol);
            if (destRow == curRow || destCol == curCol || rowDif == colDif) {
                int rowDir = (destRow > curRow) ? 1 : (destRow < curRow) ? -1 : 0;
                int colDir = (destCol > curCol) ? 1 : (destCol < curCol) ? -1 : 0;
                int row = curRow + rowDir;
                int col = curCol + colDir;
                while (row != destRow || col != destCol) {
                    if (!labels[row][col].tag.equals(" ")) {
                        return false; // Obstacle found
                    }
                    row += rowDir;
                    col += colDir;
                }
                return true;
            }
            return false;
        } // Queen

        if (type.equals("K") || type.equals("k")) { // No obstacles
            if (destRow == curRow && destCol == curCol) {
                return false;
            }
            int rowDif = Math.abs(destRow - curRow);
            int colDif = Math.abs(destCol - curCol);
            if ((rowDif == 1 && colDif == 0) || (rowDif == 0 && colDif == 1) || (rowDif == 1 && colDif == 1)) {
                return true;
            }
            return false;
        } // King

        if (type.equals("P") || type.equals("p")) {
            if (destRow == curRow && destCol == curCol) {
                return false;
            }
            int rowDif = destRow - curRow;
            int colDif = destCol - curCol;
            int dir = (tag.equals("\u265F")) ? 1 : -1;
            if (colDif == 0 && rowDif == dir && labels[destRow][destCol].tag.equals(" ")) { // Check if forward direction is empty
                return true;
            }
            if (colDif == 0 && rowDif == dir * 2 && ((curRow == 1 && dir == 1) || (curRow == 6 && dir == -1))) { // For the first move, check if both squares in front are empty
                if (labels[curRow + dir][curCol].tag.equals(" ") && labels[destRow][destCol].tag.equals(" ")) {
                    return true;
                }
            }
            if (Math.abs(colDif) == 1 && rowDif == dir && !labels[destRow][destCol].tag.equals(" ")) {
                return true;
            }
            return false;
        } // Pawn
        return true;
    } // isValidMove()
} // class ChessLabel
