public class AVItem extends BorrowableItem{
    private String format;
    private Float duration;

    public AVItem(String format, Float duration, Integer ID, Boolean issued, String title, String type, Float cost, String location) {
        super(ID, title, issued, type, cost, location);
        this.format = format;
        this.duration = duration;
    }

    public String getFormat() {
        return format;
    }
    public Float getDuration() {
        return duration;
    }

    public void setFormat(String format) {
        this.format = format;
    }
    public void setDuration(Float duration) {
        this.duration = duration;
    }
}
