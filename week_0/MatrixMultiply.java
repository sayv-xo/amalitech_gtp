import java.util.Scanner;

class MatrixMultiply
{
	public static final Scanner SC = new Scanner(System.in);

    /**
     * main: main function
     * @param args: unused
     */
	public static void main(String[] args)
	{
        System.out.println("Matrix A:\n");

        int[] dimensionsA = getDimensions();
        int rowA = dimensionsA[0];
        int colA = dimensionsA[1];
        int[][] matrixA = getMatrix(rowA, colA);

        System.out.println("\nMatrix B:\n");

        int[] dimensionsB = getDimensions();
        int rowB = dimensionsB[0];
        int colB = dimensionsB[1];
        int[][] matrixB = getMatrix(rowB, colB);

        System.out.println("\nMultiplying Matrix A and Matrix B");

        int[][] resMatrix = mulMatrix(matrixA, matrixB);
        System.out.println("Matrix C:\n");
        
        try {
            printMatrix(resMatrix);
        } catch (Exception e) {
            System.out.println("Dimensions mismatch: Cannot multiply matrices");
        }
	}
    
    /**
     * getDimensions: get dimensions of the matrix
     * @param: None
     * Return - array of dimensions
     */
	public static int[] getDimensions()
	{
		int[] dimensions = new int[2];

		// Loop to get dimensions. End only when it's right
		while (true)
		{
            System.out.println("Please enter dimensions of matrix "
                               + "(row column), sperated by commas or spaces");
			// Split input by comma or whitespaces
			String[] input = SC.nextLine().split("[,\\s]+");

			if (input.length !=2)
			{
				System.out.println("Invalid number of dimensions"
						+ "Please input exactly two numbers\n");
				continue;
			}

			try {
				dimensions[0] = Integer.parseInt(input[0]);
				dimensions[1] = Integer.parseInt(input[1]);
				if (dimensions[0] == 0 || dimensions[1] == 0)
				{
					System.out.println("Dimesion cannot be zero");
					continue;
				}
				return dimensions;
			} catch (NumberFormatException e) {
            System.out.println("Invalid input. Dimensions must be numbers.");
			}
		}
    }

    /**
     * getMatrix - build the 2D matrix
     * @param rows: row of matrix
     * @param cols: column of matrix 
     * Return: 2D matrix
     */
    public static int[][] getMatrix(int rows, int cols)
    {
        int[][] matrix = new int[rows][cols];
        int currentRow = 0;
        System.out.println("\nEnter matrix elements row by row, separated by commas or spaces:");

        while (currentRow < rows)
        {
            System.out.printf("\nEnter %d elements for Row %d:\n", cols, currentRow + 1);
            String[] elements = SC.nextLine().trim().split("[,\\s]+");

            if (elements.length != cols)
            {
                System.out.println("Invalid input: Each row must have exactly " + cols + " elements.");
                continue;
            }

            try {
                for (int i = 0; i < cols; i++) {
                    matrix[currentRow][i] = Integer.parseInt(elements[i]);
                }
                currentRow++;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: All elements must be numbers.");
            }
        }
        return matrix;
    }

    /**
     * printMatrix - print a 2D matrix
     * @param matrix: the matrix to print
     * Return: None
     */
    public static void printMatrix(int[][] matrix)
    {
        for (int i = 0; i < matrix.length; i++)
        {
            System.out.print("| ");
            for (int j = 0; j < matrix[0].length; j++)
            {
                System.out.print(matrix[i][j] + " ");
                if (j == matrix[0].length - 1) System.out.println("|");
            }
        }
    }

    /**
     * mulMatrix: multiply two matrices
     * @param matrixA: first matrix
     * @param matrixB: second matrix
     * Return: null if cant multiply else result
     */
    public static int[][] mulMatrix(int[][] matrixA, int[][]matrixB)
    {
        if (matrixA[0].length != matrixB.length)
        {
            return null;
        }

        int[][] matrix = new int[matrixA.length][matrixB[0].length];
        for (int i = 0; i < matrixA.length; i++)
        {
            for (int j = 0; j < matrixB[0].length; j++)
            {
                for (int k = 0; k < matrixA[0].length; k++)
                {
                    matrix[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }
        return matrix;
    }
}
