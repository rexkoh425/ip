package thethinker.parser;

import thethinker.exceptions.FormattingException;
import java.util.Scanner;

/**
 * Handles the parsing of input other than to-do , deadline , event and date related parsing.
 * Parsing of To-do , deadline , event and date are done by their respective parsers.
 */
public class UserInputParser {

    public static String userInput;
    public static Scanner scanner = new Scanner(System.in);
    public static final String GET_FORMAT = "Please follow format : get [dd/mm/yyyy]";
    public static final String FIND_FORMAT = "Please follow format : find [keyword]";
    public static final int LENGTH_OF_FIND = 4;

    public static String getUserInput() {
        userInput = scanner.nextLine();
        userInput = userInput.trim();
        return userInput;
    }

    public static String parseUserAction() {
        String[] wordsInUserInput = userInput.split(" ");
        return wordsInUserInput[0];
    }

    /**
     * Obtains task number after inputting commands that require task number.
     *
     * @throws NumberFormatException If task number string provided is not a number.
     * @throws FormattingException If no task or multiple task number is provided.
     */
    public static int parseNumberAfterTask(String task) throws NumberFormatException , FormattingException {

        int indexOfTask = userInput.toLowerCase().indexOf(task);
        String taskNumber = userInput.substring(indexOfTask + task.length()).trim();
        String[] parsedInputs = taskNumber.split(" ");

        if (taskNumber.isEmpty()) {
            throw new FormattingException("Task number is not indicated. Please follow format : mark/unmark [number]");
        }

        if (parsedInputs.length >= 2) {
            throw new FormattingException("Multiple tasks to mark is indicated in a single command. "
                    + "Please mark them one by one.");
        }

        return Integer.parseInt(taskNumber);
    }

    /**
     * Extracts single word from user input after "find".
     *
     * @return keyword after removing space on the side.
     * @throws FormattingException If no keyword is provided or keyword is more than one word.
     */
    public static String parseKeywordAfterFind() throws FormattingException {

        int indexOfTask = userInput.toLowerCase().indexOf("find");
        String keyword = userInput.substring(indexOfTask + LENGTH_OF_FIND).trim();
        String[] parsedInputs = keyword.split(" ");

        if (keyword.isEmpty()) {
            throw new FormattingException("Keyword is missing. " + FIND_FORMAT);
        }

        if (parsedInputs.length >= 2) {
            throw new FormattingException("More than one word is provided. " + FIND_FORMAT);
        }

        return keyword;
    }
}
