public class Book extends BorrowableItem {
    private String author;
    private Integer numberOfPages;
    private String publisher;

    public Book(String author, Integer numberOfPages, String publisher, Integer ID,
                String title, String type, Float cost, String location) {
        super(ID, title, type, cost, location);
        this.author = author;
        this.numberOfPages = numberOfPages;
        this.publisher = publisher;
    }

    public String getAuthor() {
        return author;
    }
    public Integer getNumberOfPages() {
        return numberOfPages;
    }
    public String getPublisher() {
        return publisher;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
