package com.salil.chessproblems.util;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;


/**
 * Created by sasurendran on 2/21/2015.
 * Kasparov is a class that stores the dimension of the chess board for which the knights tour
 * has to be found. It has a bunch of static methods that are used in the NextMoveRecursiveTask.compute()
 * method.
 */
public class Kasparov{

    private static volatile int dimension;

    /**
     * Initialize the Kasparov class so that it can help with finding the Knights Tour
     * The method throws an IllegalArgumentException if the value passed is greater than
     * 46340
     * @param dim
     */
    public static void initialize(int dim){
        if(dim > Math.sqrt(Integer.MAX_VALUE))
            throw new IllegalArgumentException("Sorry Kasparov doesn't support a dimension greater than "
                    + (int)Math.sqrt(Integer.MAX_VALUE));
        dimension = dim;

    }

    /**
     * Mark the specified row and column as being visited in the bitset
     * and return back the same bitset
     * @param bitSet
     * @param row
     * @param column
     * @return
     */
    public static BitSet setVisited(BitSet bitSet,int row,int column){
        checkInit();
        bitSet.set((row - 1)* dimension + column - 1);
        return bitSet;
    }

    /**
     * If the specified row and column has already been marked as being visited
     * in the passed bitset then it returns true.
     * @param bitSet
     * @param row
     * @param column
     * @return
     */
    public static boolean isVisited(BitSet bitSet,int row, int column){
        checkInit();
        return bitSet.get((row - 1) * dimension + column - 1);
    }

    /**
     * Returns back a list of positions that can be visited legally without
     * going over the boundaries of the chess board and without revisiting
     * any of the squares already visited as represented by the bitset
     * visited squares
     * @param visitedSquares
     * @param row
     * @param column
     * @return
     */
    public static List<int[]> getNonVisitedLegalMoves(
                        BitSet visitedSquares, int row, int column){
        checkInit();
        List<int[]> legalMoveList = new ArrayList<>();
        checkAndAddToList(legalMoveList, visitedSquares,row + 2, column + 1);
        checkAndAddToList(legalMoveList, visitedSquares,row + 1, column + 2);
        checkAndAddToList(legalMoveList, visitedSquares,row - 1, column + 2);
        checkAndAddToList(legalMoveList, visitedSquares,row - 2, column + 1);
        checkAndAddToList(legalMoveList, visitedSquares,row - 2, column - 1);
        checkAndAddToList(legalMoveList, visitedSquares,row - 1, column - 2);
        checkAndAddToList(legalMoveList, visitedSquares,row + 1, column - 2);
        checkAndAddToList(legalMoveList, visitedSquares,row + 2, column - 1);
        return legalMoveList;
    }

    private static void checkAndAddToList(
            List<int[]> legalMoveList, BitSet visitedSquares,int row, int column) {
        if(row <= dimension && row > 0
                && column <= dimension && column > 0 && !isVisited(visitedSquares,row,column))
            legalMoveList.add(new int[]{row,column});
    }

    //The method takes the current visited squares and the next unvisited square that has to
    //be visited and checks if this square is the last one left and if visiting this will
    //visit every single square.
    public static boolean isLastSquareLeft(BitSet visitedSquares, int row, int column) {
        checkInit();
        int size = (int)Math.pow(dimension,2);
        BitSet bits =  setVisited(new BitSet(size),row,column);
        bits.or(visitedSquares);
        return bits.cardinality() == size;
    }

    private static void checkInit(){
        if(dimension == 0)
            throw new IllegalStateException("Please initialize Kasparov to a valid positive integer less than : "
                    + (int)Math.sqrt(Integer.MAX_VALUE));
    }

    public static boolean canJumpTo(int[] currentPosition, int targetRow, int targetColumn){
       return getNonVisitedLegalMoves(new BitSet(dimension * dimension),
               currentPosition[0],currentPosition[1]).stream().anyMatch(
                arr -> arr[0] == targetRow && arr[1] ==targetColumn);
    }
}
