import java.util.FormatterClosedException;

public class Controller {
    // Create a new inventory.
    private final Inventory inventory;
    // Create a new view
    private final View view;
    // Constructor
    public Controller(Inventory inventory, View view) {
        this.inventory = inventory;
        this.view = view;
    }
    // Validate string input to make sure it can be parsed into an integer.
    // Takes in context string to give clarity in error message.
    public int ValidateInt(String input, String context) {
        int validInput;
        try {
            validInput = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return ValidateInt(view.getUserInput("Invalid input. Please enter a valid " + context + "!"), context);
        }
        return validInput;
    }
    // Validate string input to make sure it's a string.
    // Takes in context string to give clarity in error message.
    public String ValidateString(String input, String context) {
        String validInput;
        try {
            validInput = input;
            if (validInput.isEmpty()) {
                throw new FormatterClosedException();
            }

        } catch (FormatterClosedException e) {
            return ValidateString(view.getUserInput("Invalid input. Please enter a valid " + context + "!"), context);
        }
        return validInput;
    }
    // Validate string input to make it can be parsed into a float.
    // Takes in context string to give clarity in error message.
    public Float ValidateFloat(String input, String context) {
        float validInput;
        try {
            validInput = Float.parseFloat(input);
        } catch (NumberFormatException e) {
            return ValidateFloat(view.getUserInput("Invalid input. Please enter a valid " + context + "!"), context);
        }
        return validInput;
    }
    // Validate inputted ID to make sure it's unique (following business rule).
    public int ValidateID(int input) {
        for (int i = 0; i < InventorySize(); i++) {
            if (input == inventory.getItemByIndex(i).getID()) {
                return ValidateID(ValidateInt(view.getUserInput("ID already exists. Please enter a different ID!"), "ID"));
            }
        }
        return input;
    }
    // Call getItemByIndex method in inventory and turn it to a string.
    public String GetItemByIndex(int x) {
        return Inventory.ObjectToString(inventory.getItemByIndex(x));
    }
    // Call findItemByID method in inventory and turn it to a string.
    public String GetItemByID(int x) {
        return Inventory.ObjectToString(inventory.findItemByID(x));
    }
    // Call view to display each item in inventory to string.
    public void PrintInventory() {
        if (InventorySize() == 0) {
            view.displayMessage("-------------------------------");
            view.displayMessage("Inventory is empty!");
        }
        else {
            view.displayMessage("-------------------------------");
            view.displayMessage(inventory.getNumberOfItems() + " items in the inventory:");
            view.displayMessage("-------------------------------");
            for (int i = 0; i < InventorySize(); i++) {
                view.displayMessage(GetItemByIndex(i));
            }
        }
    }
    // Call view to display item with the associated ID inputted by user (if it exists).
    public void PrintItemByID(int x) {
        String item = GetItemByID(x);
        if (item.equals("null")) {
            view.displayMessage("Item not found!");
        }
        else {
            view.displayMessage("Item found!");
            view.displayMessage(item);
        }
    }
    // Call the removeItemByID method to remove item by inputted ID in inventory.
    public void RemoveItemByID(int x) {
        if (inventory.removeItemByID(x)) {
            view.displayMessage("Item removed successfully.");
        }
        else {
            view.displayMessage("Item not found!");
        }
    }
    // Call the getNumberOfItems method in inventory to get size of inventory.
    public int InventorySize() {
        return inventory.getNumberOfItems();
    }
    // Validate string input to make sure it's an either a yes or no choice.
    // Takes in context string to give clarity in error message.
    public boolean ValidateBoolean(String input) {
        if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
            return true;
        }
        else if (input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no")) {
            return false;
        }
        else {
            return ValidateBoolean(ValidateString(view.getUserInput("Invalid input. Please enter a valid answer, either yes or no!"), "choice"));
        }
    }
    // Make sure the user inputs an appropriate choice for the AVItem format.
    public String FormatChoice(int format) {
        return switch (format) {
            case 1 -> "CD";
            case 2 -> "DVD";
            default ->
                    FormatChoice(ValidateInt(view.getUserInput("Invalid input. Please enter a valid format!"), "choice"));
        };
    }
    // Call calcTotalCost method in inventory to get total cost of inventory.
    public Float CalcTotalCost() {
        return inventory.calcTotalCost();
    }
    // Call calcInsuranceCost method in inventory to get total insurance cost of inventory.
    public Float CalcInsuranceCost() {
        return inventory.calcInsuranceCost();
    }
    // Call view to display text for user to input into to create a basic borrowable item which will be used to edit either a book, AVItem or journal.
    private BorrowableItem getNewItem(int itemID) {
        int ID = EditID(ValidateInt(view.getUserInput("Enter a new ID"), "ID"), itemID);
        boolean issued = ValidateBoolean(ValidateString(view.getUserInput("Is it issued? (y/n)"), "choice"));
        String title = ValidateString(view.getUserInput("Enter a new title"), "title");
        String type = ValidateString(view.getUserInput("Enter a new type"), "type");
        Float cost = ValidateFloat(view.getUserInput("Enter a new cost"), "cost");
        String location = ValidateString(view.getUserInput("Enter a new location"), "location");
        return new BorrowableItem(ID, title, issued, type, cost, location);
    }
    // Allows for the previous ID the item held before edit to be used again and that no other ID's held by other items are used.
    private int EditID(int newID, int itemID) {
        for (inventory.getCurrentRecord(); inventory.getCurrentRecord() < InventorySize(); inventory.incrementCurrentRecord()) {
            if (newID == itemID) {
                return newID;
            }
            else if (newID == inventory.getItemByIndex(inventory.getCurrentRecord()).getID()) {
                return EditID(ValidateInt(view.getUserInput("ID already exists. Please enter a different ID!"), "ID"), itemID);
            }
        }
        return newID;
    }
    // Edit an item via an ID and call view to display text depending on items class.
    void EditItem(int id) {
        BorrowableItem item = inventory.findItemByID(id);
        switch (item) {
            case Book _ -> {
                view.displayMessage("-------------------------------");
                view.displayMessage("Editing book with ID: " + item.getID());
                view.displayMessage("-------------------------------");
                String newAuthor = ValidateString(view.getUserInput("Enter the author:"), "author");
                String newPublisher = ValidateString(view.getUserInput("Enter the publisher:"), "publisher");
                Integer newNumberOfPages = ValidateInt(view.getUserInput("Enter the number of pages:"), "number of pages");
                // Create a new tempItem to be used to edit and update this item.
                BorrowableItem tempItem = getNewItem(item.getID());
                UpdateBook(item, newAuthor, newPublisher, newNumberOfPages, tempItem.getTitle(), tempItem.getType(),
                        tempItem.getCost(), tempItem.getLocation(), tempItem.getID(), tempItem.getIssued());
                view.displayMessage("Book edited successfully.");
            }
            case AVItem _ -> {
                view.displayMessage("-------------------------------");
                view.displayMessage("Editing AVItem with ID: " + item.getID());
                view.displayMessage("-------------------------------");
                view.displayMessage("Choose a new format: ");
                view.displayMessage("-------------------------------");
                view.displayMessage("1: CD");
                view.displayMessage("2: DVD");
                view.displayMessage("-------------------------------");
                String newFormat = FormatChoice(ValidateInt(view.getUserInput("Enter the format:"), "choice"));
                Float newDuration = ValidateFloat(view.getUserInput("Enter the duration:"), "duration");
                // Create a new tempItem to be used to edit and update this item.
                BorrowableItem tempItem = getNewItem(item.getID());
                UpdateAVItem(item, newFormat, newDuration, tempItem.getTitle(), tempItem.getType(),
                        tempItem.getCost(), tempItem.getLocation(), tempItem.getID(), tempItem.getIssued());
                view.displayMessage("AVItem edited successfully.");
            }
            case Journal _ -> {
                view.displayMessage("-------------------------------");
                view.displayMessage("Editing journal with ID: " + item.getID());
                view.displayMessage("-------------------------------");
                Integer newIssueNo = ValidateInt(view.getUserInput("Enter the issue number:"), "issue number");
                String newPublisher = ValidateString(view.getUserInput("Enter the publisher:"), "publisher");
                Integer newNumberOfPages = ValidateInt(view.getUserInput("Enter the number of pages:"), "number of pages");
                String newSubject = ValidateString(view.getUserInput("Enter the subject:"), "subject");
                // Create a new tempItem to be used to edit and update this item.
                BorrowableItem tempItem = getNewItem(item.getID());
                UpdateJournal(item, newIssueNo, newPublisher, newNumberOfPages, newSubject, tempItem.getID(), tempItem.getTitle(),
                        tempItem.getIssued(), tempItem.getType(), tempItem.getCost(), tempItem.getLocation());
                view.displayMessage("Journal edited successfully.");
            }
            case null -> {
                view.displayMessage("-------------------------------");
                view.displayMessage("Item not found!");
                view.displayMessage("Canceling edit...");
            }
            default -> throw new IllegalStateException("Canceling edit... \nError occurred in switch statement. Contact developer.");
        }
    }
    // Set new book attributes to currentBook and update it.
    public void UpdateBook(BorrowableItem item, String Author, String Publisher, Integer numberOfPages, String title,
                           String type, Float cost, String location, Integer ID, Boolean issued) {
        ((Book) item).setAuthor(Author);
        ((Book) item).setPublisher(Publisher);
        ((Book) item).setNumberOfPages(numberOfPages);
        item.setTitle(title);
        item.setType(type);
        item.setCost(cost);
        item.setLocation(location);
        item.setID(ID);
        item.setIssued(issued);
        view.displayMessage("-------------------------------");
        view.displayMessage("Book updated.");
    }
    // Set new AVItem attributes to currentAVItem and update it.
    public void UpdateAVItem(BorrowableItem item, String format, Float duration, String title,
                             String type, Float cost, String location, Integer ID, Boolean issued) {
        ((AVItem) item).setFormat(format);
        ((AVItem) item).setDuration(duration);
        item.setTitle(title);
        item.setType(type);
        item.setCost(cost);
        item.setLocation(location);
        item.setID(ID);
        item.setIssued(issued);
        view.displayMessage("-------------------------------");
        view.displayMessage("AVItem updated.");
    }
    // Set new journal attributes to currentJournal and update it.
    public void UpdateJournal(BorrowableItem item, Integer issueNo, String publisher, Integer numberOfPages, String subject,
                              Integer ID, String title, Boolean issued, String type, Float cost, String location) {
        ((Journal) item).setIssueNo(issueNo);
        ((Journal) item).setPublisher(publisher);
        ((Journal) item).setNumberOfPages(numberOfPages);
        ((Journal) item).setSubject(subject);
        item.setID(ID);
        item.setTitle(title);
        item.setType(type);
        item.setCost(cost);
        item.setLocation(location);
        item.setIssued(issued);
        view.displayMessage("-------------------------------");
        view.displayMessage("Journal updated.");
    }
    // Call view to get users choice for main menu.
    public void handleMainMenuChoice(String choice) {
        // Validate users choice.
        int validChoice = ValidateInt(choice, "choice");
        if (validChoice < 1 || validChoice > 8) {
            handleMainMenuChoice(view.getUserInput("Invalid choice. Please enter a valid choice."));
        }
        switch (validChoice) {
            case 1:
                addItem();
                break;
            case 2:
                editItem();
                break;
            case 3:
                viewAllItems();
                break;
            case 4:
                viewItemByID();
                break;
            case 5:
                removeItemByID();
                break;
            case 6:
                calculateTotalCost();
                break;
            case 7:
                calculateInsuranceCost();
                break;
            case 8:
                view.displayExitMessage();
                System.exit(0);
        }
    }
    // Call view to get users choice when using the add item menu.
    public void addItem() {
        view.displayAddItemMenu();
        // Validate users choice.
        int choice = ValidateInt(view.getUserInput("Enter a choice."), "choice");
        if (choice < 1 || choice > 4) {
            view.displayMessage("Invalid choice. Please enter a valid choice.");
            addItem();
        }
        switch (choice) {
            case 1:
                addBook();
                break;
            case 2:
                addAVItem();
                break;
            case 3:
                addJournal();
                break;
            case 4:
                view.displayExitMessage();
        }
    }
    // Call view to get users input for the add book function.
    private void addBook() {
        // Validate all users input.
        String author = ValidateString(view.getUserInput("Enter the author:"), "author");
        int pages = ValidateInt(view.getUserInput("Enter the number of pages:"), "number of pages");
        String publisher = ValidateString(view.getUserInput("Enter the publisher:"), "publisher");
        BorrowableItem book = getBorrowableItemDetails();
        // Call the inventory to add book with the users input.
        inventory.addBook(author, pages, publisher, book.getID(), book.getIssued(),
                book.getTitle(), book.getType(), book.getCost(), book.getLocation());
    }
    // Call view to get users input for the add AVItem function.
    private void addAVItem() {
        // Validate all users input.
        String format = FormatChoice(ValidateInt(view.getUserInput("""
                -------------------------------\
                
                Choose a format:\
                
                -------------------------------\
                
                1: CD\s
                2: DVD\
   
                -------------------------------"""), "choice"));
        float duration = ValidateFloat(view.getUserInput("Enter the duration:"), "duration");
        BorrowableItem avItem = getBorrowableItemDetails();
        // Call the inventory to add AVItem with the users input.
        inventory.addAVItem(format, duration, avItem.getID(), avItem.getIssued(), avItem.getTitle(),
                avItem.getType(), avItem.getCost(), avItem.getLocation());
    }
    // Call view to get the users input for the add journal function.
    private void addJournal() {
        // Validate all users input.
        int issueNo = ValidateInt(view.getUserInput("Enter the issue number:"), "issue number");
        String publisher = ValidateString(view.getUserInput("Enter the publisher:"), "publisher");
        int pages = ValidateInt(view.getUserInput("Enter the number of pages:"), "number of pages");
        String subject = ValidateString(view.getUserInput("Enter the subject:"), "subject");
        BorrowableItem journal = getBorrowableItemDetails();
        // Call the inventory to add journal with users input.
        inventory.addJournal(issueNo, publisher, pages, subject, journal.getID(), journal.getIssued(),
                journal.getTitle(), journal.getType(), journal.getCost(), journal.getLocation());
    }
    // Call view to get users input for basic item details.
    private BorrowableItem getBorrowableItemDetails() {
        // Validate all users inputs.
        int ID = ValidateID(ValidateInt(view.getUserInput("Enter ID:"), "ID"));
        boolean issued = ValidateBoolean(ValidateString(view.getUserInput("Is it issued? (y/n)"), "choice"));
        String title = ValidateString(view.getUserInput("Enter title:"), "title");
        String type = ValidateString(view.getUserInput("Enter type:"), "type");
        float cost = ValidateFloat(view.getUserInput("Enter cost:"), "cost");
        String location = ValidateString(view.getUserInput("Enter location:"), "location");
        // Create a new basic borrowable item.
        return new BorrowableItem(ID, title, issued, type, cost, location);
    }
    // Call view to get users input for the edit item function.
    private void editItem() {
        // Validate users input.
        int ID = ValidateInt(view.getUserInput("Enter the ID of the item you want to edit:"), "ID");
        EditItem(ID);
    }
    // Call the print inventory function.
    private void viewAllItems() {
        PrintInventory();
    }
    // Call view to get users input for the print item by ID function.
    private void viewItemByID() {
        // Validate input.
        int ID = ValidateInt(view.getUserInput("Enter the ID of the item you want to view:"), "ID");
        PrintItemByID(ID);
    }
    // Call view to get users input for the remove item by ID function.
    private void removeItemByID() {
        int ID = ValidateInt(view.getUserInput("Enter the ID of the item you want to remove:"), "ID");
        RemoveItemByID(ID);
    }
    // Call view to display total cost while calling the calculate total cost function in controller.
    private void calculateTotalCost() {
        view.displayMessage("Total cost: " + CalcTotalCost());
    }
    // Call view to display total insurance cost while calling the calculate insurance cost function in controller.
    private void calculateInsuranceCost() {
        view.displayMessage("Insurance cost: " + CalcInsuranceCost());
    }
}