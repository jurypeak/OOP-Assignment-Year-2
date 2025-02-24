
public class Main {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        inventory.addBook("Author1", 100, "Publisher1", 1, "Title1",
                "Book", 10.0f, "Location1");
        inventory.addAVItem("Format1", 10, 2, "Title2",
                "AV", 20.0f, "Location2");
        System.out.println(inventory.calcTotalCost());
    }
}