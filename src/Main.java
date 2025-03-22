
import java.util.*;

public class Main {

    // List of categories and their words
    static Map<String, List<String>> categories = new HashMap<>();

    public static void main(String[] args) {
        initializeCategories();

        // Display welcome message and instructions
        System.out.println("Welcome to the Scramble Game!");
        System.out.println("There are 5 categories you can choose from:");

        displayCategories();

//         Ask user to choose category
        Scanner scanner = new Scanner(System.in);
        String selectedCategory = getCategoryName(scanner);
        System.out.println("You chose: " + selectedCategory);

        // Start the game with the chosen category
        startTask2(selectedCategory);

    }

    // Method to initialize categories with words
    private static void initializeCategories() {
        categories.put("Animals", Arrays.asList(
                "lion", "tiger", "elephant", "zebra", "giraffe", "kangaroo", "panda", "koala", "hippopotamus", "bear",
                "rabbit", "dog", "cat", "horse", "monkey", "parrot", "eagle", "owl", "snake", "shark",
                "whale", "dolphin", "deer", "fox", "wolf", "cheetah", "crocodile", "bat", "leopard", "rhinoceros", "hippo"
        ));

        categories.put("Fruits", Arrays.asList(
                "apple", "banana", "cherry", "grape", "orange", "mango", "pear", "peach", "kiwi", "lemon",
                "lime", "apricot", "plum", "watermelon", "pineapple", "blueberry", "blackberry", "strawberry", "melon", "papaya",
                "nectarine", "fig", "coconut", "date", "pomegranate", "carrot", "apricot", "tangerine", "cantaloupe", "guava", "dragonfruit"
        ));

        categories.put("Colors", Arrays.asList(
                "red", "blue", "green", "yellow", "purple", "orange", "pink", "brown", "black", "white",
                "gray", "violet", "indigo", "magenta", "turquoise", "beige", "lavender", "maroon", "cyan", "teal",
                "aqua", "gold", "silver", "bronze", "charcoal", "peach", "plum", "emerald", "rose", "pearl", "amber"
        ));

        categories.put("Countries", Arrays.asList(
                "USA", "Canada", "Germany", "France", "Italy", "Spain", "Japan", "Australia", "China", "India",
                "Brazil", "Russia", "South Korea", "Mexico", "UK", "Argentina", "South Africa", "Sweden", "Norway", "Finland",
                "New Zealand", "Netherlands", "Belgium", "Portugal", "Egypt", "Turkey", "Greece", "Switzerland", "Denmark", "Ireland", "Austria"
        ));

        categories.put("Food", Arrays.asList(
                "pizza", "burger", "sushi", "pasta", "sandwich", "tacos", "salad", "soup", "fries", "noodles",
                "rice", "steak", "chicken", "fish", "pancakes", "waffles", "croissant", "muffin", "brownie", "cake",
                "ice cream", "cheeseburger", "spaghetti", "lasagna", "dumplings", "ramen", "curry", "hot dog", "burrito", "sushi roll"
        ));
    }

    // Method to display the categories
    private static void displayCategories() {
        int count = 1;
        for (String category : categories.keySet()) {
            System.out.println(count + ". " + category);
            count++;
        }
    }

    // Method to get the name of the category based on the choice
    private static String getCategoryName(Scanner scanner) {
        String[] categoryNames = categories.keySet().toArray(new String[0]);
        int choice;

        while (true) { // Loop until valid input
            System.out.print("Please choose a category by typing its number (1-5): ");

            // Check if input is an integer
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                // validate range (1-5)
                if (choice >= 1 && choice <= 5) {
                    return categoryNames[choice - 1];
                }
            } else {
                scanner.nextLine(); // Consume invalid input
            }

            // Error message for invalid input
            System.out.println("Invalid Category. Please enter a number between 1 and 5.");
        }
    }


    // This method will proceed to Task 2 (word scramble and guess game)
    private static void startTask2(String category) {
        List<String> words = categories.get(category);
        Scanner scanner = new Scanner(System.in);

        int correct_word = 0;
        Random random = new Random();

        // Loop for each word in the chosen category
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(random.nextInt(words.size()));
            // Scramble the word
            String scrambledWord = scrambleWord(word);

            System.out.println("\nGuess the word: " + scrambledWord);

            // Provide the player with 3 attempts to guess
            int attempts = 3;

            while (attempts > 0) {
                System.out.print("Your guess (type 'g' to give up): ");
                String guess = scanner.nextLine().toLowerCase().strip();

                if (guess.equalsIgnoreCase(word)) {
                    correct_word++;
                    System.out.println("Correct! The word was: " + word);
                    break; // exit the loop if the guess is correct
                }else if(guess.equalsIgnoreCase("g")){
                    System.out.println("You have given up. The correct word was: " + word + ". Thank you for playing.");
                    break;
                }else {
                    attempts--;
                    if (attempts > 0) {
                        System.out.println("Incorrect. You have " + attempts + " attempts left.");
                    } else {
                        System.out.println("The correct word was: " + word + ". Thank you for playing.");
                    }
                }
            }

            // Ask the player if they want to continue after each word
            String choice;
            while (true) { // Keep asking until user inputs 'y' or 'n'
                System.out.print("\nDo you want to continue playing? (y=yes, n=no): ");
                choice = scanner.nextLine().toLowerCase().strip();

                if (choice.equals("y") || choice.equals("n")) {
                    break; // Exit loop if the input is valid
                } else {
                    System.out.println("Invalid choice. Please type 'y' or 'n'.");
                }
            }

            if (choice.equals("n")) {
                System.out.println("Thanks for playing! You have unscrambled " + correct_word + " words.");
                break; // Exit if the player chooses not to continue
            }


        }
    }

    // Method to scramble the word
    private static String scrambleWord(String word) {
        List<Character> characters = new ArrayList<>();
        for (char c : word.toCharArray()) {
            characters.add(c);
        }
        Collections.shuffle(characters);
        StringBuilder scrambled = new StringBuilder();
        for (char c : characters) {
            scrambled.append(c);
        }
        return scrambled.toString();
    }
}