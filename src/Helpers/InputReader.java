package Helpers;

import java.util.Scanner;

/**
 * 
 * @author Francisco Moura (180221015), Tiago Farinha (180221011)
 * this class reads the user input
 */
public class InputReader {
    
    private final Scanner reader;

    /**
     * this constructor initializes the Scanner object so it can be used later
     * to read user input.
     */
    public InputReader() {
        reader = new Scanner(System.in);
    }

    /**
     * 
     * @param question is the message displayed before the input
     * this method formats the message that will be displayed before input.
     */
    private void showFormattedQuestion(String question) {
        if (question == null) {
            question = "";
        }
        question += ": ";
        System.out.print(question);
    }

    /**
     * 
     * @param question is the message that will be used in the 
     * showFormattedQuestion() method.
     * @return returns the number from the input.
     */
    public int getIntegerNumber(String question) {
        showFormattedQuestion(question);

        while (!reader.hasNextInt()) {
            reader.nextLine();
            showFormattedQuestion(question);
        }
        int number = reader.nextInt();
        reader.nextLine();
        return number;
    }

    /**
     * 
     * @param question is the message that will be used in the 
     * showFormattedQuestion() method.
     * @return returns the text (String) from the input.
     */
    public String getText(String question) {
        showFormattedQuestion(question);

        return reader.nextLine();
    }
}
