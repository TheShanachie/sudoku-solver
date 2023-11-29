package com.github.theshanachie;

public class SudokuSolver {
    private boolean solveSudoku(Integer[][] board){
        SudokuConstraints sc = new SudokuConstraints();
        for(int i = 0; i < 9; i++)
        {
            for(int j = 0; j < 9; j++)
            {
                if ( board[i][j] == -1 )
                {
                    for( int val = 1; val <= 9; val++ )
                    {
                        if (sc.isValid (board, i, j)) {
                            board[i][j] = ch;

                            // Backtracking Portion According to Condition
                            if (solveSudoku( board )) { return true; }
                            else { board[i][j] = -1; }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
