public class Main {

    public static void main(String[] args) {

        Inventory inventory = new Inventory();  // Model
        Controller controller = new Controller(inventory, new View(null));  // Controller
        View view = new View(controller); // View

        view.displayMenu();  // Start the application by displaying the menu.
    }
}