package com.java.recruitment.linefukuoka;

import java.util.Scanner;

/*
You are given a maze with a width of W and height of H. The maze is configured with either 1 or 0, 1 representing the wall while 0 represents a walkable path. We want to know the shortest path from start-point S to goal G.
Diagonal move is not allowed.


Test case

The first line contains the integers W and H. The subsequent H lines contain strings of length W representing the maze grid. Every character in the string is either 1, 0, S, or G. The limitations for W,H,S,G are given below:

There is only one S and one G.
4 <= W <= 100
3 <= H <= 100
Output should be minimal steps from S to G.

Example

input

5 3
11111
1S0G1
11111
output

2
input

8 5
11111111
1S000001
11101101
1G000001
11111111
output

6


Analysis of output value
In the first example, 2 is outputted since we just need to move right twice.
In the second example, 6 is outputted since the shortest path is right, right, down, down, left, left.
 */
public class BacktrackShortestPathInMaze {

    private int height = -1;
    private int width = -1;
    private int startRow = -1;
    private int startCol = -1;
    private int distRow = -1;
    private int distCol = -1;

    public BacktrackShortestPathInMaze(int height, int width, int startRow, int startCol, int distRow, int distCol) {
        this.height = height;
        this.width = width;
        this.startRow = startRow;
        this.startCol = startCol;
        this.distRow = distRow;
        this.distCol = distCol;
    }

    public static void main(String[] args) throws Exception
    {
        Scanner scanner = new Scanner(System.in);
        int width = scanner.nextInt();
        int height = scanner.nextInt();
        int startRow = -1;
        int startCol = -1;
        int distRow = -1;
        int distCol = -1;

        scanner.nextLine();

        int[][] maze = new int[height][width];
        for (int row=0 ; row<height; row++) {
            String lineOfValues = scanner.nextLine();
            for (int col=0;col<width; col++){
                maze[row][col] = lineOfValues.charAt(col);
                if ((char)maze[row][col] == 'S'){
                    startRow = row;
                    startCol = col;
                } else if ((char)maze[row][col] == 'G'){
                    distRow = row;
                    distCol = col;
                }
            }
        }

        int distance = new BacktrackShortestPathInMaze(height, width, startRow, startCol, distRow, distCol).findShortestPath(maze);

        System.out.println( distance);

    }

    public int findShortestPath(int maze[][])
    {
        int[][] visitted = new int[height][width];
        return findShortestPath(maze, visitted, startRow, startCol, distRow, distCol, Integer.MAX_VALUE, 0);
    }

    // Check if it is possible to go to (x, y) from current position. The
    // function returns false if the cell has value 0 or already visited
    private boolean isSafe(int maze[][], int visited[][], int x, int y)
    {
        return !(maze[x][y] == 1 || visited[x][y] != 0);
    }

    // if not a valid position, return false
    private boolean isValid(int height, int width, int x, int y)
    {
        return (x < height && y < width && x >= 0 && y >= 0);
    }

    // Find Shortest Possible Route in a Matrix mat from source cell (0, 0)
    // to destination cell (x, y)

    // 'min_dist' stores length of longest path from source to destination
    // found so far and 'dist' maintains length of path from source cell to
    // the current cell (i, j)

    private int findShortestPath(int maze[][], int visited[][],
                                       int i, int j, int x, int y, int min_dist, int dist)
    {
        // if destination is found, update min_dist
        if (i == x && j == y)
        {
            return Math.min(dist, min_dist);
        }

        // set (i, j) cell as visited
        visited[i][j] = 1;

        // go to bottom cell
        if (isValid(height, width, i + 1, j) && isSafe(maze, visited, i + 1, j)) {
            min_dist = findShortestPath(maze, visited, i + 1, j, x, y,
                min_dist, dist + 1);
        }

        // go to right cell
        if (isValid(height, width,i, j + 1) && isSafe(maze, visited, i, j + 1)) {
            min_dist = findShortestPath(maze, visited, i, j + 1, x, y,
                min_dist, dist + 1);
        }

        // go to top cell
        if (isValid(height, width,i - 1, j) && isSafe(maze, visited, i - 1, j)) {
            min_dist = findShortestPath(maze, visited, i - 1, j, x, y,
                min_dist, dist + 1);
        }

        // go to left cell
        if (isValid(height, width,i, j - 1) && isSafe(maze, visited, i, j - 1)) {
            min_dist = findShortestPath(maze, visited, i, j - 1, x, y,
                min_dist, dist + 1);
        }

        // Backtrack - Remove (i, j) from visited matrix
        visited[i][j] = 0;

        return min_dist;
    }

}
