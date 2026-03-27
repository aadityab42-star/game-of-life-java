import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Author: Aaditya Bhandari
 * Date: 2/28/2025
 * Description: The App class is the entry point for running Conway's Game of
 * Life.
 * It reads an initial game configuration from a file, initializes the game,
 * and advances the game for a given number of generations.
 * Bugs: None Known
 * Relection: This Project1 helped me with the concept of 2D array and loops.
 */

class App {
    /**
     * The main method calls the launchGame() method to start the Game of Life.
     *
     * @param args Command-line arguments (not used).
     * @throws FileNotFoundException If the file "life.txt" is not found.
     */
    public static void main(String[] args) throws FileNotFoundException {
        launchGame();
    }

    /**
     * Reads the game configuration from "life.txt", initializes the Game of Life,
     * and runs the simulation for the specified number of generations.
     *
     * @throws FileNotFoundException If the file "life.txt" is not found.
     */
    public static void launchGame() throws FileNotFoundException {
        // Open the file "life.txt"
        Scanner scan = new Scanner(new File("life.txt"));

        // Read the first line containing the number of rows, columns, and generations
        int rows = scan.nextInt();
        int cols = scan.nextInt();
        int generations = scan.nextInt();

        // Create a 2D array to store the initial board state
        int[][] initialBoard = new int[rows][cols];

        // Populate the board with values from the file
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                initialBoard[i][j] = scan.nextInt();
            }
        }

        // Close the file
        scan.close();

        // Initialize the GameOfLife instance with the loaded board
        GameOfLife game = new GameOfLife(initialBoard);

        // Print the welcome message
        System.out.println("Welcome to the Game of Life! ;-)\n");

        // Print the initial generation
        System.out.println("Generation # 0");
        System.out.println(game);

        // Run the game for the specified number of generations
        game.advanceNGenerations(generations);
    }
}
