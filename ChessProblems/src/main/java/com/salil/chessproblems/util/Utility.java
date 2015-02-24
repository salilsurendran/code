package com.salil.chessproblems.util;

/**
 * Created by sasurendran on 2/21/2015.
 */
public class Utility {

    public static int[] checkInput(String[] args) {
        if (args != null &&  args.length == 3) {
            try {
                int dimension = Integer.valueOf(args[0]);
                int row = Integer.valueOf(args[1]);
                int column = Integer.valueOf(args[2]);
                if (0 < row && row <= dimension && 0 < column && column <= dimension)
                    return new int[]{dimension,row,column};
            } catch (NumberFormatException e) {

            }
        }

        return null;
    }
}
