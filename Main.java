import java.io.*;
import static java.lang.System.out;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        try {
            out.println("Do you want to start the Game?");
            char start = br.readLine().trim().charAt(0);
            if (start == 'y' || start == 'Y')
                new Game().start();
            out.println("Thanks For Playing");
        } catch (IOException e) {
            System.out.println("Unable to take input!");
        }
    }
}