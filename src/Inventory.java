import java.util.ArrayList;
import java.util.Objects;

public class Inventory {
    private ArrayList<BorrowableItem> allItems;
    private BorrowableItem currentItem;
    private Integer currentRecord;

    public Inventory() {
        allItems = new ArrayList<>();
        currentItem = null;
        currentRecord = 0;
    }

    public AVItem addAVItem(String format, Integer duration, Integer ID,
                            String title, String type, Float cost, String location) {
        AVItem newItem = new AVItem(format, duration, ID, title, type, cost, location);
        allItems.add(newItem);
        return newItem;
    }
    public Book addBook(String author, Integer numberOfPages, String publisher, Integer ID, String title,
                        String type, Float cost, String location) {
        Book newItem = new Book(author, numberOfPages, publisher, ID, title, type, cost, location);
        allItems.add(newItem);
        return newItem;
    }
    public Journal addJournal(Integer issueNo, String publisher, Integer numberOfPages, String subject,
                              Integer ID, String title, String type, Float cost, String location) {
        Journal newItem = new Journal(issueNo, publisher, numberOfPages, subject, ID, title, type, cost, location);
        allItems.add(newItem);
        return newItem;
    }
    public Float calcInsuranceCost() {
        float totalInsuranceCost = 0;
        for (BorrowableItem item : allItems) {
           totalInsuranceCost += item.getCost()/2;
        }
        if (totalInsuranceCost > 400) {
             totalInsuranceCost = 400;
        }
        return totalInsuranceCost;
    }
    public Float calcTotalCost() {
        float totalCost = 0;
        for (BorrowableItem item : allItems) {
            totalCost += item.getCost();
        }
        return totalCost;
    }
    public void decrementCurrentRecord() {
        currentRecord--;
    }
    public Integer findItemByID(Integer ID) {
        for (int i = 0; i < allItems.size(); i++) {
            if (allItems.get(i).getID() == ID) {
                return i;
            }
            else {
                return -1;
            }
        }
        return -1;
    }
    public BorrowableItem getCurrentItem() {
        return currentItem;
    }
    public BorrowableItem getItemByIndex(Integer index) {
        return allItems.get(index);
    }
    public Integer getNumberOfItems() {
        return allItems.size();
    }
    public void incrementCurrentRecord() {
        currentRecord++;
    }
    public void PopulateDatabase() {
        //TODO add database.
    }
    public Boolean removeItemByIndex(Integer index) {
        return allItems.removeIf(item -> Objects.equals(item.getID(), index));
    }
}
