package com.relaxedcomplexity;

/**
 * The Eight Queens problem is a classic computer science exercise
 * that solves the following problem. Given a chess board and eight
 * queens the goal is to determine how many ways can the queens be 
 * arranged on the board so that no two queens can attack each other.
 * <p>
 * Remember that in the game of chess, queens can move any number
 * of spaces horizontally, vertically, or diagonally across the 
 * board. A chess board contains eight rows and eight columns. With 
 * respect to this problem the attributes of board and the Queen mean
 * that no two queens may occupy the same row, column, or diagonal.
 * <p>
 * The algorithm I've developed to solve this is to run 64 passes over 
 * the chess board starting at position (1,1) and then walk through 
 * each position and place a Queen only if doing so would not allow the
 * game piece to be captured by a Queen that is already on the board.
 * <p>
 * A pass is considered complete when eight queens have been successfully
 * placed on the chess board or when the last position on the board (8,8)
 * has been examined. If the board contains a solution, i.e. eight queens
 * were successfully placed on it the solution will be printed.
 * <p>
 * Some refinements to make the process more efficient are:
 * <ul>
 * <li>The state of the board is maintained as an 8x8 matrix where
 *     each position may have one of the values:
 *       - Unoccupied: '-'
 *       - Occupied:   'Q'
 *       - Blocked:    '_'
 * <li>Two additional arrays of 8 cells each are maintained which
 *     maintain the state of each row and column of the board. This
 *     state indicates whether a Queen has been placed anywhere in 
 *     that row or column and removes the need to check each position
 *     in a row or column to determine if positioning a new Queen is
 *     blocked. These arrays may have one of two values:
 *       - Unoccupied: false
 *       - Occupied:   true
 * <li>Before a Queen can be placed on the board the following conditions
 *     must be met:
 *       - The positions row must not already contain a Queen (see #2)
 *       - The positions column must not already contain a Queen (see #2)
 *       - The diagonal(s) must not already contain a Queen. For a given 
 *         position the diagonals are defined as the positions with the
 *         coordinates:
 *           - (r+1,c-1) : Only rows 1 thru 7 and columns 2 thru 8
 *           - (r-1,c-1) : Only rows 2 thru 8 and columns 2 thru 8
 *           - (r-1,c+1) : Only rows 2 thru 8 and columns 1 thru 7
 *           - (r+1,c+1) : Only rows 1 thru 7 and columns 1 thru 7
 * <li>When a Queen is placed on the board: 
 *  	 - The state of the row and column it occupies is updated (see
 *         #2).
 *       - Each position on the board along the it's diagonal(s) are
 *         updated to indicate that they are blocked.
 * <li>When a solution is found the chessboard will be printed as a line
 *     containing the solution number and the positions (r,c) containing
 *     the eight queens.
 * </ul>
 * @author Jim Medlock
 *
 */
public class EightQueens {

	private static final int	NROWS = 8;
	private static final int	NCOLS = 8;
	private static final int	NQUEENS = 8;
	private static final int	NSOLS = 92;
	private static final char	UNOCCUPIED = '-';
	private static final char	OCCUPIED = 'Q';
	private static final char 	BLOCKED = '_';
	
	private static boolean		solutionFound = false;
	private static int			solutionCount = 0;
	private static boolean[]	rowOccupied = new boolean[NROWS];
	private static boolean[]	colOccupied = new boolean[NCOLS];
	private static char[][]		chessBoard = new char[NROWS][NCOLS];
	private static String[][]	solutions = new String[NSOLS][NQUEENS];
	
	/**
	 * Initialize Pass
	 * 
	 * Initialize the variables prior to the start of each pass to reset them
	 * from the ending state of the prior pass
	 */
	public static void initializePass() {
		solutionFound = false;
		for (int i=0; i<NROWS; i++) {
			rowOccupied[i] = false;
			colOccupied[i] = false;
			for (int j=0; j<NCOLS; j++) {
				chessBoard[i][j] = UNOCCUPIED;
			}
		}
	}
	
	/**
	 * Test to determine if a Queen can be placed at the current position on the
	 * chess board.
	 * 
	 * @param rowPos Row number of the position coordinate to test
	 * @param colPos Column number of the position coordinate to test
	 */
	public static boolean testPosition(int currRow, int currCol) {
		return false;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}
}
