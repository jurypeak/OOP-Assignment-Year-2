import java.util.FormatterClosedException;

public class Controller {
    private final Inventory inventory;

    public Controller(Inventory inventory) {
        this.inventory = inventory;
    }
    public void AddBook(String author, Integer numberOfPages, String publisher, Integer ID, Boolean issued,
                        String title, String type, Float cost, String location) {
        inventory.addBook(author, numberOfPages, publisher, ID, issued, title, type, cost, location);
    }
    public void AddAVItem(String format, Float duration, Integer ID, Boolean issued,
                          String title, String type, Float cost, String location) {
        inventory.addAVItem(format, duration, ID, issued, title, type, cost, location);
    }
    public void AddJournal(Integer issueNo, String publisher, Integer numberOfPages, String subject, Integer ID,
                           String title, Boolean issued, String type, Float cost, String location) {
        inventory.addJournal(issueNo, publisher, numberOfPages, subject, ID, issued, title, type, cost, location);
    }
    public int ValidateInt(Object input, String context) {
        int validInput;
        try {
            validInput = Integer.parseInt(input.toString());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid " + context + ".");
            return ValidateInt(System.console().readLine(), context);
        }
        return validInput;
    }
    public String ValidateString(Object input, String context) {
        String validInput;
        try {
            validInput = input.toString();
            if (validInput.isEmpty()) {
                throw new FormatterClosedException();
            }

        } catch (FormatterClosedException e) {
            System.out.println("Invalid input. Please enter a valid " + context + ".");
            return ValidateString(System.console().readLine(), context);
        }
        return validInput;
    }
    public Float ValidateFloat(Object input, String context) {
        float validInput;
        try {
            validInput = Float.parseFloat(input.toString());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid " + context + ".");
            return ValidateFloat(System.console().readLine(), context);
        }
        return validInput;
    }
    public int ValidateID(int input) {
        int validID = input;
        for (int i = 0; i < InventorySize(); i++) {
            if (validID == inventory.getItemByIndex(i).getID()) {
                System.out.println("ID already exists. Please enter a different ID.");
                return ValidateID(ValidateInt(System.console().readLine(), "ID"));
            }
        }
        return validID;
    }
    public String PrintInventory(int x) {
        String item = Inventory.Convert(inventory.getItemByIndex(x));
        return item;
    }
    public int InventorySize() {
        return inventory.getNumberOfItems();
    }
    public boolean ValidateBoolean(String input) {
        if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
            return true;
        }
        else if (input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no")) {
            return false;
        }
        else {
            System.out.println("Invalid input. Please enter a valid answer, either yes or no.");
            return ValidateBoolean(System.console().readLine());
        }
    }
}
