import java.util.Scanner;

public class App {
    //i solemnly swear to not statically declare any variables up here
    public static void main(String[] args) throws Exception {
        final String os = System.getProperty("os.name");
        Scanner scanscan = new Scanner(System.in);
        System.out.println(".o88b.   .d88b.  d8b   db d8b   db d88888b  .o88b. d888888b     j88D\n"
                          +"d8P  Y8 .8P  Y8. 888o  88 888o  88 88'     d8P  Y8 `~~88~~'    j8~88\n"
                          + "8P      88    88 88V8o 88 88V8o 88 88ooooo 8P         88      j8' 88\n"
                          + "8b      88    88 88 V8o88 88 V8o88 88~~~~~ 8b         88      V88888D\n"
                          + "Y8b  d8 `8b  d8' 88  V888 88  V888 88.     Y8b  d8    88          88\n"
                          + "`Y88P'   `Y88P'  VP   V8P VP   V8P Y88888P  `Y88P'    YP          VP\n");
        switch (CLUtility.switchValidator("1) New Game\n2) LAN Multiplayer (port 1983) \n3) Other Gamemodes \n4) Exit", 3, scanscan)) {
            case 1:
                CLUtility.clear(os);
                char[][] board = new char[4][4];
                for (int r = 0; r < board.length; r++) {
                    for (int c = 0; c < board[0].length; c++) {
                        board[r][c] = ' ';
                    }
                }
                drawBoard(board);
            case 2:
                // CLUtility.clear(os);
            case 3:
                scanscan.close();
                System.exit(0);
        }
    }


    /**
     * @param board the game board, obviously
     */
    static void drawBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int h = 0; h < board[0].length; h++) {
                System.out.print("--");
            }
            System.out.println("-");
            for (int j = 0; j < board[0].length; j++) {
                System.out.print("|" + board[i][j]);
            }
            System.out.println("|");
        }
        for (int r = 0; r < board[0].length; r++) {
            System.out.print("--");
        }
        System.out.println("-");
    }

}