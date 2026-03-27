import java.util.Random;

/**
 * Author: Aaditya Bhandari
 * Date: 2/28/2025
 * Description: The GameOfLife class implements Conway's Game of Life.
 * It maintains a board of cells where each cell can be alive (1) or dead (0).
 * The board evolves through generations based on specific rules.
 * Bugs: None Known
 * Relection: This Project1 helped me with the concept of 2D array and loops.
 */
public class GameOfLife {
    private int[][] board; // 2D array representing the board

    /**
     * Constructor that initializes a board with random values (0 or 1).
     * A fixed seed (12345) is used for reproducibility.
     *
     * @param rows Number of rows in the board.
     * @param cols Number of columns in the board.
     */
    public GameOfLife(int rows, int cols) {
        board = new int[rows][cols];
        Random rand = new Random(12345); // Fixed seed for reproducibility

        // Populate board with random 0s and 1s
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = rand.nextInt(2);
            }
        }
    }

    /**
     * Constructor that initializes the board using a given 2D array.
     *
     * @param initialBoard A predefined 2D array to initialize the game board.
     */
    public GameOfLife(int[][] initialBoard) {
        board = new int[initialBoard.length][initialBoard[0].length];

        // Copy values from initialBoard to board
        for (int i = 0; i < initialBoard.length; i++) {
            for (int j = 0; j < initialBoard[0].length; j++) {
                board[i][j] = initialBoard[i][j];
            }
        }
    }

    /**
     * Advances the game board by one generation according to the rules of Conway's
     * Game of Life:
     * - Any live cell with fewer than two live neighbors dies.
     * - Any live cell with two or three live neighbors survives.
     * - Any live cell with more than three live neighbors dies.
     * - Any dead cell with exactly three live neighbors becomes a live cell.
     */
    public void advanceOneGeneration() {
        int numRows = board.length;
        int numCols = board[0].length;
        int[][] nextGeneration = new int[numRows][numCols];

        // Iterate through each cell in the board
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                int liveNeighborCount = numLiveNeighbors(i, j); // Count live neighbors

                // Apply Game of Life rules
                if (board[i][j] == 1) { // If cell is alive
                    if (liveNeighborCount < 2 || liveNeighborCount > 3) {
                        nextGeneration[i][j] = 0; // Cell dies
                    } else {
                        nextGeneration[i][j] = 1; // Cell survives
                    }
                } else { // If cell is dead
                    if (liveNeighborCount == 3) {
                        nextGeneration[i][j] = 1; // Cell becomes alive
                    } else {
                        nextGeneration[i][j] = 0; // Cell remains dead
                    }
                }
            }
        }
        board = nextGeneration; // Update board with new generation
    }

    /**
     * Advances the board by a given number of generations.
     *
     * @param numGens The number of generations to advance.
     */
    public void advanceNGenerations(int numGens) {
        for (int i = 1; i < numGens; i++) {
            advanceOneGeneration(); // Update the board
            System.out.println("Generation # " + i + "\n" + this); // Print updated board
        }
    }

    /**
     * Generates a string representation of the board with a grid format.
     * 
     * @return A formatted string representation of the board.
     */
    @Override
    public String toString() {
        String result = "-----------------\n"; // Top border
        for (int[] row : board) {
            result += "| "; // Left border
            for (int cell : row) {
                result += cell + " | "; // Cell value with separator
            }
            result += "\n-----------------\n"; // Bottom border after each row
        }
        return result;
    }

    /**
     * Counts the number of live neighbors for a given cell.
     *
     * @param r Row index of the cell.
     * @param c Column index of the cell.
     * @return The number of live neighbors surrounding the cell.
     */
    public int numLiveNeighbors(int r, int c) {
        int count = 0;
        int[] directions = { -1, 0, 1 }; // Directions for neighbor search

        // Iterate through all possible neighbors
        for (int dr : directions) {
            for (int dc : directions) {
                if (!(dr == 0 && dc == 0)) { // Skip the cell itself
                    int nr = r + dr, nc = c + dc;
                    if (nr >= 0 && nr < board.length && nc >= 0 && nc < board[0].length) {
                        count += board[nr][nc]; // Add live neighbor count
                    }
                }
            }
        }
        return count;
    }
}
