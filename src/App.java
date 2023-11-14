import java.util.Scanner;

public class App {
    //i solemnly swear to not statically declare any variables up here

    //NOTE TO SELF!!!!! DO NOT FORGET
    //board.length = # ROWS
    //board[0].length = # COLUMNS
    public static void main(String[] args) throws Exception {
        final String os = System.getProperty("os.name");
        Scanner scanscan = new Scanner(System.in);
        char[][] board;
        System.out.println(".o88b.   .d88b.  d8b   db d8b   db d88888b  .o88b. d888888b     j88D\n"
                         + "d8P  Y8 .8P  Y8. 888o  88 888o  88 88'     d8P  Y8 `~~88~~'    j8~88\n"
                         + "8P      88    88 88V8o 88 88V8o 88 88ooooo 8P         88      j8' 88\n"
                         + "8b      88    88 88 V8o88 88 V8o88 88~~~~~ 8b         88      V88888D\n"
                         + "Y8b  d8 `8b  d8' 88  V888 88  V888 88.     Y8b  d8    88          88\n"
                         + "`Y88P'   `Y88P'  VP   V8P VP   V8P Y88888P  `Y88P'    YP          VP\n");
        switch (CLUtility.switchValidator("1) New Game\n2) LAN Multiplayer \n3) Custom Board \n4) Exit", 3, scanscan)) {
            case 1:
                CLUtility.clear(os);
                //6 rows 7 columns
                board = new char[6][7];
                for (int r = 0; r < board.length; r++) {
                    for (int c = 0; c < board[0].length; c++) {
                        board[r][c] = ' ';
                    }
                }
                drawBoard(board);
                System.out.println(validator(board, 4)[0]);
                System.out.println(validator(board, 4)[1]);
            case 2:
                // CLUtility.clear(os);
            case 3:
                System.out.println("Enter a board width (n>4)");
            case 4:
                scanscan.close();
                System.exit(0);
        }
    }


    /**
     * @param board the game board, obviously
     */
    //im pretty sure the time complexity on this is like worse than O(n^2)
    //but hey, as long as youre not playing connect 1000 this will run fine
    static void drawBoard(char[][] board) {
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
    static int[] validator(char[][] board, int column) throws Exception {
        if (column > board[0].length || column < 0)
            //invalid move
            throw new Exception();
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
     * @param board game board
     */
    static void gameLogic(char[][] board) {

    }

}