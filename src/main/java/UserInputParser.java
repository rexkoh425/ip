import java.util.Scanner;

public class UserInputParser {
    public static String userInput;
    public static final Scanner SCANNER = new Scanner(System.in);
    public static final int LENGTH_OF_TODO = 4;
    public static final int LENGTH_OF_EVENT = 5;
    public static final int LENGTH_OF_DEADLINE = 8;
    public static final int LENGTH_OF_SLASH_FROM = 5;
    public static final int LENGTH_OF_SLASH_TO = 3;
    public static final int LENGTH_OF_SLASH_BY = 3;

    public static Task parseTask(){

        String taskType = userInput.substring(0 , LENGTH_OF_TODO);
        String taskDescription = userInput.substring(LENGTH_OF_TODO).trim();
        char taskTypeChar = getCorrespondingCharForTaskType(taskType);
        return new Task(taskDescription , taskTypeChar);
    }

    public static Event parseEvent(){

        String remainingTaskDescription = userInput.substring(LENGTH_OF_EVENT).trim();
        int indexOfFirstSlash = remainingTaskDescription.indexOf("/from");
        String taskDescription = remainingTaskDescription.substring(0, indexOfFirstSlash).trim();
        remainingTaskDescription = remainingTaskDescription.substring(indexOfFirstSlash + LENGTH_OF_SLASH_FROM).trim();
        int indexOfSecondSlash = remainingTaskDescription.indexOf("/to");
        String startTime = remainingTaskDescription.substring(0, indexOfSecondSlash).trim();
        String endTime = remainingTaskDescription.substring(indexOfSecondSlash + LENGTH_OF_SLASH_TO).trim();
        return new Event(taskDescription , startTime, endTime);
    }

    public static Deadline parseDeadline(){

        String remainingTaskDescription = userInput.substring(LENGTH_OF_DEADLINE).trim();
        int indexOfSlash = remainingTaskDescription.indexOf("/by");
        String taskDescription = remainingTaskDescription.substring(0, indexOfSlash).trim();
        String deadline = remainingTaskDescription.substring(indexOfSlash + LENGTH_OF_SLASH_BY).trim();
        return new Deadline(taskDescription , deadline);
    }

    public static char getCorrespondingCharForTaskType(String type){
        switch(type){
        case "todo" :
            return 'T';
        case "event" :
            return 'E';
        case "deadline" :
            return 'D';
        default:
            return ' ';
        }
    }

    public static String parseUserAction(){
        String[] wordsInUserInput = userInput.split(" ");
        return wordsInUserInput[0];
    }

    public static String getUserInput(){
        userInput = SCANNER.nextLine();
        userInput = userInput.trim();
        return userInput;
    }

    public static int parseMarkAndUnmarkTask(){
        String numberToMark = userInput.split(" ")[1].trim();
        return Integer.parseInt(numberToMark);
    }
}
