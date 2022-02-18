import java.util.Arrays;
import java.util.Scanner;

public class Hangman {
    public static Scanner scan = new Scanner (System.in);

    public static String misses = "", randomlyGeneratedWord;
    public static String[][] hangmanArray;

    public static String[] words = {"ant", "baboon", "badger", "bat", "bear", "beaver", "camel",
    "cat", "clam", "cobra", "cougar", "coyote", "crow", "deer",
    "dog", "donkey", "duck", "eagle", "ferret", "fox", "frog", "goat",
    "goose", "hawk", "lion", "lizard", "llama", "mole", "monkey", "moose",
    "mouse", "mule", "newt", "otter", "owl", "panda", "parrot", "pigeon", 
    "python", "rabbit", "ram", "rat", "raven","rhino", "salmon", "seal",
    "shark", "sheep", "skunk", "sloth", "snake", "spider", "stork", "swan",
    "tiger", "toad", "trout", "turkey", "turtle", "weasel", "whale", "wolf",
    "wombat", "zebra"};

    /**
     * @param arr (Array)
     * @return (Array Indices)
     * Generates random number based on length of given array and returns string at the indices generated
     */
    public static String randomWord(String[] arr) { //pick random word from array
        int randomWordGenerator = (int) (Math.random()* arr.length);
        return arr[randomWordGenerator];
    }

    /**
     * @return (String)
     * Prints gallow state based on how many incorrect guesses there are
     */
    public static String showGallows() {
        switch (misses.length()) {
            case 0: return 
            "+---+\n" +
            "|   |\n" +
            "    |\n" +
            "    |\n" +
            "    |\n" +
            "    |\n" +
            "=========\n";
            case 1: return
            "+---+\n" +
            "|   |\n" +
            "O   |\n" +
            "    |\n" +
            "    |\n" +
            "    |\n" +
            "=========\n";
            case 2: return 
            "+---+\n" +
            "|   |\n" +
            "O   |\n" +
            "|   |\n" +
            "    |\n" +
            "    |\n" +
            "=========\n";
            case 3: return
            " +---+\n" +
            " |   |\n" +
            " O   |\n" +
            "/|   |\n" +
            "     |\n" +
            "     |\n" +
            " =========\n";
            case 4: return 
            " +---+\n" +
            " |   |\n" +
            " O   |\n" +
            "/|\\  |\n" + 
            "     |\n" +
            "     |\n" +
            " =========\n";
            case 5: return 
            " +---+\n" +
            " |   |\n" +
            " O   |\n" +
            "/|\\  |\n" +
            "/    |\n" +
            "     |\n" +
            " =========\n";
            case 6: return    
            " +---+\n" +
            " |   |\n" +
            " O   |\n" +
            "/|\\  |\n" + 
            "/ \\  |\n" +
            "     |\n" +
            " =========\n";
            default: return "error";
        }
    }

    /**
     * Prints placeholders for current word
     */
    public static void printPlaceholders() {
        System.out.print("Word: ");
        for (int i = 0; i < hangmanArray[0].length; i++) {
            System.out.print(hangmanArray[0][i] + " ");
        }
    }
    /**
     * Checks to see if the user has won
     * @return (boolean)
     */
    public static boolean checkwin() {
        for (int i = 0; i < hangmanArray[0].length; i++) {
            if (hangmanArray[0][i].equals(hangmanArray[1][i])) {
                continue;
            }
            else {
                return false;
            }
        }
        return true;
    }

    /**
     * Asks user to guess a letter within a-z, if they guess with incorrect input it asks them to guess again
     */
    public static void guess() {
        System.out.print("\nGuess:\t");

        String answer = scan.nextLine().toLowerCase();
        while (answer.length() > 1 || !answer.matches("[a-z]")) {
            System.out.println("\nGuess with one letter!");
            System.out.print("\nGuess:\t");
            answer = scan.nextLine().toLowerCase();
        }

        int counter = 0;

        for (int i = 0; i < hangmanArray[1].length; i++) {

            if (answer.equals(hangmanArray[1][i])) {

                hangmanArray[0][i] = answer;

                counter++;

            }
        }

        if (counter == 0 && !misses.contains(answer)) { //designed to catch input of already incorrect answer, as well as different casing
            misses += answer;
        }
    }


    /**
     * utilizes the random generated word to create an array and fill it with both the place holder and word
     */
    public static void setWordArray() {
        String word = randomWord(words); //find random word from list
        randomlyGeneratedWord = word;
        hangmanArray = new String[2][word.length()];//create array based off of word

        Arrays.fill(hangmanArray[0], "_"); //fill placeholder array

        for (int i = 0; i < word.length(); i++) {
            hangmanArray[1][i] = Character.toString(word.charAt(i)); //fill array with the word
        }

    }



    public static void main(String[] args) {
        setWordArray();
        while (true) {
            System.out.println(showGallows());
            printPlaceholders();
            System.out.println("\n\nMisses:\t" + misses);
            guess();
            if (checkwin() == true) {
                System.out.println(showGallows());
                printPlaceholders();
                System.out.println("\n\nMisses:\t" + misses);
                System.out.println("nice job, you won!");
                break;
            }
            if (misses.length() >= 6) {
                System.out.println(showGallows());
                printPlaceholders();
                System.out.println("\n\nMisses:\t" + misses);
                System.out.println("\nNice try! The word was '" + randomlyGeneratedWord + "'.");
                break;
            }
        }
    }

}





