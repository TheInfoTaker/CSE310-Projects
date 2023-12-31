import java.io.*;
import java.util.*;

class Program {
    public static void main(String[] args) {
    // Basic variables
    Random random = new Random();
    int result = 0;
    String fileName = "previous_rolls.txt";

    Scanner scanner = new Scanner(System.in);

    // A menue display
    System.out.println("Welcome to the Random Number Generator!");
    while (true) {
        System.out.println("\nSelect an option:");
        System.out.println("1. Coin Flip");
        System.out.println("2. Three-Sided Dice");
        System.out.println("3. Six-Sided Dice");
        System.out.println("4. Twenty-Sided Dice");
        System.out.println("5. Load Previous Rolls");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");

        // Displays a result depending on what the user selected
        if (scanner.hasNextInt()) {
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // Coin flip (0 for heads, 1 for tails)
                    result = random.nextInt(2);
                    System.out.println(result == 0 ? "Heads" : "Tails");
                    break;
                case 2:
                    // Three-Sided Dice (1 to 3)
                    result = random.nextInt(3) + 1;
                    System.out.println("You rolled a " + result + "!");
                    break;
                case 3:
                    // Six-Sided Dice (1 to 6)
                    result = random.nextInt(6) + 1;
                    System.out.println("You rolled a " + result + "!");
                    break;
                case 4:
                    // Twenty-Sided Dice (1 to 20)
                    result = random.nextInt(20) + 1;
                    System.out.println("You rolled a " + result + "!");
                    break;
                case 5:
                    // Load previous rolls
                    loadPreviousRolls(fileName);
                    System.out.print("Press enter to continue:");
                    scanner.nextLine();
                    continue;
                case 6:
                    // Exit
                    System.out.println("Goodbye!");
                    return;
                default:
                    // Displays a message if the user doesn't input correctly
                    System.out.println("Invalid choice. Please select a valid option.");
                    continue;
            }
            SavePrompt(result, fileName);
        } else {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine();
        }
    }
}
// Function to prompt the user to save the roll result
public static void SavePrompt(int result, String fileName) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Do you want to save this rool? (Y/N): ");
    String saveChoice = scanner.nextLine();
    if (saveChoice.equalsIgnoreCase("Y")) {
        saveRoll(fileName, result);
    }
}
// Function to save the roll result to a file
public static void saveRoll(String fileName, int result) {
    try (FileWriter writer = new FileWriter(fileName, true)) {
        writer.write(Integer.toString(result) + "\n");
        System.out.println("Result saved successfully!");
    } catch (IOException e) {
        e.printStackTrace();
    }
}

// Function to load and display previous rolls from a file
public static void loadPreviousRolls(String fileName) {
    File file = new File(fileName);
    if (file.exists()) {
        List<Integer> rolls = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextInt()) {
                int roll = scanner.nextInt();
                rolls.add(roll);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // This iterates through the list of previous rolls and displays them
        if (!rolls.isEmpty()) {
            System.out.println ("Previous Rolls:");
            for (int roll :rolls) {
                System.out.println(roll);
            }
            // These two else statements will display if there are no saved rolls
        } else {
            System.out.println("No previous rolls found.");
        }
    } else {
        System.out.println("No previous rolls found.");
    }
}
}