import java.util.*;
import java.io.*;
import static java.lang.System.out;

class Game {
    public Player player;
    private Lifeline lifeline;
    private Random rand;
    public boolean GAME_OVER;
    Question currQues;
    ArrayList<Question> easy_qs, mid_qs, hard_qs;
    static int prize_list[] = { 0, 1, 2, 3, 5, 10, 20, 40, 80, 160,
            320, 640, 1250, 2500, 5000, 10000, 70000 };
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static boolean DEBUGMODE;

    public Game() {
        DEBUGMODE = GAME_OVER = false;
        lifeline = new Lifeline();
        player = new Player();
        rand = new Random();
    }

    void setup() throws IOException {
        easy_qs = new ArrayList<>() {
            {
                add(new Question("What is the Capital of India?",
                        new ArrayList<String>(Arrays.asList("A. Mumbai", "B. New Delhi", "C. Kolkata", "D. Chennai")),
                        "B"));
                add(new Question("Which is the Longest River in India?",
                        new ArrayList<String>(Arrays.asList("A. Indus River", "B. Brahmaputra River", "C. Ganga River",
                                "D. Godavari River")),
                        "A"));
            }
        };
        player.getDetails();
    }

    void start() throws IOException {
        setup();
        for (int level = 1; level <= 16; level++) {
            if (easy_qs.isEmpty()) {
                out.println("Times Up!!");
                break;
            }

            currQues = easy_qs.get(rand.nextInt(easy_qs.size()));
            easy_qs.remove(currQues);

            currQues.display(level);
            out.print("Enter your Answer: ");
            String ans = br.readLine().trim().toUpperCase();
            check(level, ans);
            // Get answer from User
            if (!GAME_OVER) {
                player.prize_index = level;
                out.println("Your Answer is Correct!!");
                out.println("Current Prize: " + prize_list[level] + "000");
            } else {
                out.println("Oh No, Your Answer is Wrong");
                out.println("You Won: " + prize_list[player.prize_index] + "000");
                break;
            }
        }
    }

    void check(int level, String answer) throws IOException {
        if (answer.equals("L")) {
            lifeline.use(currQues);
            currQues.display(level);
            out.println("Enter Your Answer: ");
            answer = br.readLine().trim().toUpperCase();
            check(level, answer);
        } else if (!answer.equals(currQues.answer)) {
            GAME_OVER = true;
        }
    }
}
