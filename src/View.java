import java.util.Scanner;

public class View {

    // Create a new controller.
    private final Controller controller;
    // Create a new scanner to get user input.
    private final Scanner scan = new Scanner(System.in);

    public View(Controller controller) {
        this.controller = controller;
    }

    // Display main menu.
    public void displayMenu() {
        while (true) {
            displayMessage("-------------------------------");
            displayMessage("Enter a number to select an option:");
            displayMessage("1: Add an item");
            displayMessage("2: Edit an existing item");
            displayMessage("3: View all items");
            displayMessage("4: Search for an item by ID");
            displayMessage("5: Remove item by ID");
            displayMessage("6: Calculate total cost");
            displayMessage("7: Calculate insurance cost");
            displayMessage("8: Exit");
            displayMessage("-------------------------------");

            String choice = (scan.nextLine());
            controller.handleMainMenuChoice(choice);
        }
    }
    // Display a message.
    public void displayMessage(String message) {
        System.out.println(message);
    }
    // Display add item menu.
    public void displayAddItemMenu() {
        displayMessage("-------------------------------");
        displayMessage("Enter a number to add an item:");
        displayMessage("1: Book");
        displayMessage("2: AV Item");
        displayMessage("3: Journal");
        displayMessage("4: Exit");
        displayMessage("-------------------------------");
    }
    // Display exit message.
    public void displayExitMessage() {
        displayMessage("-------------------------------");
        displayMessage("Exiting...");
    }
    // Display a message and get user input of string.
    public String getUserInput(String prompt) {
        displayMessage(prompt);
        return scan.nextLine();
    }
}

