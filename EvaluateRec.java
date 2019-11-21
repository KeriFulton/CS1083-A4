
/**
 *
 * @author T El-Shanta
*/
public class EvaluateRec{

    // Two dimensional array representing the game board.
    private static char[][] gameBoard;

    /**
     * The method that coordinates the game board evaluation process
     * by calling the several methods the perform the actual checking.
     @param char[][] - it accepts the two diamensional character array
                       game board as a parameter
     @return int - it returns an integer that represents the result of the evaluation,
                   where 0 means keep playing, 1 means there is a winner,
	           and 2 means the game is a draw.
    */
    public static int evaluate(char[][] board) throws InvalidGameBoardException {

 	if (board == null || (board.length != board[0].length) ||
	    board.length < 3 || (board.length % 2 == 0))
	    throw new InvalidGameBoardException("\nInvalid game board dimensions!\n" +
					"The number of rows must be an odd number " +
					"and equal to the number of columns.");

	gameBoard = board;

	//TODO - You need to implement the logic that calls the
	//	 methods that perform the RECURSIVE checking to
	//	 conclude whether to return 0, 1 or 2 to the
	//       evaluateGame method of the PlayGame class.

    /**These boolean variables check every possible outcome of winning directions for X and O respectively.*/
    boolean checkX = column('X', 0, board)  || row('X', 0, board) || diagonal('X', 0, 0, board) || diagonal2('X',board.length-1, 0, board);
    boolean check0 = column('O', 0, board)  || row('O', 0, board) || diagonal('O', 0, 0, board) || diagonal2('O',board.length-1, 0, board);

    /**if the boolean value is true, which means a win, it returns a 1, which means there is a winner.*/
    if(checkX || check0){
        return 1;
    }
    /** if the boolean value for the draw is true, it returns a 2, which means therehas been a draw*/
    if(draw(board, 0, 0)){
        return 2;
    }
    /** if none of these cases are true, it means that there is still game to play, and returns a 0*/
    return 0;



    }

    //TODO - Implement as many RECURSIVE methods as required
    //       to evaluate the game board and return the proper
    //       result to the evaluate method above.
    /**
     * This method evaluates the left-to-right diagonal direction to determine if it is a winner.\
     @param player - it accepts the player in which we are trying to find the winner.
     @param row - it accepts the row that is intialized at the start of the evaluation.
     @param col - it accepts the column that is intialized at the start of the evaluation.
     @param board - it accepts the two diamensional character array board
                    as a parameter
     @return boolean - returns a true or false value, true if there is a winner, false if
                        there is an 0 or an empty slot in its direction.
    */
    public static boolean diagonal(char player, int row, int col, char[][] board){

        if(row >= board.length || col >= board.length){
            return true;
        }
        if(player != board[row][col]){
            return false;
        }
        return diagonal(player, row+1, col+1, board);


    }
    /**
     * This method evaluates the right-to-left diagonal direction to determine if it is a winner.\
     @param player - it accepts the player in which we are trying to find the winner.
     @param row - it accepts the row that is intialized at the start of the evaluation.
     @param col - it accepts the column that is intialized at the start of the evaluation.
     @param board - it accepts the two diamensional character array board
                    as a parameter
     @return boolean - returns a true or false value, true if there is a winner, false if
                        there is an 0 or an empty slot in its direction.
    */
    public static boolean diagonal2(char player, int row, int col, char[][] board){
        if(col >= board.length || row < 0){
            return true;
        }
        if(player != board[row][col]){
            return false;
        }
        return diagonal2(player, row-1, col+1, board);


    }
    /**
     * This method evaluates a single column to determine if it is a winner.\
     @param player - it accepts the player in which we are trying to find the winner.
     @param row - it accepts the row that is intialized at the start of the evaluation.
     @param col - it accepts the column that is intialized at the start of the evaluation.
     @param board - it accepts the two diamensional character array board
                    as a parameter
     @return boolean - returns a true or false value, true if there is a winner, false if
                        there is an 0 or an empty slot in its direction.
    */
    public static boolean evaluateColumnWin(char player, int row, int col, char[][] board){
        if(row == board.length){
          return true;
        }
        if(player != board[row][col]){
            return false;
        }
        boolean result = evaluateColumnWin(player, row+1, col, board);
        return result;



    }
    /**
     * This method evaluates all of the columns to determine if it is a winner.\
     @param player - it accepts the player in which we are trying to find the winner.
     @param col - it accepts the column that is intialized at the start of the evaluation.
     @param board - it accepts the two diamensional character array board
                       as a parameter
     @return boolean - returns a true or false value, true if there is a winner, either for a single column or
                        any of the columns are winners,false if
                        there is an 0 or an empty slot in its direction.
    */
    public static boolean column(char player, int col, char[][] board){
        if(col >= board.length){
            return false;
        }
        boolean next = column(player, col+1, board);
        boolean check = evaluateColumnWin(player, 0, col, board);
        if(next){
            return next;
        }
        return next || check;

    }
    /**
     * This method evaluates a single row to determine if it is a winner.
     @param player - it accepts the player in which we are trying to find the winner.
     @param row - it accepts the row that is intialized at the start of the evaluation.
     @param col - it accepts the column that is intialized at the start of the evaluation.
     @param board - it accepts the two diamensional character array board
                       as a parameter
     @return boolean - returns a true or false value, true if there is a winner, false if
                        there is an 0 or an empty slot in its direction.
    */
    public static boolean evaluateRowWin(char player, int row, int col, char[][] board){
        if(col == board.length){
          return true;
        }
        if(player != board[row][col]){
            return false;
        }
        boolean result = evaluateRowWin(player, row, col+1, board);
        return result;

    }
    /**
     * This method evaluates all of the rows using evaluateRowWin
     @param player - it accepts the player in which we are trying to find the winner.
     @param row - it accepts the row that is intialized at the start of the evaluation.
     @param board - it accepts the two diamensional character array board
                       as a parameter
     @return boolean - returns a true or false value for either the result of the single row win, true
                        if there is a winner for any row, false if
                        there is an 0 or an empty slot in its direction.
    */
    public static boolean row(char player, int row, char[][] board){
         if(row >= board.length){
            return false;
        }
        boolean next = row(player, row +1, board);
        boolean check = evaluateRowWin(player, row, 0, board);
        if(next){
            return next;
        }
        return next || check;
    }
    /**
     * This method evaluates the board to determine if there was a draw.
     @param board - it accepts the two diamensional character array board
                    as a parameter
     @param row - it accepts the row that is intialized at the start of the evaluation.
     @param col - it accepts the column that is intialized at the start of the evaluation.
     @return boolean - returns a true or false value based on the checks for a draW in either direction.
    */
    public static boolean draw(char[][] board, int row, int col){
        if( row >= board.length || col >= board.length){
            return true;
        }
        if(board[row][col] == ' '){
            return false;
        }
        boolean down = draw(board, row + 1, col);
        boolean right = draw(board, row, col +1);
        return down && right;
    }


}
