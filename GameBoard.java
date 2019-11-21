
/**
 *
 * @author T El-Shanta
 */
public class GameBoard {

    private final char[][] board;

    private char currentPiece;

    public GameBoard(int size) {

	board = new char[size][size];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++)
                board[row][col] = ' ';
        }

        currentPiece = 'X';
    }


    public void drawGameBoard() {

        System.out.println();
	System.out.print("  ");
	for (int i = 1; i <= board[0].length; i++) {
	     if (i < 10)
		 System.out.print(" ");
             System.out.print("    " + i);
	}
        System.out.println("\n");
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (col == 0) {
		    if (row < 9)
			System.out.print(" ");
                    System.out.print((row + 1) + "   ");
		}
                if (col > 0 && col < (board[row].length))
                    System.out.print("|");
                System.out.print("  " + board[row][col] + "  ");
            }
            System.out.println();
            if (row < (board.length - 1)) {
		System.out.print("     ");
		for (int i = 0; i < board[row].length; i++) {
                     System.out.print("-----");
		     if (i < board[row].length - 1)
			 System.out.print("+");
		}
		System.out.println();
	    }
        }
    }


    public char getGamePiece(int row, int col) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            System.out.println("Invalid row/col combination!");
            return 'E';
        }
        else
            return board[row][col];
    }


    public boolean setGamePiece(int row, int col) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            System.out.println("Invalid row/col combination!");
            return false;
        } else {
            if (getGamePiece(row, col) == 'X' || getGamePiece(row, col) == 'O') {
                System.out.println("Chosen location already contains an '" + getGamePiece(row, col) + "'");
                return false;
            } else {
                board[row][col] = currentPiece;
                if (currentPiece == 'X')
                    currentPiece = 'O';
                else
                    currentPiece = 'X';

                return true;
            }
        }
    }


    public char[][] getBoard() {
	char[][] boardCopy = null;
	if (board.length > 0 && board[0].length > 0) {
		boardCopy = new char[board.length][board[0].length];
		for (int row = 0; row < board.length; row++) {
		     for (int column = 0; column < board[row].length; column++)
			  boardCopy[row][column] = board[row][column];
		}
	}

	return boardCopy;
    }

}
