package com.salil.chessproblems.util;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.BitSet;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by sasurendran on 2/22/2015.
 */
public class KasparovTest{

    @BeforeClass
    public static void setup(){
        Kasparov.initialize(8);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInitialization(){
        Kasparov.initialize(46341);
    }

    @Test
    public void testSetVisited(){
        BitSet visitedSquares=new BitSet(64);
        Kasparov.setVisited(visitedSquares, 1, 1);
        Assert.assertTrue(Kasparov.isVisited(visitedSquares, 1, 1));
        Kasparov.setVisited(visitedSquares, 8, 1);
        Assert.assertTrue(Kasparov.isVisited(visitedSquares, 8, 1));
        Kasparov.setVisited(visitedSquares, 1, 8);
        Assert.assertTrue(Kasparov.isVisited(visitedSquares, 1, 8));
        Kasparov.setVisited(visitedSquares, 8, 8);
        Assert.assertTrue(Kasparov.isVisited(visitedSquares, 8, 8));
        Kasparov.setVisited(visitedSquares, 6, 4);
        Assert.assertTrue(Kasparov.isVisited(visitedSquares, 6, 4));
        Assert.assertTrue(visitedSquares.cardinality() == 5);
        Assert.assertTrue(visitedSquares.get(0));
        Assert.assertTrue(visitedSquares.get(56));
        Assert.assertTrue(visitedSquares.get(7));
        Assert.assertTrue(visitedSquares.get(63));
        Assert.assertTrue(visitedSquares.get(43));
    }

    @Test
    public void testGetNonVisitedLegalMoves(){
        BitSet visitedSquares=new BitSet(64);

        List<int[]> legalMoves=Kasparov.getNonVisitedLegalMoves(visitedSquares, 1, 1);
        Assert.assertEquals(2, legalMoves.size());
        Assert.assertTrue(contains(legalMoves.stream(), 3, 2));
        Assert.assertTrue(contains(legalMoves.stream(), 2, 3));
        Kasparov.setVisited(visitedSquares, 3, 2);
        legalMoves=Kasparov.getNonVisitedLegalMoves(visitedSquares, 1, 1);
        Assert.assertEquals(1, legalMoves.size());
        Assert.assertTrue(contains(legalMoves.stream(), 2, 3));
        Kasparov.setVisited(visitedSquares, 2, 3);
        legalMoves=Kasparov.getNonVisitedLegalMoves(visitedSquares, 1, 1);
        Assert.assertEquals(0, legalMoves.size());

        visitedSquares=new BitSet(64);
        legalMoves=Kasparov.getNonVisitedLegalMoves(visitedSquares, 8, 8);
        Assert.assertEquals(2, legalMoves.size());
        Assert.assertTrue(contains(legalMoves.stream(), 7, 6));
        Assert.assertTrue(contains(legalMoves.stream(), 6, 7));

        legalMoves=Kasparov.getNonVisitedLegalMoves(visitedSquares, 8, 1);
        Assert.assertEquals(2, legalMoves.size());
        Assert.assertTrue(contains(legalMoves.stream(), 6, 2));
        Assert.assertTrue(contains(legalMoves.stream(), 7, 3));

        legalMoves=Kasparov.getNonVisitedLegalMoves(visitedSquares, 1, 8);
        Assert.assertEquals(2, legalMoves.size());
        Assert.assertTrue(contains(legalMoves.stream(), 3, 7));
        Assert.assertTrue(contains(legalMoves.stream(), 2, 6));

        legalMoves=Kasparov.getNonVisitedLegalMoves(visitedSquares, 4, 4);
        Assert.assertEquals(8, legalMoves.size());
        Assert.assertTrue(contains(legalMoves.stream(), 6, 3));
        Assert.assertTrue(contains(legalMoves.stream(), 5, 2));
        Assert.assertTrue(contains(legalMoves.stream(), 3, 2));
        Assert.assertTrue(contains(legalMoves.stream(), 2, 3));
        Assert.assertTrue(contains(legalMoves.stream(), 2, 5));
        Assert.assertTrue(contains(legalMoves.stream(), 3, 6));
        Assert.assertTrue(contains(legalMoves.stream(), 5, 6));
        Assert.assertTrue(contains(legalMoves.stream(), 6, 5));
    }

    public boolean contains(Stream<int[]> stream, int row, int column){
        return stream.anyMatch(arr -> arr[0] == row && arr[1] == column);
    }

    @Test
    public void testIsLastSquareLeft(){
        BitSet visitedSquares=new BitSet(64);

        visitedSquares.set(0, 61);
        Assert.assertFalse(Kasparov.isLastSquareLeft(visitedSquares, 8, 8));
        Kasparov.setVisited(visitedSquares, 8, 6);
        Assert.assertFalse(Kasparov.isLastSquareLeft(visitedSquares, 8, 8));
        Kasparov.setVisited(visitedSquares, 8, 7);
        Assert.assertTrue(Kasparov.isLastSquareLeft(visitedSquares, 8, 8));

        visitedSquares=new BitSet(64);
        Assert.assertFalse(Kasparov.isLastSquareLeft(visitedSquares, 5, 3));
        Kasparov.setVisited(visitedSquares, 5, 3);
        visitedSquares.flip(0, 63);
    }

    @Test
    public void testCanJumpTo(){
        Assert.assertTrue(Kasparov.canJumpTo(new int[]{3, 3}, 5, 4));
        Assert.assertTrue(Kasparov.canJumpTo(new int[]{3, 3}, 5, 2));
        Assert.assertTrue(Kasparov.canJumpTo(new int[]{3, 3}, 4, 5));
        Assert.assertFalse(Kasparov.canJumpTo(new int[]{3, 3}, 5, 5));
        Assert.assertFalse(Kasparov.canJumpTo(new int[]{3, 3}, 3, 3));
    }
}
