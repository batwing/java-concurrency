package com.java.recruitment.linefukuoka;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BacktrackShortestPathInMazeTest {

    @Test
    public void testFindShortestPath2(){
        int width = 5;
        int height = 3;

        int maze[][] =
                {
                        { 1, 1, 1, 1, 1 },
                        { 1, 0, 0, 0, 1 },
                        { 1, 1, 1, 1, 1 },
                };

        int distance = new BacktrackShortestPathInMaze(height, width, 1, 1, 1, 3).findShortestPath(maze);
        assertEquals(2, distance);
    }

    @Test
    public void testFindShortestPath6(){
        int width = 8;
        int height = 5;

        int maze[][] =
                {
                        { 1, 1, 1, 1, 1, 1, 1, 1 },
                        { 1, 0, 0, 0, 0, 0, 0, 1 },
                        { 1, 1, 1, 0, 1, 1, 0, 1 },
                        { 1, 0, 0, 0, 0, 0, 0, 1 },
                        { 1, 1, 1, 1, 1, 1, 1, 1 },
                };

        int distance = new BacktrackShortestPathInMaze(height, width, 1, 1, 3, 1).findShortestPath(maze);
        assertEquals(6, distance);
    }
}
