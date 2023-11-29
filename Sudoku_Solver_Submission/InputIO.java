package Sudoku_Solver_Submission;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class InputIO {
    public ArrayList<String> readInput( String path ) throws Exception
    {
        try {
            ArrayList<String> input = parseInputFile(path);
            if (!validateInput) throw new Exception("Invalid input file: \"" + path + "\"");
            return input;
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public ArrayList<String> parseInputFile( String path ) throws IOException
    {
        try {
            // Create the output list.
            ArrayList<String> out = new ArrayList<String>();

            // Open the file and check that it is valid.
            File file = new File(path);
            if (!file.exists() || !file.isFile() || !file.canRead()) throw new IOException("Cannot read from specified input file.");

            // Read the lines from the file.
            Scanner in = new Scanner(file);
            while (in.hasNextLine()) {
                out.add(in.nextLine());
            }
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public boolean validateInput(ArrayList<String> input) {
        if (input.size() > 3 || input.size() < 2) return false;
        if (!validFile(input.get(0)) || !validFile(input.get(1))) return false;
        if (input.size() == 3) {
            if (!input.get(2).equals("") && !input.get(2).equals("")) { input.set(2, ""); }
        } else {
            input.add(2, "");
        }
        return true;
    }

    public boolean validFile(String path) {
        try {
            // Open the file and check that it is valid.
            File file = new File(path);
            return (file.exists() && file.isFile() && file.canRead());
        } catch (Exception ex) {
            return false;
        }
    }
}
