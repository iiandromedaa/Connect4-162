import java.util.Scanner;

public class App {
    //i solemnly swear to not statically declare any objects up here
    public static void main(String[] args) throws Exception {
        Scanner scanscan = new Scanner(System.in);
        System.out.println("Connect 4");
    }

    /**
     * @param prompt String that prompts the user to enter an integer input
     * @param bound Highest value that can be inputted
     * @param scanscan The scanner that will be used (duh)
     * @return valid input for switch statement
     */
    public static int switchValidator(String prompt, int bound, Scanner scanscan) {
        System.out.println(prompt);
        while(true){
            try {
                int inty = Integer.parseInt(scanscan.next());
                if (inty > bound)
                    return inty;
                System.out.println("Invalid input.\n"+prompt);
            }
            catch(NumberFormatException ne) {
                System.out.println("Invalid input.\n"+prompt);
            }
        }
    }
}
