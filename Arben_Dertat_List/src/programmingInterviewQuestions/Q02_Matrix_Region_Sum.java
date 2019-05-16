package programmingInterviewQuestions;

/*
http://www.ardendertat.com/2011/09/20/programming-interview-questions-2-matrix-region-sum/

Given a matrix of integers and coordinates of a rectangular region within the matrix, 
find the sum of numbers falling inside the rectangle. 

Our program will be called multiple times with different rectangular regions from the same matrix.
 */
public class Q02_Matrix_Region_Sum {

	public static void main(String[] args) {
		int[][] matrix = {
				{70,37,23,57,27,22,90,99,22,59},
				{47,63,33,1,42,46,6,70,98,93},
				{36,62,50,21,92,27,60,29,15,34},
				{53,3,88,45,57,39,83,81,79,56},
				{28,63,89,20,47,15,84,18,82,33},
				{26,87,11,76,79,5,94,55,73,51},
				{17,82,86,10,96,5,42,43,51,6},
				{44,76,51,4,15,99,52,11,70,89},
				{66,36,92,85,50,21,72,27,52,65},
				{60,0,67,37,59,14,33,13,36,36}};
		
		/*UpperLeft:  Point A
		  UpperRight: Point B
		  LowerLeft:  Point C
		  LowerRight: Point D*/
		
		int upperRow = 3;
		int lowerRow = 7;
		int leftCol = 3;
		int rightCol = 7;
		
		int[][] allSums = preComputeAllSums(matrix);
		
		int sum = matrixRegionSum(matrix, upperRow, lowerRow, leftCol, rightCol, allSums);
		
		System.out.println("Sum = " + sum);

	}
	
	/*
	 * Time: O(N*M)
	 * Space:O(N*M)
	 */
	public static int[][] preComputeAllSums(int[][] matrix) {
		int rows = matrix.length;
		int cols = matrix[0].length;
		
		int[][] allSums = new int[rows][cols];
		
		allSums[0][0] = matrix[0][0];
		
		for(int c=1; c<cols; c++) {//set sums for first ROW
			allSums[0][c] = allSums[0][c-1] + matrix[0][c];
		}
		
		for(int r=1; r<rows; r++) {//set sums for first COL
			allSums[r][0] = allSums[r-1][0] + matrix[r][0];
		}
		
		for(int r=1; r<rows; r++) {
			for(int c=1; c<cols; c++) {	
				allSums[r][c] = allSums[r][c-1] + allSums[r-1][c] + matrix[r][c] - allSums[r-1][c-1];
			}
		}
		
		printMatrix(allSums);
		
		return allSums;
	}

	/*
	 * Time: O(1)
	 * Space:O(1)
	 */
	public static int matrixRegionSum(int[][] matrix, int upperRow, int lowerRow, int leftCol, int rightCol, int[][] allSums) {
		int sumOA = allSums[upperRow-1][leftCol-1];
		int sumOB = allSums[upperRow-1][rightCol];
		int sumOC = allSums[lowerRow][leftCol-1];
		int sumOD = allSums[lowerRow][rightCol];

		return sumOD-sumOB-sumOC+sumOA;
	}

	private static void printMatrix(int[][] m) {
		for(int r=0; r<m.length; r++) {
			for(int c=0; c<m[0].length; c++) {
				System.out.print(" " + m[r][c]);
			}
			System.out.println();
		}

	}

}
