public class Journal extends BorrowableItem {
    private Integer issueNo;
    private String publisher;
    private Integer numberOfPages;
    private String subject;
    // Constructor
    public Journal(Integer issueNo, String publisher, Integer numberOfPages, String subject, Integer ID, Boolean issued,
                   String title, String type, Float cost, String location) {
        super(ID, title, issued, type, cost, location);
        this.issueNo = issueNo;
        this.publisher = publisher;
        this.numberOfPages = numberOfPages;
        this.subject = subject;
    }

    // Getters
    public Integer getIssueNo() {
        return issueNo;
    }
    public String getPublisher() {
        return publisher;
    }
    public Integer getNumberOfPages() {
        return numberOfPages;
    }
    public String getSubject() {
        return subject;
    }
    // Setters
    public void setIssueNo(Integer issueNo) {
        this.issueNo = issueNo;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
}
