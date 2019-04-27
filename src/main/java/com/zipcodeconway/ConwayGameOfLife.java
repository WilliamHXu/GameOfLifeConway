package com.zipcodeconway;


public class ConwayGameOfLife {

    private int[][] currentBoard;
    private int[][] nextBoard;
    private SimpleWindow displayWindow;

    public ConwayGameOfLife(Integer dimension) {
        this(dimension, dimension);
     }

    public ConwayGameOfLife(Integer xDimension, Integer yDimension) {
        nextBoard = new int[xDimension][yDimension];
        currentBoard = new int[xDimension][yDimension];
        createRandomStart();
        this.displayWindow = new SimpleWindow(Math.max(xDimension, yDimension));
    }

    public ConwayGameOfLife(Integer dimension, int[][] startmatrix) {
        this(startmatrix);
        if (!dimension.equals(startmatrix.length) || !dimension.equals(startmatrix[0].length))
            throw new ExceptionInInitializerError();
    }

    public ConwayGameOfLife(int[][] startmatrix) {
        nextBoard = new int[startmatrix.length][startmatrix[0].length];
        currentBoard = startmatrix;
        this.displayWindow = new SimpleWindow(Math.max(startmatrix.length, startmatrix[0].length));
    }

    public static void main(String[] args) {
        ConwayGameOfLife sim = new ConwayGameOfLife(50);
//        int[][] start = {
//                {0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0},
//                {0, 1, 1, 1, 0},
//                {0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0}};
//        ConwayGameOfLife sim = new ConwayGameOfLife(start);
        int[][] endingWorld = sim.simulate(500);
    }

    // Contains the logic for the starting scenario.
    // Which cells are alive or dead in generation 0.
    // allocates and returns the starting matrix of size 'dimension'
    private void createRandomStart() {
        int xDimension = currentBoard.length;
        int yDimension = currentBoard[0].length;
        for (int i = 0; i < xDimension; i++) {
            for (int j = 0; j < yDimension; j++) {
                if (Math.random() < 0.5) currentBoard[i][j] = 0;
                else currentBoard[i][j] = 1;
            }
        }
    }

    public int[][] simulate(Integer maxGenerations) {
        for (int i = 0; i < maxGenerations; i++) {
            determineNextGeneration();
            copyAndZeroOut(nextBoard, currentBoard);
            displayWindow.display(currentBoard, i + 1);
            displayWindow.sleep(250);
        }
        return currentBoard;
    }

    private void determineNextGeneration() {
        for (int i = 0; i < currentBoard.length; i++) {
            for (int j = 0; j < currentBoard[0].length; j++) {
                nextBoard[i][j] = isAlive(i,j,currentBoard);
            }
        }
    }

    // copy the values of 'next' matrix to 'current' matrix,
    // and then zero out the contents of 'next' matrix
    public void copyAndZeroOut(int [][] next, int[][] current) {
        for (int i = 0; i < current.length; i++) {
            for (int j = 0; j < current[0].length; j++) {
                current[i][j] = next[i][j];
            }
        }
        next = new int[current.length][current[0].length];
    }

    // Calculate if an individual cell should be alive in the next generation.
    // Based on the game logic:
	/*
		Any live cell with fewer than two live neighbours dies, as if by needs caused by underpopulation.
		Any live cell with more than three live neighbours dies, as if by overcrowding.
		Any live cell with two or three live neighbours lives, unchanged, to the next generation.
		Any dead cell with exactly three live neighbours cells will come to life.
	*/
    private int isAlive(int row, int col, int[][] world) {
        int sum = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                int rowCheck = (i + world.length) % world.length;
                int colCheck = (j + world[0].length) % world[0].length;
                if (!(i == row && j == col)) sum += world[rowCheck][colCheck];
            }
        }
        // If cell is alive
        if (world[row][col] == 1) {
            if (sum == 3 || sum == 2) return 1;
        }
        // If cell is dead
        else {
            if (sum == 3) return 1;
        }
        return 0;
    }
}
