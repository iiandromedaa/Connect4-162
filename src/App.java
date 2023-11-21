import java.util.Scanner;

public class App {
    //i solemnly swear to not statically declare any variables up here

    //NOTE TO SELF!!!!! DO NOT FORGET
    //board.length = # ROWS ^v
    //board[0].length = # COLUMNS < >
    public static void main(String[] args) throws Exception {
        Scanner scanscan = new Scanner(System.in);
        char[][] board;
        System.out.println(".o88b.   .d88b.  d8b   db d8b   db d88888b  .o88b. d888888b     j88D\n"
                         + "d8P  Y8 .8P  Y8. 888o  88 888o  88 88'     d8P  Y8 `~~88~~'    j8~88\n"
                         + "8P      88    88 88V8o 88 88V8o 88 88ooooo 8P         88      j8' 88\n"
                         + "8b      88    88 88 V8o88 88 V8o88 88~~~~~ 8b         88      V88888D\n"
                         + "Y8b  d8 `8b  d8' 88  V888 88  V888 88.     Y8b  d8    88          88\n"
                         + "`Y88P'   `Y88P'  VP   V8P VP   V8P Y88888P  `Y88P'    YP          VP\n");
        switch (CLUtility.switchValidator("1) New Game \n2) Custom Board \n3) Exit", 4, scanscan)) {
            //i was going to implement multiplayer through sockets but i dont have time for that
            //i do have a simple chat thing using sockets though at https://github.com/iiandromedaa/JavaP2Pchat
            case 1:
                CLUtility.clear();
                //6 rows 7 columns
                board = new char[6][7];
                for (int r = 0; r < board.length; r++) {
                    for (int c = 0; c < board[0].length; c++) {
                        board[r][c] = ' ';
                    }
                }
                char winner = gameLogic(board, 4, scanscan);
                System.out.println(winner + " has won the game!");
                System.exit(0);
            case 2:
                System.out.println("Some board dimensions may cause out of bounds exceptions, proceed with caution!");
                //waSwitchValidator is the evil version of switchValidator, hence the 'wa', like wario or waluigi
                int w = CLUtility.waSwitchValidator("Enter a board width (n>=4)", 4, scanscan);
                int win = CLUtility.waSwitchValidator("Enter number in a row to win (3<=n<=" + (w-1) + ")", w-1, 3, scanscan);
                board = new char[win + 2][w];
                for (int r = 0; r < board.length; r++) {
                    for (int c = 0; c < board[0].length; c++) {
                        board[r][c] = ' ';
                    }
                }
                char customwinner = gameLogic(board, win, scanscan);
                System.out.println(customwinner + " has won the game!");
                System.exit(0);
            case 3:
                scanscan.close();
                System.exit(0);
        }
    }


    /**
     * @param board the game board, obviously
     */
    //lots of nested for loops but it gets the job done
    static void drawBoard(char[][] board) {
        for (int c = 0; c < board[0].length; c++) {
            System.out.print(" " + c);
        }
        System.out.println();
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                System.out.print("--");
            }
            System.out.println("-");
            for (int j = 0; j < board[0].length; j++) {
                System.out.print("|" + board[r][j]);
            }
            System.out.println("|");
        }
        for (int c = 0; c < board[0].length; c++) {
            System.out.print("--");
        }
        System.out.println("-");
    }

    /**
     * @param board gameboard
     * @param column  
     * @return array length 2 containing info:
     * <li> return[0] indicates if the move is valid, 0 false 1 true of course
     * <li> return[1] is the row position of the valid move, so that the game method doesn't need to figure that out
     * @throws Exception
     */
    static int[] validator(char[][] board, int column) {
        if (column > board[0].length-1 || column < 0)
            //invalid move
            return new int[] {0,0};
        int count = -1;
        for (int r = 0; r < board.length; r++) {
            if (board[r][column] == ' ')
                count++;
            else
                break;
        }
        if (count <= -1) {
            return new int[] {0,count};
        }
        return new int[] {1,count};
    }

    /**
     * @param board the game board
     */
    //generalized method to check for x in a row
    static boolean winCheck(char[][] board, int checkFor, char player) {
        int count = 0;
        //check horizontals
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (board[r][c] == player)
                    count++;
                else   
                    count = 0;
                if (count >= checkFor)
                    return true;
            }
        }
        count = 0;
        //check verticals
        for (int c = 0; c < board[0].length; c++) {
            for (int r = 0; r < board.length; r++) {
                if (board[r][c] == player)
                    count++;
                else   
                    count = 0;
                if (count >= checkFor)
                    return true;
            }
        }
        //let the pain begin
        //CHECK DIAGONALS
        count = 0;
        //upwards diagonal /
        for (int c = 0; c < board[0].length - (checkFor-1); c++) {
            for (int r = checkFor-1; r < board.length; r++) {
                for (int i = 0; i < checkFor; i++) {
                    if (board[r - i][c + i] == player)
                        count++;
                    else
                        count = 0;
                    if (count >= checkFor)
                        return true;
                }
            }
        }
        //downwards diagonal \
        for (int c = 0; c < board[0].length - (checkFor-1); c++) {
            for (int r = 0; r < checkFor - 1; r++) {
                for (int i = 0; i < checkFor; i++) {
                    if (board[r + i][c + i] == player)
                        count++;
                    else
                        count = 0;
                    if (count >= checkFor)
                        return true;
                }
            }
        }
        return false;
    }

    /**
     * @param board
     * @param scanscan
     * @return row and column of a safe valid move
     */
    //hold the user hostage until they make a move that wont kill the program
    static int[] validatorValidator(char[][] board, Scanner scanscan) {
        int row[];
        while (true) {
            int clm = CLUtility.switchValidator("Select a column to drop a piece in", board[0].length, scanscan);
            row = validator(board, clm);
            if (row[0] == 1)
                return new int[] {row[1], clm};
        }
        
    }

    /**
     * @param board game board
     * @param checkFor how many in a row you need to win
     * @param scanscan the scanner i've been kicking around everywhere by reference
     */
    static char gameLogic(char[][] board, int checkFor, Scanner scanscan) {
        boolean game = true;
        drawBoard(board);
        while (game) {
            System.out.print("Red to move, ");
            int[] safeMove = validatorValidator(board, scanscan);
            board[safeMove[0]][safeMove[1]] = 'R';
            CLUtility.clear();
            drawBoard(board);
            if (winCheck(board, checkFor, 'R'))
                return 'R';
            //yellow turn
            System.out.print("Yellow to move, ");
            int[] safeMove2 = validatorValidator(board, scanscan);
            board[safeMove2[0]][safeMove2[1]] = 'Y';
            CLUtility.clear();
            drawBoard(board);
            if (winCheck(board, checkFor, 'Y'))
                return 'Y';
        }
        return ' ';
    }

}