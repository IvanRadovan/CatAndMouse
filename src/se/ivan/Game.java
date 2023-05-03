package se.ivan;

import java.util.Random;
import java.util.Scanner;

public class Game {

    private static final Scanner scanner = new Scanner(System.in);

    public static void printMatrix(String[][] matrix) {
        for (String[] array : matrix) {
            for (String element : array) {
                System.out.print(element);
            }
            System.out.println();
        }
    }

    public static void moveSymbol(String[][] matrix, int x, int y) {


        Random rng = new Random();

        boolean playedMoved = false;


        // Movement algorithm for the player
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length; column++) {
                if (matrix[row][column].equals("@")) {
                    if (playedMoved) break;
                    String temp = matrix[row][column];
                    matrix[row][column] = matrix[row + (x)][column + (y)];
                    matrix[row + (x)][column + (y)] = temp;
                    playedMoved = true;
                }
            }
        }

    }

    // This method adds the barriers for a visual effect
    public static String[][] mergeMatrices(String[][] matrix) {
        String[][] newMatrix = new String[8][10];
        int lastRow = newMatrix.length - 1;
        int lastColumn = newMatrix[0].length - 1;

        for (int row = 0; row < newMatrix.length; row++) {
            for (int column = 0; column < newMatrix[row].length; column++) {
                if ((row == 0) || (column == 0) || (row == lastRow) || (column == lastColumn)) {
                    newMatrix[row][column] = "#";
                } else {
                    newMatrix[row][column] = matrix[row - 1][column - 1];
                }
            }
        }
        return newMatrix;
    }

    public static void main(String[] args) {

        String[][] innerMatrix = new String[][]
                {
                        {"@", " ", " ", " ", " ", " ", " ", " "},  // [0;0][0:1][0;2][0;3][0;4][0;5][0:6][0;7]
                        {" ", " ", " ", " ", " ", "$", " ", " "},  // [1;0][1;1][1;2][1;3][1;4][1;5][1:6][1;7]
                        {" ", " ", " ", " ", " ", " ", " ", " "},  // [2;0][2;1][2;2][2;3][2;4][2;5][2:6][2;7]
                        {" ", " ", " ", " ", " ", " ", " ", " "},  // [3;0][3;1][3;2][3;3][3;4][3;5][3:6][3;7]
                        {" ", " ", "$", " ", " ", " ", " ", " "},  // [4;0][4;1][4;2][4;3][4;4][4;5][4:6][4;7]
                        {" ", " ", " ", " ", " ", " ", " ", "¤"},  // [5;0][5;1][5;2][5;3][5;4][5;5][5:6][5;7]
                };

        printMatrix(mergeMatrices(innerMatrix));

        while (true) {

            String input = scanner.nextLine().toUpperCase();
            try {
                switch (input) {
                    case "W" -> moveSymbol(innerMatrix, -1, 0);
                    case "A" -> moveSymbol(innerMatrix, 0, -1);
                    case "D" -> moveSymbol(innerMatrix, 0, 1);
                    case "S" -> moveSymbol(innerMatrix, 1, 0);
                    case "0" -> System.exit(0);
                    default -> {
                        System.out.println("""
                                Use only:
                                W - Up
                                A - Left
                                D - Right
                                S - Down
                                0 - Exit""");
                        continue;
                    }
                }
                printMatrix(mergeMatrices(innerMatrix));
            } catch (IndexOutOfBoundsException e) {
                printMatrix(mergeMatrices(innerMatrix));
            }
        }
    }
}
