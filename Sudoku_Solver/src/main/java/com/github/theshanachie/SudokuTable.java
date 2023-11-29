package com.github.theshanachie;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SudokuTable {
    Variable[][] board;

    public SudokuTable(Integer[][] board) {
        this.board = initBoardDomain(board);
    }

    public Variable[][] get

    private Variable[][] initBoardDomain(Integer[][] board) {
        Variable[][] updated_board =  new Variable[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                updated_board[i][j] = getDomainAt(board, i, j);
            }
        }
        return updated_board;
    }

    private Variable getDomainAt(Integer[][] board, int row, int col) {
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

        // Add the set values to a new Variable.
        Integer[] myArray = new Integer[set.size()];
        return new Variable(row, col, set.toArray(myArray));
    }
}
