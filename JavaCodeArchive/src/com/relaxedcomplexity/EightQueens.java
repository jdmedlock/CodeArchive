package com.relaxedcomplexity;

/**
 * The Eight Queens problem is a classic computer science exercise
 * that solves the following problem. Given a chess board and eight
 * queens the goal is to determine how many ways can the queens be 
 * arranged on the board so that no two queens can attack each other.
 * 
 * Remember that in the game of chess, queens can move any number
 * of spaces horizontally, vertically, or diagonally across the 
 * board. A chess board contains eight rows and eight columns. With 
 * respect to this problem the attributes of board and the Queen mean
 * that no two queens may occupy the same row, column, or diagonal.
 * 
 * The algorithm I've developed to solve this is to run eight 
 * passes over the chess board - one for each column. Starting at
 * position (1,n), where 'n' is the column number, walk through each
 * position to and place a Queen only if doing so would not allow the
 * game piece to be captured by a Queen that is already on the board.
 * 
 * Some refinements to make the process more efficient are:
 * 
 *  1. The state of the board is maintained as an 8x8 matrix where
 *     each position may have one of the values:
 *       - Unoccupied: '-'
 *       - Occupied:   'Q'
 *       - Blocked:    '_'
 *  2. Two additional arrays of 8 cells each are maintained which
 *     maintain the state of each row and column of the board. This
 *     state indicates whether a Queen has been placed anywhere in 
 *     that row or column and removes the need to check each position
 *     in a row or column to determine if positioning a new Queen is
 *     blocked. These arrays may have one of two values:
 *       - Unoccupied: '-'
 *       - Occupied:   'Q'
 *  3. Before a Queen can be placed on the board the following conditions
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
 *  4. When a Queen is placed on the board: 
 *  	 - The state of the row and column it occupies is updated (see
 *         #2).
 *       - Each position on the board along the it's diagonal(s) are
 *         updated to indicate that they are blocked.
 * 
 * @author Jim Medlock
 *
 */
public class EightQueens {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}
}
