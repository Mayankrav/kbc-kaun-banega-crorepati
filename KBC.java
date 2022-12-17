import java.util.*;

class KBC {
    static boolean DEBUGMODE = true;
    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();
    static int prize_list[] = { 0, 1, 2, 3, 5, 10, 20, 40, 80, 160,
            320, 640, 1250, 2500, 5000, 10000, 70000 };
    // Quesion Format "Question" "Options" "Answer"
    static ArrayList<QuestionData> easy_qs = new ArrayList<>() {
        {
            add(new QuestionData("What is the Capital of India?",
                    "A. Mumbai", "B. New Delhi", "C. Kolkata", "D. Chennai", "B"));
            add(new QuestionData("Which is the Longest River in India?",
                    "A. Indus River", "B. Brahmaputra River", "C. Ganga River", "D. Godavari River", "A"));
        }
    };

    public static void main(String args[]) {
        System.out.println("Do you want to start the Game?");
        char start = sc.next().charAt(0);
        if (start == 'y' || start == 'Y') {
            Contestant player = new Contestant();
            if (DEBUGMODE != true) {
                game_setup(player);
            }
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
        ArrayList<QuestionData> used_qs = new ArrayList<>();
        // For 16 levels
        for (int level = 1; level <= 16; level++) {
            QuestionData question_data = new QuestionData();
            question_data = easy_qs.get(random.nextInt(easy_qs.size()));
            // Check if particular list of questions is over
            if (qs_list_over(used_qs)) {
                System.out.println("Times Up!!"); // Simply Times Up
                break;
            }
            // If Question is already used, skip it without changing level
            if (used_qs.contains(question_data)) {
                level--;
                continue;
            }
            used_qs.add(question_data);
            question_display(level, question_data);
            // Get answer from User
            if (check_answer(player, level, question_data)) {
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

    public static void question_display(int qno, QuestionData question_data) {
        System.out.println("\n" + qno + ". " + question_data.question_q);
        for (String option : question_data.options)
            System.out.println(option); // Print Options to User
        System.out.println();
        System.out.print("Your Answer: ");
    }

    public static boolean qs_list_over(ArrayList<QuestionData> used) {
        for (int i = 0; i < easy_qs.size(); i++) {
            QuestionData qd = easy_qs.get(i);
            if (!used.contains(qd)) {
                return false;
            }
        }
        return true;
    }

    public static boolean check_answer(Contestant player, int level, QuestionData question_data) {
        String answer = sc.next().toUpperCase().substring(0, 1);
        if (answer.equals("L")) {
            player.lifeline.display();
            System.out.println("Enter Lifeline to Use: ");
            String lifeline_name = sc.next();
            player.lifeline.use(lifeline_name, question_data);
            question_display(level, question_data);
            return check_answer(player, level, question_data);
        } else if (answer.equals(question_data.answer)) {
            return true;
        } else {
            return false;
        }
    }
}

class Contestant {
    String name;
    String use_of_money;
    int prize_index = 0;
    Lifeline lifeline = new Lifeline();
}

class QuestionData {
    String question_q;
    ArrayList<String> options = new ArrayList<>();
    String answer;

    QuestionData() {
        question_q = "";
        answer = "";
    }

    QuestionData(String q, String opt1, String opt2, String opt3, String opt4, String ans) {
        question_q = q;
        options.add(opt1);
        options.add(opt2);
        options.add(opt3);
        options.add(opt4);
        answer = ans;
    }
}

class Lifeline {
    Random random = new Random();
    ArrayList<String> Lifelines = new ArrayList<>() {
        {
            add("50-50");
            add("Audience Poll");
            add("Double Dip");
        }
    };

    public void display() {
        for (String lifeline : Lifelines) {
            System.out.println(lifeline);
        }
    }

    public boolean use(String lifeline_name, QuestionData question_data) {
        if (!Lifelines.contains(lifeline_name)) {
            System.out.println("Lifeline already used!");
            return false;
        }
        if (lifeline_name.equals("50-50")) {
            while (question_data.options.size() > 2) {
                int random_index = random.nextInt(0, question_data.options.size());
                if (!question_data.options.get(random_index).startsWith(question_data.answer)) {
                    question_data.options.remove(random_index);
                }
            }
            Lifelines.remove(lifeline_name);
        }
        return true;
    }
}