public class AVItem extends BorrowableItem{
    private String format;
    private Integer duration;

    public AVItem(String format, Integer duration, Integer ID, String title, String type, Float cost, String location) {
        super(ID, title, type, cost, location);
        this.format = format;
        this.duration = duration;
    }

    public String getFormat() {
        return format;
    }
    public Integer getDuration() {
        return duration;
    }

    public void setFormat(String format) {
        this.format = format;
    }
    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
