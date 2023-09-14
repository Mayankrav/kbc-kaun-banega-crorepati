import java.util.*;
import java.io.*;
import static java.lang.System.out;

class Lifeline {
    private Random rand;
    private ArrayList<String> lifelines;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    Lifeline() {
        rand = new Random();
        lifelines = new ArrayList<>(
                Arrays.asList("50-50", "Audience Poll", "Double Dip"));
    }

    public void display() {
        for (String lifeline : lifelines)
            out.println(lifeline);
    }

    public void use(Question question) throws IOException {
        display();
        String lifeline = br.readLine();
        if (!lifelines.contains(lifeline)) {
            out.println("Lifeline already used!");
            return;
        }
        if (lifeline.equals("50-50")) {
            while (question.options.size() > 2) {
                int i = rand.nextInt(0, question.options.size());
                if (!question.options.get(i).startsWith(question.answer))
                    question.options.remove(i);
            }
            lifelines.remove(lifeline);
        }
    }
}