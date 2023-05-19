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
        if (type.equals("R")) {
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
                if (labels[destRow][destCol].tag.equals(" ") || !Character.isUpperCase(labels[destRow][destCol].type.charAt(0))) {
                    return true; // Prevent taking own piece
                }
            }
            return false;
        } // White Rook

        if (type.equals("r")) {
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
                if (labels[destRow][destCol].tag.equals(" ") || !Character.isLowerCase(labels[destRow][destCol].type.charAt(0))) {
                    return true; // Prevent taking own piece
                }
            }
            return false;
        } // Black Rook
    
        if (type.equals("B")) {
            if (destRow == curRow && destCol == curCol) {
                return false;
            }
            int rowDiff = Math.abs(destRow - curRow);
            int colDiff = Math.abs(destCol - curCol);
            if (rowDiff == colDiff) {
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
                if (labels[destRow][destCol].tag.equals(" ") || !Character.isUpperCase(labels[destRow][destCol].type.charAt(0))) {
                    return true; // Prevent taking own piece
                }
            }
            return false;
        } // White Bishop

        if (type.equals("b")) {
            if (destRow == curRow && destCol == curCol) {
                return false;
            }
            int rowDiff = Math.abs(destRow - curRow);
            int colDiff = Math.abs(destCol - curCol);
            if (rowDiff == colDiff) {
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
                if (labels[destRow][destCol].tag.equals(" ") || !Character.isLowerCase(labels[destRow][destCol].type.charAt(0))) {
                    return true; // Prevent taking own piece
                }
            }
            return false;
        } // Black Bishop

        if (type.equals("N")) {
            int rowDiff = Math.abs(destRow - curRow);
            int colDiff = Math.abs(destCol - curCol);
            if (labels[destRow][destCol].tag.equals(" ") || !Character.isUpperCase(labels[destRow][destCol].type.charAt(0))) {
                return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2); // Prevent taking own piece
            }
            return false;
        } // White Knight

        if (type.equals("n")) {
            int rowDiff = Math.abs(destRow - curRow);
            int colDiff = Math.abs(destCol - curCol);
            if (labels[destRow][destCol].tag.equals(" ") || !Character.isLowerCase(labels[destRow][destCol].type.charAt(0))) {
                return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2); // Prevent taking own piece
            }
            return false;
        } // Black Knight

        if (type.equals("Q")) {
            if (destRow == curRow && destCol == curCol) {
                return false;
            }
            int rowDiff = Math.abs(destRow - curRow);
            int colDiff = Math.abs(destCol - curCol);
            if (destRow == curRow || destCol == curCol || rowDiff == colDiff) {
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
                if (labels[destRow][destCol].tag.equals(" ") || !Character.isUpperCase(labels[destRow][destCol].type.charAt(0))) {
                    return true; // Prevent taking own piece
                }
            }
            return false;
        } // White Queen

        if (type.equals("q")) {
            if (destRow == curRow && destCol == curCol) {
                return false;
            }
            int rowDiff = Math.abs(destRow - curRow);
            int colDiff = Math.abs(destCol - curCol);
            if (destRow == curRow || destCol == curCol || rowDiff == colDiff) {
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
                if (labels[destRow][destCol].tag.equals(" ") || !Character.isLowerCase(labels[destRow][destCol].type.charAt(0))) {
                    return true; // Prevent taking own piece
                }
            }
            return false;
        } // Black Queen

        if (type.equals("K")) {
            if (destRow == curRow && destCol == curCol) {
                return false;
            }
            int rowDiff = Math.abs(destRow - curRow);
            int colDiff = Math.abs(destCol - curCol);
            if ((rowDiff == 1 && colDiff == 0) || (rowDiff == 0 && colDiff == 1) || (rowDiff == 1 && colDiff == 1)) {
                if (labels[destRow][destCol].tag.equals(" ") || !Character.isUpperCase(labels[destRow][destCol].type.charAt(0))) {
                    return true; // Prevent taking own piece
                }
            }
            return false;
        } // White King

        if (type.equals("k")) {
            if (destRow == curRow && destCol == curCol) {
                return false;
            }
            int rowDiff = Math.abs(destRow - curRow);
            int colDiff = Math.abs(destCol - curCol);
            if ((rowDiff == 1 && colDiff == 0) || (rowDiff == 0 && colDiff == 1) || (rowDiff == 1 && colDiff == 1)) {
                if (labels[destRow][destCol].tag.equals(" ") || !Character.isLowerCase(labels[destRow][destCol].type.charAt(0))) {
                    return true; // Prevent taking own piece
                }
            }
            return false;
        } // Black King

        if (type.equals("P")) {
            if (destRow == curRow && destCol == curCol) {
                return false;
            }
            int rowDiff = destRow - curRow;
            int colDiff = destCol - curCol;
            if (labels[destRow][destCol].tag.equals(" ") || !Character.isUpperCase(labels[destRow][destCol].type.charAt(0))) {
                if (colDiff == 0 && rowDiff == -1 && labels[destRow][destCol].tag.equals(" ")) {
                    return true; // Check if forward direction is empty
                }
                if (colDiff == 0 && rowDiff == -2 && curRow == 6) { // Check if first row
                    if (labels[curRow - 1][curCol].tag.equals(" ") && labels[destRow][destCol].tag.equals(" ")) {
                        return true; // Check if both squares in front are empty
                    }
                }
                if (Math.abs(colDiff) == 1 && rowDiff == -1 && !labels[destRow][destCol].tag.equals(" ")) {
                    return true; // Take diagonally
                }
            } // Prevent taking own piece
            return false;
        } // White Pawn

        if (type.equals("p")) {
            if (destRow == curRow && destCol == curCol) {
                return false;
            }
            int rowDiff = destRow - curRow;
            int colDiff = destCol - curCol;
            if (labels[destRow][destCol].tag.equals(" ") || !Character.isLowerCase(labels[destRow][destCol].type.charAt(0))) {
                if (colDiff == 0 && rowDiff == 1 && labels[destRow][destCol].tag.equals(" ")) {
                    return true; // Check if forward direction is empty
                }
                if (colDiff == 0 && rowDiff == 2 && curRow == 1) { // Check if first row
                    if (labels[curRow + 1][curCol].tag.equals(" ") && labels[destRow][destCol].tag.equals(" ")) {
                        return true; // Check if both squares in front are empty
                    }
                }
                if (Math.abs(colDiff) == 1 && rowDiff == 1 && !labels[destRow][destCol].tag.equals(" ")) {
                    return true; // Take diagonally
                }
            } // Prevent taking own piece
            return false;
        } // Black Pawn

        return true;
    } // isValidMove()
} // class ChessLabel
