import java.io.IOException;
import java.util.Scanner;

public class CLUtility {

    //the CL stands for command line :)
    //you liked my code organization so i moved my common static methods to this utility class to make it *even cleaner*

    //private constructor so u dont end up trying to instantiate this
    //srry if this is a little too object oriented but i already got taught creating classes so i just cant help myself
    private CLUtility() {}

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
                if (inty <= bound && inty > -1)
                    return inty;
                System.out.println("Invalid input.\n"+prompt);
            } catch(NumberFormatException ne) {
                System.out.println("Invalid input.\n"+prompt);
            }
        }
    }

    /**
     * @param prompt String that prompts the user to enter an integer input
     * @param bound Lowest value that can be inputted
     * @param scanscan The scanner that will be used (duh)
     * @return valid input for switch statement
     */
    //the evil twin of switch validator
    public static int waSwitchValidator(String prompt, int bound, Scanner scanscan) {
        System.out.println(prompt);
        while(true){
            try {
                int inty = Integer.parseInt(scanscan.next());
                if (inty >= bound)
                    return inty;
                System.out.println("Invalid input.\n"+prompt);
            } catch(NumberFormatException ne) {
                System.out.println("Invalid input.\n"+prompt);
            }
        }
    }
    //overloading
    //exclusive with bound
    public static int waSwitchValidator(String prompt, int upper, int lower, Scanner scanscan) {
        System.out.println(prompt);
        while(true){
            try {
                int inty = Integer.parseInt(scanscan.next());
                if (inty >= lower && inty <= upper)
                    return inty;
                System.out.println("Invalid input.\n"+prompt);
            } catch(NumberFormatException ne) {
                System.out.println("Invalid input.\n"+prompt);
            }
        }
    }

    /**
     * @param os that string that we grab at the start of the runtime
     */
    public static void clear() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            System.out.println("Clear failed :( " + e);
        }  
    }

}
