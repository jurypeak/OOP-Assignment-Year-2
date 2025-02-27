import java.util.stream.Collectors;

public class BorrowableItem {
    private Float cost;
    private Integer ID;
    private Boolean issued;
    private String location;
    private String title;
    private String type;
    // Constructor
    public BorrowableItem(Integer ID, String title, Boolean issued, String type, Float cost, String location) {
        this.ID = ID;
        this.title = title;
        this.issued = issued;
        this.type = type;
        this.cost = cost;
        this.location = location;
    }
    // Getters
    public float getCost() {
        return cost;
    }
    public Integer getID() {
        return ID;
    }
    public Boolean getIssued() {
        return issued;
    }
    public String getLocation() {
        return location;
    }
    public String getTitle() {
        return title;
    }
    public String getType() {
        return type;
    }
    // Setters
    public void setCost(Float cost) {
        this.cost = cost;
    }
    public void setID(Integer ID) {
        this.ID = ID;
    }
    public void setIssued(Boolean issued) {
        this.issued = issued;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setType(String type) {
        this.type = type;
    }
}
