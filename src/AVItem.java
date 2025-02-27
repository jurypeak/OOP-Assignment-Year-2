public class AVItem extends BorrowableItem{
    private String format;
    private Float duration;
    // Constructor
    public AVItem(String format, Float duration, Integer ID, Boolean issued, String title, String type, Float cost, String location) {
        super(ID, title, issued, type, cost, location);
        this.format = format;
        this.duration = duration;
    }
    // Getters
    public String getFormat() {
        return format;
    }
    public Float getDuration() {
        return duration;
    }
    // Setters
    public void setFormat(String format) {
        this.format = format;
    }
    public void setDuration(Float duration) {
        this.duration = duration;
    }
}
