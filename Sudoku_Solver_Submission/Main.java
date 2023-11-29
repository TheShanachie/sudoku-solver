package Sudoku_Solver_Submission;

public class Main {
    public static void main(String[] args) {
        /* Read Args **/
        // variables for file paths and mode.
        String read_path;
        String write_path;
        String mode;

        if ((args.length >= 2 && args.length <= 3)) {
            // Add the path to read from...
            read_path = args[0];
            // Add the path to write to...
            write_path = args[1];
            // Add the mode...
            if (args.length == 3) mode = args[2];
            else mode = "";

        } else if (args.length == 0) {
            InputiO in = new InputIO();
            ArrayList<String> input = in.readInput("Sudoku_Solver_Submission\\resources\\input.txt");
            read_path = input.get(0);
            write_path = input.get(1);
            read_path = input.get(2);
        } else {
            // Exit the program.
            System.out.println("Invalid Program Argument Number. Exiting Program.");
            return;
        }

        /* Run Program with Args **/
        handleSudokuSolve(read_path, write_path, mode);
    }

    public static void handleSudokuSolve(String read_path, String write_path, String mode) {
        try {
            // create variables
            SudokuIO io = new SudokuIO();
            Sudoku su = new Sudoku();

            // Try to read from the board.
            Integer[][] board = io.readFromFile(read_path);

            // check if the board is valid.
            if (!su.isValidBoard( board )) {
                System.out.println("Initial sudoku board is not valid. Exiting Program.");
                return;
            }
            else System.out.println("Initial sudoku board is valid.");
            io.printSudoku( board );

            // solve the sudoku given the mode.
            long t1 = System.currentTimeMillis();
            su.solve(board, mode);
            System.out.println("Sudoku solved in time: " + (System.currentTimeMillis() - t1) + " milliseconds");
            io.printSudoku( board );

            // try to write to a file.
            if (io.writeToFile(write_path, board)) {
                System.out.println("Sudoku board written to file: \"" + write_path + "\" successfully.");
            } else {
                System.out.println("Sudoku board was not written to file: \"" + write_path + "\" successfully.");
            }

            // finish the program.
            System.out.println("Sudoku finished without error.");
        } catch (Exception ex) {
            System.out.println("Error Occurred...");
            System.out.println("Message: " + ex.getMessage());
            System.out.println("Cause: " + ex.getCause());
        }
    }
}
