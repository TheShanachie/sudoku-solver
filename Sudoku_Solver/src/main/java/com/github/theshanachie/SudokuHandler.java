package com.github.theshanachie;

public class SudokuHandler {
    SudokuIO io = new SudokuIO();
    Sudoku su = new Sudoku();
    public Integer[][] runSudoku(Integer[][] board, String mode) {
        io.printSudoku( board );
        if (!su.isValidBoard( board )) {
            System.out.println("Initial sudoku board is not valid. Exiting Program.");
            return null;
        }
        else System.out.println("Initial sudoku board is valid.");
        long t1 = System.currentTimeMillis();
        su.solve(board, mode);
        System.out.println("Sudoku solved in time: " + (System.currentTimeMillis() - t1) + "milliseconds");
        io.printSudoku( board );
        return board;
    }
}
