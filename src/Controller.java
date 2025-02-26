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
            System.out.println("Invalid input. Please enter a valid " + context + "!");
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
            System.out.println("Invalid input. Please enter a valid " + context + "!");
            return ValidateString(System.console().readLine(), context);
        }
        return validInput;
    }
    public Float ValidateFloat(Object input, String context) {
        float validInput;
        try {
            validInput = Float.parseFloat(input.toString());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid " + context + "!");
            return ValidateFloat(System.console().readLine(), context);
        }
        return validInput;
    }
    public int ValidateID(int input) {
        for (int i = 0; i < InventorySize(); i++) {
            if (input == inventory.getItemByIndex(i).getID()) {
                System.out.println("ID already exists. Please enter a different ID!");
                return ValidateID(ValidateInt(System.console().readLine(), "ID"));
            }
        }
        return input;
    }
    public String GetItemByIndex(int x) {
        return Inventory.ObjectToString(inventory.getItemByIndex(x));
    }
    public void PrintInventory() {
        if (InventorySize() == 0) {
            System.out.println("Inventory is empty!");
        }
        else {
            System.out.println("All items in the inventory:");
            for (int i = 0; i < InventorySize(); i++) {
                System.out.println(GetItemByIndex(i));
            }
        }
    }
    public void PrintItemByID(int x) {
        String item = Inventory.ObjectToString(inventory.findItemByID(x));
        if (item.equals("null")) {
            System.out.println("Item not found!");
        }
        else {
            System.out.println("Item found!");
            System.out.println(item);
        }
    }
    public void RemoveItemByID(int x) {
        if (inventory.removeItemByIndex(x)) {
            System.out.println("Item removed successfully.");
        }
        else {
            System.out.println("Item not found!");
        }
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
            System.out.println("Invalid input. Please enter a valid answer, either yes or no!");
            return ValidateBoolean(System.console().readLine());
        }
    }
    public String FormatChoice(int format) {
        return switch (format) {
            case 1 -> "CD";
            case 2 -> "DVD";
            default -> {
                System.out.println("Invalid input. Please enter a valid format!");
                yield FormatChoice(ValidateInt(System.console().readLine(), "choice"));}
        };
    }
    public Float CalcTotalCost() {
        return inventory.calcTotalCost();
    }
    public Float CalcInsuranceCost() {
        return inventory.calcInsuranceCost();
    }
    private BorrowableItem EditBorrowableItemText(int itemID) {
        System.out.println("Enter a new ID: ");
        int ID = EditID(ValidateInt(System.console().readLine(), "number"), itemID);
        System.out.println("Is it issued? (y/n): ");
        boolean issued = ValidateBoolean(System.console().readLine());
        System.out.println("Enter a new title: ");
        String title = ValidateString(System.console().readLine(), "title");
        System.out.println("Enter a new type: ");
        String type = ValidateString(System.console().readLine(), "type");
        System.out.println("Enter a new cost: ");
        Float cost = ValidateFloat(System.console().readLine(), "cost");
        System.out.println("Enter a new location: ");
        String location = ValidateString(System.console().readLine(), "location");
        return new BorrowableItem(ID, title, issued, type, cost, location);
    }

    private int EditID(int newID, int itemID) {
        for (int i = 0; i < InventorySize(); i++) {
            if (newID == itemID) {
                return newID;
            }
            else if (newID == inventory.getItemByIndex(i).getID()) {
                System.out.println("ID already exists. Please enter a different ID!");
                return EditID(ValidateInt(System.console().readLine(), "ID"), itemID);
            }
        }
        return newID;
    }

    void EditItem(int id) {
        BorrowableItem item = inventory.findItemByID(id);
        switch (item) {
            case Book _ -> {
                System.out.println("-------------------------------");
                System.out.println("Editing book with ID: " + item.getID());
                System.out.println("-------------------------------");
                System.out.println("Enter a new author:");
                String newAuthor = ValidateString(System.console().readLine(), "author");
                System.out.println("Enter a new publisher:");
                String newPublisher = ValidateString(System.console().readLine(), "publisher");
                System.out.println("Enter a new number of pages:");
                Integer newNumberOfPages = ValidateInt(System.console().readLine(), "number of pages");
                BorrowableItem tempItem = EditBorrowableItemText(item.getID());
                UpdateBook(item, newAuthor, newPublisher, newNumberOfPages, tempItem.getTitle(), tempItem.getType(),
                        tempItem.getCost(), tempItem.getLocation(), tempItem.getID(), tempItem.getIssued());
                System.out.println("Book edited successfully.");
            }
            case AVItem _ -> {
                System.out.println("-------------------------------");
                System.out.println("Editing AVItem with ID: " + item.getID());
                System.out.println("-------------------------------");
                System.out.println("Choose a new format: ");
                System.out.println("-------------------------------");
                System.out.println("1: CD");
                System.out.println("2: DVD");
                System.out.println("-------------------------------");
                String newFormat = FormatChoice(ValidateInt(System.console().readLine(), "choice"));
                System.out.println("Enter a new duration:");
                Float newDuration = ValidateFloat(System.console().readLine(), "duration");
                BorrowableItem tempItem = EditBorrowableItemText(item.getID());
                UpdateAVItem(item, newFormat, newDuration, tempItem.getTitle(), tempItem.getType(),
                        tempItem.getCost(), tempItem.getLocation(), tempItem.getID(), tempItem.getIssued());
                System.out.println("AVItem edited successfully.");
            }
            case Journal _ -> {
                System.out.println("-------------------------------");
                System.out.println("Editing journal with ID: " + item.getID());
                System.out.println("-------------------------------");
                System.out.println("Enter a new issue number:");
                Integer newIssueNo = ValidateInt(System.console().readLine(), "issue number");
                System.out.println("Enter a new publisher:");
                String newPublisher = ValidateString(System.console().readLine(), "publisher");
                System.out.println("Enter a new number of pages:");
                Integer newNumberOfPages = ValidateInt(System.console().readLine(), "number of pages");
                System.out.println("Enter a new subject:");
                String newSubject = ValidateString(System.console().readLine(), "subject");
                BorrowableItem tempItem = EditBorrowableItemText(item.getID());
                UpdateJournal(item, newIssueNo, newPublisher, newNumberOfPages, newSubject, tempItem.getID(), tempItem.getTitle(),
                        tempItem.getIssued(), tempItem.getType(), tempItem.getCost(), tempItem.getLocation());
                System.out.println("Journal edited successfully.");
            }
            case null -> {
                System.out.println("-------------------------------");
                System.out.println("Item not found!");
                System.out.println("Canceling edit...");
            }
            default -> throw new IllegalStateException("Canceling edit... \nError occurred in switch statement. Contact developer.");
        }
    }
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
        System.out.println("-------------------------------");
        System.out.println("Book updated.");
    }
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
        System.out.println("-------------------------------");
        System.out.println("AVItem updated.");
    }
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
        System.out.println("-------------------------------");
        System.out.println("Journal updated.");
    }

}
