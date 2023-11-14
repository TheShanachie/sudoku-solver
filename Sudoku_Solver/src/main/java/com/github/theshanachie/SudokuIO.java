package com.github.theshanachie;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SudokuIO {
    public String[] readRawFromFile( String path ) throws IOException
    {
        try {
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
            return result;
        } catch (FileNotFoundException ex) {
            throw new IOException("File: \"" + path + "\" not found.", ex);
        }
    }

    public char[][] parseInputStrArr( String[]  board )
    {
        return null;
    }

    public boolean printToFile( String path, String prefix, char[][] board )
    {
        try {
            File file = new File(path);
            if (!path.startsWith(prefix)) return false;
            if (!file.canWrite()) return false;

            FileWriter out = new FileWriter( file );
            for (int i = 0; i < board.length; i++) {
                out.write( board[i] );
            }
            out.close();
            return true;

        } catch (NullPointerException ex) {
            return false;
        } catch (IOException ex) {
            return false;
        }
    }

    public String[] buildOutputStrArr( char[][] board )
    {
        return null;
    }
}
