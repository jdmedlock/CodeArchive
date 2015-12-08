package com.relaxedcomplexity;

/**
 * The Eight Queens problem is a classic computer science exercise
 * that solves the following problem. Given a chess board and eight
 * queens the goal is to determine how many ways can the queens be 
 * arranged on the board so that no two queens can attack each other.
 * <p>
 * In the game of chess the board contains eight rows and eight 
 * columns. Queens can move any number of spaces horizontally, 
 * vertically, or diagonally across the board. With respect to this 
 * problem the attributes of board and the Queen mean that no two 
 * queens may occupy the same row, column, or diagonal.
 * <p>
 * Some refinements to make the process more efficient are:
 * <ul>
 * <li>The state of the board is maintained as an 8x8 matrix where
 *     each position may have one of the values:
 *       <ul>
 *       <li>Unoccupied: false
 *       <li>Occupied:   true
 *       </ul>
 * <li>Before a Queen can be placed on the board the following conditions
 *     must be met:
 *       <ul>
 *       <li>The positions row must not already contain a Queen (see #2)
 *       <li>The positions column must not already contain a Queen (see #2)
 *       <li>The diagonal(s) must not already contain a Queen. 
 *       </ul>
 * <li>When a solution is found the chessboard will be printed as a line
 *     matrix showing where the eight queens have been placed.
 * </ul>
 * @author Jim Medlock
 *
 */
public class EightQueens {

	private static final int		NROWS = 8;
	private static final int		NCOLS = 8;
	private static final boolean	UNOCCUPIED = false;
	private static final boolean	OCCUPIED = true;
	
	private static boolean[][]	chessBoard = new boolean[NROWS][NCOLS];
	
	/**
	 * Initialize Board
	 * 
	 * Initialize the variables prior to the start of each pass to reset
	 * them from the ending state of the prior pass
	 */
	public static void initializeBoard() {
		for (int r=0; r<NROWS; r++) {
			for (int c=0; c<NCOLS; c++) {
				chessBoard[r][c] = UNOCCUPIED;
			}
		}
	}
	
	/**
	 * Examine a row to determine if it contains a board position a queen
	 * can be moved onto.
	 * 
	 * @param rowPos The row to start the calculation from
	 * @return
	 */
	public static boolean solveByRow(int rowPos) {
		if(rowPos >= NROWS) {
			return true;
	    }
	    for(int colPos = 0; colPos < NCOLS; colPos++) {
			if(testPosition(rowPos, colPos)) {
				chessBoard[rowPos][colPos] = OCCUPIED;
				if(!solveByRow(rowPos+1)) {
					chessBoard[rowPos][colPos] = UNOCCUPIED;
				} else {
					return true;
				}
			}
	    }
	    return false;
	}
	
	/**
	 * Test to determine if a Queen can be placed at the current position on the
	 * chess board. Since we are moving forward through the board row by row it is
	 * only necessary to check the positions we've already passed over. 
	 * <p>
	 * The chess board is an 8x8 set positions on which Queens may be placed with 
	 * a coordinate system defined as:
	 * {@code 
	 *    Column #/ 0    1    2    3    4    5    6    7
	 *      Row #  ---  ---  ---  ---  ---  ---  ---  ---
	 *       0     0,0  0,1  0,2  0,3  0,4  0,5  0,6  0,7
	 *       1     1,0  1,1  1,2  1,3  1,4  1,5  1,6  1,7
	 *       2     2,0  2,1  2,2  2,3  2,4  2,5  2,6  2,7
	 *       3     3,0  3,1  3,2  3,3  3,4  3,5  3,6  3,7
	 *       4     4,0  4,1  4,2  4,3  4,4  4,5  4,6  4,7
	 *       5     5,0  5,1  5,2  5,3  5,4  5,5  5,6  5,7
	 *       6     6,0  6,1  6,2  6,3  6,4  6,5  6,6  6,7
	 *       7     7,0  7,1  7,2  7,3  7,4  7,5  7,6  7,7
	 * }
	 * <p>
	 * Before a Queen can be placed on the board the following conditions
         * must be met:
         *   <ul>
         *   <li>The position must not already be occupied by a Queen
         *   <li>The row the position lies in must not already contain a Queen 
         *   <li>The column the position lies in must not already contain a Queen
         *   <li>The diagonal(s) must not already contain a Queen. For a given 
         *       position there are two diagonals which move through the
         *       current board position.
         *         <ul>
         *         <li>major diagonal: the line from left to right
         *         <li>minor diagonal: the line from right to left
     	 *         </ul>
         *   </ul>
	 * 
	 * @param startRow Row number of the position coordinate to test
	 * @param startCol Column number of the position coordinate to test
	 */
	public static boolean testPosition(int startRow, int startCol) {
		int currRow, currCol;

		// Check to see if any of the rows for this column position
		// are occupied
	    for (currRow = 0; currRow < startRow; currRow++) {
	      if(chessBoard[currRow][startCol] == OCCUPIED) {
	        return false;
	      }
	    }
	    
	    // Backtrack across the major diagonal to see if it
	    // is already occupied.
	    currRow = startRow - 1;
	    currCol = startCol - 1;
	    while((currRow >= 0) && (currCol >= 0))
	      if(chessBoard[currRow--][currCol--] == OCCUPIED) {
	    	  return false;
	      }

	    // Backtrack across the minor diagonal to see if it
	    // is already occupied.
	    currRow = startRow - 1;
	    currCol = startCol + 1;
	    while((currRow >= 0) && (currCol < NCOLS))
	      if(chessBoard[currRow--][currCol++] == OCCUPIED) {
	    	  return false;
	      }
	    return true;
	}
	
	/**
	 * Print the current solution
	 * 
	 * @param solutionNo The solution number to print
	 */
	public static void printBoard() {
		System.out.println("\nChess board w/Solution:");
		System.out.println("----------------------\n");
		System.out.print("Column #: ");
		for (int c=0; c<NCOLS; c++) {
			System.out.printf(" %1d ", c);
		}
		for (int r=0; r<NROWS; r++) {
			System.out.printf("\nRow #%1d:   ", r);
			for (int c=0; c<NCOLS; c++) {
				if (chessBoard[r][c]) {
					System.out.print(" Q ");
				} else {
					System.out.print(" _ ");
				}
			}
		}
		System.out.println();
	}
	
	/**
	 * The main function is the driver for the process of finding solutions
	 * to the Eight Queens problem. 
	 * 
	 * @param args An array of parameters to be used by the program. However,
	 * this are not necessary for this program and are ignored.
	 */
	public static void main(String[] args) {
		initializeBoard();
		solveByRow(0);
		printBoard();
	}
}
