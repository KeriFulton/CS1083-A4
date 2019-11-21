
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 *
 * @author T El-Shanta
 */
public class PlayGame {

    /**
     * @param args the command line arguments
     */
    static Scanner scan  = new Scanner(System.in);
    static int player = 1, size;

    public static void main(String[] args) {

        int gameOver;
        size = inputSize();
	if (size > -1) {
	    GameBoard gameBoard = new GameBoard(size);

            gameBoard.drawGameBoard();

            do {
            	gameOver = play(gameBoard);
            	if (gameOver == 0) {
                    gameBoard.drawGameBoard();
                    gameOver = evaluateGame(gameBoard);
            	}
            	if (gameOver == 0) {
                    if (player == 1)
                    	player = 2;
                    else
                    	player = 1;
            	}
            	else {
                    System.out.println();
                    switch (gameOver) {
                    	case 1:
                        	System.out.println("Game Over. Player (" + player + ") wins!");
                        	break;
                    	case 2:
                        	System.out.println("Game Over. It's a draw.");
                        	break;
	                case 3:
        	                System.out.println("Player (" + player + ") ended the game.");
                		break;
	                case 4:
        	                System.out.println("An error caused the game to terminate.");
                		break;
	                default:
        	                break;
                    }
                    System.out.println();
            	}

             } while (gameOver == 0);
         }

    }



    private static int inputSize() {
        int size = 0;
        System.out.println();
        do {
            System.out.print("Enter game board size (odd number between 3 and 21), or -1 to exit: ");
	    try {
	         size = Integer.parseInt(scan.nextLine());
        	 if (size == -1)
                     break;
	         if (size < 3 || size > 21 || (size % 2 == 0))
        	     System.out.println("Invalid game board size entered!");
	    } catch (InputMismatchException | NumberFormatException e) {
		     System.out.println("Invalid input, try again...");
	    }
        } while (size < 3 || size > 21 || (size % 2 == 0));

        return size;

    }



    private static int play(GameBoard gameBoard) {
        int row = 0, col = 0;
        System.out.println();
        System.out.println("The turn now is for player (" + player + ").");

        while (true) {
            System.out.println();
            do {
                System.out.print("Enter row, or -1 to exit: ");
		try {
	                row = Integer.parseInt(scan.nextLine());
        	        if (row == -1)
                	    break;
	                if (row < 1 || row > size)
        	            System.out.println("Invalid row entered!");
		} catch (InputMismatchException | NumberFormatException e) {
			System.out.println("Invalid input, try again...");
		}
            } while (row < 1 || row > size);

            if (row == -1)
                break;
            else {
                do {
                    System.out.print("Enter column, or -1 to exit: ");
		    try {
	                    col = Integer.parseInt(scan.nextLine());
        	            if (col == -1)
                	        break;
	                    if (col < 1 || col > size)
        	                System.out.println("Invalid column entered!");
		    } catch (InputMismatchException | NumberFormatException e) {
			    System.out.println("Invalid input, try again...");
		    }
                } while (col < 1 || col > size);

                if (col == -1)
                    break;
                else {
                    if (gameBoard.setGamePiece(row-1, col-1))
                        break;
                }
            }
        }

        if (row < 0 || col < 0) {
            return 3;
        }
        else
            return 0;

    }


    public static int evaluateGame(GameBoard gameBoard) {
	try {
        	//return EvaluateItr.evaluate(gameBoard.getBoard());
		return EvaluateRec.evaluate(gameBoard.getBoard());
	} catch(InvalidGameBoardException exp) {
		System.out.println("\nProblem with the game board..." + exp.getMessage());
		return 4;
	}

    }

}
