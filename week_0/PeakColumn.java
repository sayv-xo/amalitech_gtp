import java.util.Scanner;

public class PeakColumn {
    
    private static int getValidIntInput(Scanner scanner) {
        while (true) {
            if (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                scanner.nextLine(); 
                return input;
            } else {
                System.out.println("Invalid input. Please enter an integer:");
                scanner.next(); 
            }
        }
    }

    public static void findPeakColumns(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] peakColumns = new int[1][3];
        int peakCount = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (isMaxInRow(matrix, i, j) && isMinInColumn(matrix, i, j)) {
                    if (peakCount == peakColumns.length) {
                        // Double the size of the array when it gets full
                        peakColumns = resizeArray(peakColumns);
                    }
                    peakColumns[peakCount][0] = i + 1;
                    peakColumns[peakCount][1] = j + 1;
                    peakColumns[peakCount][2] = matrix[i][j];
                    peakCount++;
                }
            }
        }
        // Display peak columns
        for (int k = 0; k < peakCount; k++) {
            System.out.println("A (" + peakColumns[k][0] + "," + peakColumns[k][1] + ") = " + peakColumns[k][2]);
        }
    }

    private static boolean isMaxInRow(int[][] matrix, int row, int col) {
        int value = matrix[row][col];
        for (int j = 0; j < matrix[0].length; j++) {
            if (matrix[row][j] > value) {
                return false;
            }
        }
        return true;
    }

    private static boolean isMinInColumn(int[][] matrix, int row, int col) {
        int value = matrix[row][col];
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][col] < value) {
                return false;
            }
        }
        return true;
    }

    private static int[][] resizeArray(int[][] array) {
        int newSize = array.length * 2;
        int[][] newArray = new int[newSize][3];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter the number of rows in the matrix:");
        int rows = getValidIntInput(scanner);
        
        System.out.println("Enter the number of columns in the matrix:");
        int cols = getValidIntInput(scanner);

        int[][] matrix = new int[rows][cols];

        System.out.println("Enter the elements of each row separated by spaces:");
        for (int i = 0; i < rows; i++) {
            boolean validRow = false;
            while (!validRow) {
                System.out.println("Enter row " + (i + 1) + ":");
                String rowInput = scanner.nextLine();
                String[] elements = rowInput.split(" ");
                if (elements.length != cols) {
                    System.out.println("Invalid input. Please enter " + cols + " elements separated by spaces.");
                } else {
                    validRow = true;
                    for (int j = 0; j < cols; j++) {
                        try {
                            matrix[i][j] = Integer.parseInt(elements[j]);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter valid numbers.");
                            validRow = false;
                            break;
                        }
                    }
                }
            }
        }

        findPeakColumns(matrix);
    }
}
