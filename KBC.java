import java.util.*;

class KBC {
    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();
    static int prize_list[] = { 0, 1, 2, 3, 5, 10, 20, 40, 80, 160,
            320, 640, 1250, 2500, 5000, 10000, 70000 };
    // Quesion Format "Question" "Options" "Answer"
    static String easy_qs[][] = { { "What is the Capital of India?",
            "A. Mumbai", "B. New Delhi", "C. Kolkata", "D. Chennai", "B" },
            { "Which is the Longest River in India?",
                    "A. Indus River", "B. Brahmaputra River", "C. Ganga River", "D. Godavari River", "A" } };

    static String med_qs[][] = { {} };

    static String hard_qs[][] = { {} };

    public static void main(String args[]) {
        System.out.println("Do you want to start the Game?");
        char start = sc.next().charAt(0);
        if (start == 'y' || start == 'Y') {
            Contestant player = new Contestant();
            game_setup(player);
            game_start(player);
            System.out.println("Thanks For Playing");
        } else
            System.out.println("Exiting the game");
    }

    public static void game_setup(Contestant player) {
        // Get Player Details
        System.out.println("\nWelcome to Kaun Banega Crorepati!!");
        System.out.println("Please Enter your Name:");
        player.name = sc.next();
        System.out.println("\nSo what will you do of the money you will earn Today?");
        player.use_of_money = sc.next();
        System.out.println("\nStarting with KBC");
    }

    public static void game_start(Contestant player) {
        // Create list for used qs
        ArrayList<String[]> used_qs = new ArrayList<>();
        // For 16 levels
        for (int level = 1; level <= 16; level++) {
            String question_data[] = easy_qs[random.nextInt(easy_qs.length)];
            // Check if particular list of questions is over
            if (qs_list_over(used_qs)) {
                System.out.println("Times Up!!"); // Simply Times Up
                break;
            }
            // If Question is already used skip it without changing level
            if (used_qs.contains(question_data)) {
                level--;
                continue;
            }
            used_qs.add(question_data);
            question_display(level, question_data);
            // Get answer in String to Compare
            String answer = Character.toString(sc.next().charAt(0));
            if (answer.equals(question_data[5])) {
                player.prize_index = level;
                System.out.println("Your Answer is Correct!!");
                System.out.println("Current Prize: " + prize_list[level] + "000");
            } else {
                System.out.println("Oh No, Your Answer is Wrong");
                System.out.println("You Won: " + prize_list[player.prize_index] + "000");
                break;
            }
        }
    }

    public static void question_display(int qno, String question_data[]) {
        String question_q = question_data[0]; // Get Question
        System.out.println("\n" + qno + ". " + question_q);
        for (int i = 1; i < question_data.length - 1; i++)
            System.out.println(question_data[i]); // Get and Print Options
        System.out.println();
        System.out.print("Your Answer: ");
    }

    public static boolean qs_list_over(ArrayList<String[]> used) {
        for (int i = 0; i < easy_qs.length; i++) {
            String str[] = easy_qs[i];
            if (!used.remove(str)) {
                return false;
            }
        }
        return true;
    }
}

class Contestant {
    String name;
    String use_of_money;
    int prize_index = 0;
}