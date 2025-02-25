public class View {

    Inventory inventory = new Inventory();

    private void borrowableItemText() {
        System.out.println("ID: ");
        int ID = ValidateInt(System.console().readLine().getClass());
        ValidateID(ID);
        System.out.println("Title: ");
        String title = ValidateString(System.console().readLine());
        System.out.println("Type: ");
        String type = ValidateString(System.console().readLine());
        System.out.println("Cost: ");
        String cost = System.console().readLine();
        System.out.println("Location: ");
        String location = ValidateString(System.console().readLine());
    }

    public void addItem() {
        System.out.println("-------------------------------");
        System.out.println("Enter a number to add an item:");
        System.out.println("1: Book");
        System.out.println("2: AV Item");
        System.out.println("3: Journal");
        System.out.println("4: Exit");
        System.out.println("-------------------------------");
        int input = ValidateInt(System.console().readLine());
        switch (input) {
            case 1:
                System.out.println("Enter the following information about the book:");
                System.out.println("Author: ");
                String authorBook = System.console().readLine();
                System.out.println("Number of pages: ");
                String numberOfPagesBook = System.console().readLine();
                System.out.println("Publisher: ");
                String publisherBook = System.console().readLine();
                System.out.println("Title: ");
                String titleBook = System.console().readLine();
                borrowableItemText();
                break;
            case 2:
                System.out.println("Enter the following information about the AV item:");
                System.out.println("Format: ");
                String formatAV = System.console().readLine();
                System.out.println("Duration: ");
                String durationAV = System.console().readLine();
                borrowableItemText();
                break;
            case 3:
                System.out.println("Enter the following information about the journal:");
                System.out.println("IssueNo: ");
                String issueNoJournal = System.console().readLine();
                System.out.println("Publisher: ");
                String publisherJournal = System.console().readLine();
                System.out.println("Number of pages: ");
                String numberOfPagesJournal = System.console().readLine();
                System.out.println("Subject: ");
                String subjectJournal = System.console().readLine();
                borrowableItemText();
                break;
            case 4:
                System.out.println("Exiting...");
                break;
        }
    }
    public int ValidateInt(Object input) {
        int validInput = -1;
        try {
            validInput = Integer.parseInt(input.toString());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid choice or number.");
            return ValidateInt(System.console().readLine());
        }
        return validInput;
    }
    public String ValidateString(Object input) {
        String validInput = "";
        try {
            validInput = input.toString();
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid choice or number.");
            return ValidateString(System.console().readLine());
        }
        return validInput;
    }
    public void ValidateID(int input) {
        for (int i = 0; i < inventory.getNumberOfItems(); i++) {
            if (input == inventory.getItemByIndex(i).getID()) {
                System.out.println("ID is already in use. Please enter a new ID.");
            } else {
                System.out.println("ID added successfully.");
            }
        }
    }
}
