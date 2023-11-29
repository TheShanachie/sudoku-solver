package Sudoku_Solver_Submission;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class SudokuIO {
    public Integer[][] readFromFile( String path ) throws IOException
    {
        try {
            // Output to the user.
            System.out.println("Attempting to read sudoku board from file: \"" + path + "\"");

            // create scanner and return object
            String[] result = new String[9];
            Scanner input = new Scanner( new File(path) );

            // variables for iterative process.
            int index = 0;
            String lineStr;

            // capture each line as String.
            while (input.hasNextLine())
            {
                lineStr = input.nextLine();
                if (index > 8) { throw new IOException("Illegal format in File: " + path + " row length is above limit."); }
                if (lineStr.length() != 9) { throw new IOException("Illegal format in File: " + path + " line length does not match requirement."); }
                result[index++] = lineStr;
            }

            // check row length.
            if (index < 9) { throw new IOException("Illegal format in File: " + path + " row length is below limit."); }

            // close scanner and return.
            input.close();
            return parseInputStrArr( result );
        } catch (FileNotFoundException ex) {
            throw new IOException("File: \"" + path + "\" not found.", ex);
        } catch (Exception ex) {
            throw new IOException("Problem reading from File: \"" + path + "\"", ex);
        }
    }

    public Integer[][] parseInputStrArr( String[] board ) throws Exception
    {
        try {
            Integer[][] output = new Integer[9][9];
            for (int i = 0; i < 9; i++) {
                String str = board[i];
                Integer[] line = new Integer[9];
                for (int j = 0; j < 9; j++) {
                    if (Character.getType(str.charAt(j)) == Character.DECIMAL_DIGIT_NUMBER) {
                        line[j] = str.charAt(j) - '0';
                    } else {
                        line[j] = -1;
                    }
                }
                output[i] = line;
            }
            return output;
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public boolean writeToFile( String path, Integer[][] board )
    {
        try {
            File file = new File(path);
            if(!file.exists() || file.isDirectory()) return false;
            if (!file.canWrite()) return false;

            BufferedWriter out = new BufferedWriter(new FileWriter(file, false));
            for (Integer[] arr : board)
            {
                List<String> strings = new LinkedList<>();
                for (Integer val: arr) {
                    if (val == -1) strings.add("#");
                    else strings.add(Integer.toString(val));
                }
                String line = String.join("", strings);
                out.write( line );
                out.newLine();
            }
            out.close();
            return true;

        } catch (NullPointerException ex) {
            ex.printStackTrace();
            return false;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public void printSudoku( Integer[][] board )
    {
        System.out.println("Printing sudoku board...");
        if (board == null) {
            System.out.println("Null parameter passed to Function: \"printSudoku\"");
            return;
        }
        for (int i = 0; i < board.length; i++) {
            if (board[i] == null) {
                System.out.println("Null row in board passed to Function: \"printSudoku\"");
                return;
            }
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != -1) System.out.print(board[i][j].toString());
                System.out.print(",");
            }
            System.out.println();
        }
    }
}
