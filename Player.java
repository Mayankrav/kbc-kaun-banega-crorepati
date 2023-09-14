import java.io.*;

class Player {
    public String name;
    public String use_of_money;
    int prize_index = 0;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // Get Player Details
    void getDetails() throws IOException {
        System.out.println("\nWelcome to Kaun Banega Crorepati!!");
        System.out.println("Please Enter your Name:");
        name = br.readLine();
        System.out.println("\nSo what will you do of the money you will earn Today?");
        use_of_money = br.readLine();
        System.out.println("\nStarting with KBC");

    }
}
