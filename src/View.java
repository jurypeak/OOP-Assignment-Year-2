public class View {

    private Controller controller = new Controller(new Inventory());

    public View() {
        System.out.println("Welcome to the library!");
    }

    public void displayMenu() {
        boolean continueDisplay = true;
        while (continueDisplay) {
            System.out.println("-------------------------------");
            System.out.println("Enter a number to select an option:");
            System.out.println("1: Add an item");
            System.out.println("2: View all items");
            System.out.println("3: View item by ID");
            System.out.println("4: Remove item by ID");
            System.out.println("5: Exit");
            System.out.println("-------------------------------");
            int input = controller.ValidateInt(System.console().readLine(), "choice");
            switch (input) {
                case 1:
                    addItem();
                    break;
                case 2:
                    ViewAllItems();
                    break;
                case 3:
                    ViewItemByID();
                    break;
                case 4:
                    RemoveItemByID();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    continueDisplay = false;
                    break;
            }
        }
    }

    private BorrowableItem borrowableItemText() {
        System.out.println("ID: ");
        int ID = controller.ValidateID(controller.ValidateInt(System.console().readLine(), "number"));
        System.out.println("Issued: ");
        boolean issued = controller.ValidateBoolean(System.console().readLine());
        System.out.println("Title: ");
        String title = controller.ValidateString(System.console().readLine(), "title");
        System.out.println("Type: ");
        String type = controller.ValidateString(System.console().readLine(), "type");
        System.out.println("Cost: ");
        Float cost = controller.ValidateFloat(System.console().readLine(), "cost");
        System.out.println("Location: ");
        String location = controller.ValidateString(System.console().readLine(), "location");
        BorrowableItem newItem = new BorrowableItem(ID, title, issued, type, cost, location);
        return newItem;
    }
    public void addItem() {
        System.out.println("-------------------------------");
        System.out.println("Enter a number to add an item:");
        System.out.println("1: Book");
        System.out.println("2: AV Item");
        System.out.println("3: Journal");
        System.out.println("4: Exit");
        System.out.println("-------------------------------");
        int input = controller.ValidateInt(System.console().readLine(), "choice");
        switch (input) {
            case 1:
                System.out.println("Enter the following information about the book:");
                System.out.println("Author: ");
                String authorBook = controller.ValidateString(System.console().readLine(), "author");
                System.out.println("Number of pages: ");
                int numberOfPagesBook = controller.ValidateInt(System.console().readLine(), "number of pages");
                System.out.println("Publisher: ");
                String publisherBook = controller.ValidateString(System.console().readLine(), "publisher");
                BorrowableItem newBook = borrowableItemText();
                controller.AddBook(authorBook, numberOfPagesBook, publisherBook, newBook.getID(), newBook.getIssued(),
                        newBook.getTitle(), newBook.getType(), newBook.getCost(), newBook.getLocation());
                System.out.println("Book added successfully.");
                break;
            case 2:
                System.out.println("Enter the following information about the AV item:");
                System.out.println("Format: ");
                String formatAV = controller.ValidateString(System.console().readLine(), "format");
                System.out.println("Duration: ");
                float durationAV = controller.ValidateFloat(System.console().readLine(),  "duration");
                BorrowableItem newAV = borrowableItemText();
                controller.AddAVItem(formatAV, durationAV, newAV.getID(), newAV.getIssued(),
                        newAV.getTitle(), newAV.getType(), newAV.getCost(), newAV.getLocation());
                System.out.println("AV item added successfully.");
                break;
            case 3:
                System.out.println("Enter the following information about the journal:");
                System.out.println("IssueNo: ");
                int issueNoJournal = controller.ValidateInt(System.console().readLine(), "issue number");
                System.out.println("Publisher: ");
                String publisherJournal = controller.ValidateString(System.console().readLine(), "publisher");
                System.out.println("Number of pages: ");
                int numberOfPagesJournal = controller.ValidateInt(System.console().readLine(), "number of pages");
                System.out.println("Subject: ");
                String subjectJournal = controller.ValidateString(System.console().readLine(), "subject");
                BorrowableItem newJournal = borrowableItemText();
                controller.AddJournal(issueNoJournal, publisherJournal, numberOfPagesJournal, subjectJournal, newJournal.getID(), newJournal.getTitle(),
                        newJournal.getIssued(), newJournal.getType(), newJournal.getCost(), newJournal.getLocation());
                System.out.println("Journal added successfully.");
                break;
            case 4:
                System.out.println("Exiting...");
                break;
        }
    }
    public void ViewAllItems() {
        System.out.println("-------------------------------");
        controller.PrintInventory();
    }
    public void ViewItemByID() {
        System.out.println("-------------------------------");
        System.out.println("Enter the ID of the item you want to view:");
        int ID = controller.ValidateInt(System.console().readLine(), "number");
        System.out.println("-------------------------------");
        controller.PrintItemByID(ID);
    }
    public void RemoveItemByID() {
        System.out.println("-------------------------------");
        System.out.println("Enter the ID of the item you want to remove:");
        int ID = controller.ValidateInt(System.console().readLine(), "number");
        System.out.println("-------------------------------");
        controller.RemoveItemByID(ID);
    }
}
