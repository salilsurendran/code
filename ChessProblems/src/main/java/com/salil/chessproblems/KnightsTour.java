package com.salil.chessproblems;

import com.salil.chessproblems.util.Kasparov;
import com.salil.chessproblems.util.Utility;

import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * Created by salilsurendran on 2/21/2015.
 */
public class KnightsTour{

    public static void main(String args[]){
        final Logger LOGGER = Logger.getLogger(KnightsTour.class.getName());
        int[] inputs=Utility.checkInput(args);

        if(inputs == null){
            LOGGER.info("Please enter valid input of the form [dimension of board] [start column] [start row]"
                    + ". For eg. 8 5 1");
            return;
        }

        long startTime = System.currentTimeMillis();
        List<LinkedList<int[]>> tours=KnightsTour.findKnightsTour(inputs);
        long timeTaken = System.currentTimeMillis() - startTime;
        long noOfTours=tours == null ? 0 : tours.stream().count();
        LOGGER.info("Found " + noOfTours + " ways to do  Knights Tour of a "
                + inputs[0] + " * " + inputs[0] + " sized chessboard with knight starting at : "
                + inputs[1] + "," + inputs[2] + " in " + timeTaken/1000.0 + " seconds" + System.lineSeparator());

        if(noOfTours > 0){
            tours.stream().flatMap(l -> Stream.of(l))
                    .forEach(orderMoves ->
                            LOGGER.info(orderMoves.stream()
                                    .map(arr -> arr[0] + "," + arr[1])
                                    .reduce((s1, s2) -> s1 + " -> " + s2)
                                    .get()));

            LOGGER.info(System.lineSeparator() + "***************CLOSED TOURS****************");
            tours.stream().flatMap(l -> Stream.of(l))
                    .filter(orderedMoves -> Kasparov.canJumpTo(orderedMoves.getLast(), inputs[1], inputs[2]))
                    .forEach(orderMoves ->
                            LOGGER.info(orderMoves.stream()
                                    .map(arr -> arr[0] + "," + arr[1])
                                    .reduce((s1, s2) -> s1 + " -> " + s2)
                                    .get()));
        }
    }

    public static List<LinkedList<int[]>> findKnightsTour(int[] input){
        Kasparov.initialize(input[0]);
        final ForkJoinPool pool=new ForkJoinPool();
        try{
            return pool.invoke(new NextMoveRecursiveTask(new BitSet(input[0] * input[0]), input[1], input[2]));
        }finally{
            pool.shutdown();
        }
    }


}
