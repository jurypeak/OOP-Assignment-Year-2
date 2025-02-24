import javax.swing.*;
import java.awt.event.ActionEvent;

public class LibraryUI {
    private JPanel addItemsPanel;
    private Integer index;
    private Inventory myInventory;
    private JTabbedPane myTabbedPane;
    private Integer numberOfItems;
    private JPanel removeItemsPanel;
    private JPanel searchItemsPanel;
    private BorrowableItem tmpItem;

    public LibraryUI() {
        myInventory = new Inventory();
        myTabbedPane = new JTabbedPane();
        myTabbedPane.addTab("Add Items", addItemsPanel);
        myTabbedPane.addTab("Remove Items", removeItemsPanel);
        myTabbedPane.addTab("Search Items", searchItemsPanel);
        numberOfItems = myInventory.getNumberOfItems();
        index = 0;
        tmpItem = null;
    }
    public ActionEvent actionPerformed(ActionEvent e) {
        return null;
    }
}
