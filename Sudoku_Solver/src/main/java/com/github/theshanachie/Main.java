package com.github.theshanachie;

public class Main {
    public static void main(String[] args) {
        /** add a path for a READ FILE and WRITE FILE for program IO. **/
        // Add the path to read from...
        String read_path = "src/main/resources/masterSudoku.txt";
        // Add the path to write to...
        String write_path = "src/main/resources/output.txt";

        /** Please do not touch the code below **/
        SudokuIO io = new SudokuIO();
        Sudoku su = new Sudoku();
        try {
            Integer[][] board = io.readFromFile(read_path);
            io.printSudoku( board );
            if (!su.isValidBoard( board )) {
                System.out.println("Initial sudoku board is not valid. Exiting Program.");
                return;
            }
            else System.out.println("Initial sudoku board is valid.");
            su.solve( board, "MRV" );
            io.printSudoku( board );
            io.writeToFile(write_path, board);
        } catch (Exception ex) {
            System.err.println("Exception caught in program main.");
            ex.printStackTrace();
        }
    }
}
