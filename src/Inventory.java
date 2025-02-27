import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Objects;

public class Inventory {
    // Data structure to store all items added to inventory.
    private final ArrayList<BorrowableItem> allItems;
    private BorrowableItem currentItem;
    private Integer currentRecord;
    // Constructor
    public Inventory() {
        allItems = new ArrayList<>();
        currentItem = null;
        currentRecord = 0;
    }
    // Add a AVItem to the inventory.
    public AVItem addAVItem(String format, Float duration, Integer ID, Boolean issued,
                            String title, String type, Float cost, String location) {
        AVItem newItem = new AVItem(format, duration, ID, issued, title, type, cost, location);
        allItems.add(newItem);
        return newItem;
    }
    // Add a Book to the inventory.
    public Book addBook(String author, Integer numberOfPages, String publisher, Integer ID, Boolean issued, String title,
                        String type, Float cost, String location) {
        Book newItem = new Book(author, numberOfPages, publisher, ID, issued, title, type, cost, location);
        allItems.add(newItem);
        return newItem;
    }
    // Add a Journal to the inventory.
    public Journal addJournal(Integer issueNo, String publisher, Integer numberOfPages, String subject,
                              Integer ID, Boolean issued, String title, String type, Float cost, String location) {
        Journal newItem = new Journal(issueNo, publisher, numberOfPages, subject, ID, issued, title, type, cost, location);
        allItems.add(newItem);
        return newItem;
    }
    // Calculate the insurance cost of the entire inventory.
    public Float calcInsuranceCost() {
        float totalInsuranceCost = 0;
        for (currentRecord = 0; currentRecord < allItems.size(); incrementCurrentRecord()) {
           totalInsuranceCost += getCurrentItem().getCost()/2;
        }
        // If the inventory has an insurance cost over £400 then reverse it back to £400 instead to follow the business rule.
        if (totalInsuranceCost > 400) {
            totalInsuranceCost = 400;
        }
        return totalInsuranceCost;
    }
    // Calculate the total cost of the entire inventory.
    public Float calcTotalCost() {
        float totalCost = 0;
        for (currentRecord = 0; currentRecord < allItems.size(); incrementCurrentRecord()) {
            totalCost += getCurrentItem().getCost();
        }
        return totalCost;
    }
    // Decrement the current index of the inventory arraylist.
    public void decrementCurrentRecord() {
        currentRecord--;
    }
    // Find an item via ID in the inventory.
    public BorrowableItem findItemByID(Integer ID) {
        for (currentRecord = 0; currentRecord < allItems.size(); incrementCurrentRecord()) {
            if (Objects.equals(getCurrentItem().getID(), ID)) {
                return getCurrentItem();
            }
        }
        return null;
    }
    // Converts an object to a string with its attributes.
    public static String ObjectToString(Object obj) {
        if (obj == null) return "null"; // Return "null" if the object does not exist.
        // Start building the string with the class name.
        StringBuilder result = new StringBuilder(obj.getClass().getSimpleName() + " {");
        Class<?> currentClass = obj.getClass();
        // Traverse the class hierarchy to include attributes from parent classes.
        while (currentClass != null) {
            Field[] fields = currentClass.getDeclaredFields(); // Get all declared attributes of the current class.
            for (Field field : fields) {
                field.setAccessible(true); // Allow access to private attributes.
                try {
                    // Append attribute to the result string.
                    result.append(field.getName()).append("=").append(field.get(obj)).append(", ");
                } catch (IllegalAccessException e) {
                    // Handle cases where access to the attribute is denied.
                    result.append(field.getName()).append("=ACCESS DENIED, ");
                }
            }
            currentClass = currentClass.getSuperclass(); // Move to the superclass.
        }
        // Remove the trailing comma and space, then close the curly brace.
        return result.toString().replaceAll(", $", " }");
    }
    // Get the current item in the inventory with the index of current record.
    public BorrowableItem getCurrentItem() {
        currentItem = allItems.get(currentRecord);
        return currentItem;
    }
    public int getCurrentRecord() {
        return currentRecord;
    }
    // Get an item by index.
    public BorrowableItem getItemByIndex(Integer index) {
        return allItems.get(index);
    }
    // Get inventory size.
    public Integer getNumberOfItems() {
        return allItems.size();
    }
    // Increment the current index of the inventory arraylist.
    public void incrementCurrentRecord() {
        currentRecord++;
    }
    // Add items to database.
    public void PopulateDatabase() {
        //TODO add database.
    }
    // Delete an item in the inventory via ID
    public Boolean removeItemByID(Integer ID) {
        return allItems.removeIf(item -> Objects.equals(item.getID(), ID));
    }
}
