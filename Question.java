import java.util.*;
import static java.lang.System.out;

class Question {
    String question;
    ArrayList<String> options;
    String answer;

    Question(String question, ArrayList<String> options, String answer) {
        this.question = question;
        this.options = options;
        this.answer = answer;
    }

    void display(int qno) {
        out.println("\n" + qno + ". " + question);
        for (String option : options)
            out.println(option);
        out.println();
    }
}