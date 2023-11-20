package com.github.theshanachie;

import java.lang.invoke.SwitchPoint;
import java.util.*;

public class Sudoku {
    public record Variable (
        Integer row,
        Integer col,
        Integer[] domain
    ) {}

    /**
     * Solve the given sudoku board with a given mode signifying how the program will solve the puzzle.
     * @param board is a 2D Integer object array holding the sudoku board.
     * @param mode is a String holding the mode identifier for how the program will solve the sudoku.
     * @return a boolean value signifying if the sudoku was solved properly.
     */
    public boolean solve(Integer[][] board, String mode)
    {
        switch (mode) {
            case "MRV":
                System.out.println("Using backtracking with minimum remaining values (MRV)");
                return solveSudokuMRV(board);
            case "LCV":
                System.out.println("Using backtracking with least constraining values (LCV)");
                return false;
            default:
                System.out.println("Using simple backtracking");
                return solveSudokuBase(board);
        }
    }

    public boolean solveSudokuBase(Integer[][] board){
        // check if the board is complete.
        if (isComplete(board)) return true;

        // else, solve the sudoku board.
        for(int i = 0; i < 9; i++)
        {
            for(int j = 0; j < 9; j++)
            {
                if ( board[i][j] == -1 )
                {
                    for( int val = 1; val <= 9; val++ )
                    {
                        board[i][j] = val;
                        if (isValid(board, i, j)) {
                            // Backtracking Portion According to Condition
                            if (solveSudokuBase( board )) { return true; }
                            else { board[i][j] = -1; }
                        } else {
                            board[i][j] = -1;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public boolean solveSudokuMRV(Integer[][] board)
    {
        // check if solution
        if (isComplete(board)) return true;
        Variable var = getMRVVariable(board);

        // iterate through the variables domain
        for (Integer value : var.domain)
        {
            // try to assign the value
            board[var.row][var.col] = value;
            if (isValid(board, var.row, var.col)) {
                board[var.row][var.col] = value;

                if (solveSudokuMRV(board)) { return true; }
                else { board[var.row][var.col] = -1; }
            } else {
                board[var.row][var.col] = -1;
            }
        }
        return false;
    }

    public boolean solveSudokuLCV(Integer[][] board)
    {
        return false;
    }

    /* **** ***** ***** ***** ***** ***** ***** ***** ***** **** */
    /* -- Minimum Restraining Value Func. -----------------------*/
    /* **** ***** ***** ***** ***** ***** ***** ***** ***** **** */

    public Variable getMRVVariable(Integer[][] board)
    {
        Variable min = null;
        for( int i = 0; i < 9; i++ ) {
            for ( int j = 0; j < 9; j++ ) {
                if ( board[i][j] == -1 ) {
                    Variable dom = getVariable(board, i, j);
                    if (min == null) min = dom;
                    if (min.domain.length > dom.domain.length) min = dom;
                }
            }
        }
        return min;
    }

    public Variable getVariable(Integer[][] board, int row, int col) {
        Set<Integer> set = new HashSet<Integer>();
        set.addAll(Arrays.asList( new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }));

        // Add the values in the row and column.
        for (int i = 0; i < 9; i++) {
            if (set.contains(board[row][i])) { set.remove(board[row][i]); }
            if (set.contains(board[i][col])) { set.remove(board[i][col]); }
        }

        // Add the values for the current box.
        int c = col - col % 3;
        int r = row - row % 3;
        for (int i = r; i < r + 3; i++) {
            for (int j = c; j < c + 3; j++) {
                if (set.contains(board[i][j])) { set.remove(board[i][j]); }
            }
        }

        // return the set values as an array.
        Integer[] myArray = new Integer[set.size()];
        return new Variable(row, col, set.toArray(myArray));
    }

    /* **** ***** ***** ***** ***** ***** ***** ***** ***** **** */
    /* -- Least Constraining Value Func. ------------------------*/
    /* **** ***** ***** ***** ***** ***** ***** ***** ***** **** */

    public int leastConstrainingValue(Integer[][] board, int row, int col) { return -1; }

    /* **** ***** ***** ***** ***** ***** ***** ***** ***** **** */
    /* -- Constraint Checking -----------------------------------*/
    /* **** ***** ***** ***** ***** ***** ***** ***** ***** **** */

    public boolean rowConstraint(Integer[][] arr, int row)
    {
        HashSet<Integer> store = new HashSet<>();
        for (int i = 0; i < 9; i++) {

            // If already encountered before,
            if (store.contains(arr[row][i])) { return false; }

            // If the cell is not empty, push...
            if (arr[row][i] != -1)  { store.add(arr[row][i]); }
        }
        return true;
    }

    public boolean colConstraint(Integer[][] arr, int col)
    {
        HashSet<Integer> store = new HashSet<>();
        for (int i = 0; i < 9; i++) {

            // If already encountered before,
            if (store.contains(arr[i][col])) { return false; }

            // If the cell is not empty, push...
            if (arr[i][col] != -1) { store.add(arr[i][col]); }
        }
        return true;
    }

    public boolean boxConstraint(Integer[][] arr, int rowVal, int colVal)
    {
        HashSet<Integer> st = new HashSet<>();
        int startRow = rowVal - rowVal % 3;
        int startCol = colVal - colVal % 3;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Integer curr = arr[row + startRow][col + startCol];
                if (st.contains(curr)) { return false; }
                if (curr != -1) { st.add(curr); }
            }
        }
        return true;
    }

    /* **** ***** ***** ***** ***** ***** ***** ***** ***** **** */
    /* -- State Checking ----------------------------------------*/
    /* **** ***** ***** ***** ***** ***** ***** ***** ***** **** */

    public boolean isValid(Integer[][] arr, int row, int col)
    {
        return (rowConstraint(arr, row) && colConstraint(arr, col) && boxConstraint(arr, row, col));
    }

    public boolean isValidBoard(Integer[][] arr)
    {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!isValid(arr, i, j)) { return false; }
            }
        }
        return true;
    }

    public boolean isComplete(Integer[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == -1) { return false; }
            }
        }
        return true;
    }
}
