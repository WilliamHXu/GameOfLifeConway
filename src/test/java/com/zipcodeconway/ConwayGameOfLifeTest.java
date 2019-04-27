package com.zipcodeconway;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConwayGameOfLifeTest {

    @Test
    public void runTest1() {
        int[][] start = {
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0}};
        int[][] expected = {
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0}};
        ConwayGameOfLife sim = new ConwayGameOfLife(5, start);
        int[][] results = sim.simulate(9);
        Assert.assertArrayEquals(expected, results);
//        assertTrue(java.util.Arrays.deepEquals(results, expected));
    }

    @Test
    public void runTest2() {
        int[][] start = {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}};
        int[][] expected = {
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0}};
        ConwayGameOfLife sim = new ConwayGameOfLife(5, start);
        int[][] results = sim.simulate(10);
        Assert.assertArrayEquals(expected, results);
//        assertTrue(java.util.Arrays.deepEquals(results, expected));
    }
}
