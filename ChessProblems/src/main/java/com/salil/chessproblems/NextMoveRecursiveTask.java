package com.salil.chessproblems;

import com.salil.chessproblems.util.Kasparov;

import java.util.*;
import java.util.concurrent.RecursiveTask;

/**
 * Created by sasurendran on 2/21/2015.
 * The NextMoveRecursiveTask.compute() will be called over a million times for a board of
 * reasonable size so it is critical that it be very lean. The BitSet visitedSquares
 * is the only thing that maintains the state of the chessboard and is cloned
 * to be passed to the next task.
 */
public class NextMoveRecursiveTask extends RecursiveTask<List<LinkedList<int[]>>>{

    private final BitSet visitedSquares;
    private final int row;
    private final int column;


    public NextMoveRecursiveTask(BitSet visitedSquares, int row, int col){
        this.visitedSquares=visitedSquares;
        this.row=row;
        this.column=col;
    }

    /**
     * This is the main computation method. The strategy followed here is
     * The method will return back all the ways that the chessboard can be traversed from
     * the state that is represented in this current task and after visiting the row and column
     * specified in the member variables row, column.
     * If it can't find any legal moves then return back an empty list or null     *
     * @return
     */
    @Override
    protected List<LinkedList<int[]>> compute(){
        List<LinkedList<int[]>> returnList=null;
        //Check if this is the last square left in the chessboard to visit
        //and if so we have found a way that the knight could visit all
        //possible squares and so add it to the list and return
        if(Kasparov.isLastSquareLeft(visitedSquares, row, column)){
            returnList=new ArrayList<>();
            LinkedList<int []> orderedMoves = new LinkedList<>();
            orderedMoves.add(new int[]{row, column});
            returnList.add(orderedMoves);
            return returnList;
        }

        List<int[]> legalMoves=Kasparov.getNonVisitedLegalMoves(visitedSquares, row, column);
        if(legalMoves != null){
            List<NextMoveRecursiveTask> tasks=new ArrayList<>();
            for(int[] arr : legalMoves){
                BitSet bitSet=(BitSet) visitedSquares.clone();
                //Create the next recursive task by set the current square as visited
                //and passing on the next legal move;
                NextMoveRecursiveTask task=new NextMoveRecursiveTask(
                        Kasparov.setVisited(bitSet, row, column), arr[0], arr[1]);
                //System.out.println("Cardinality = " + bitSet.cardinality());
                task.fork();
                tasks.add(task);
            }
            returnList = new ArrayList<>();
            //Wait for the child tasks to get completed
            for(NextMoveRecursiveTask task : tasks){
                List<LinkedList<int[]>> recursiveList=task.join();
                if(recursiveList != null){
                    //The child task has returned back and has given us a non null
                    //list, which means that if we follow down each one of the ordered
                    //list of moves then we will be able to complete the tour
                    //Add the current position to the front of each one of the lists
                    //and add it to the returned list.
                    for(LinkedList<int[]> orderedMoves:recursiveList){
                        orderedMoves.addFirst(new int[]{row, column});
                        returnList.add(orderedMoves);
                    }
                }
            }
        }
        return returnList;

    }


}
