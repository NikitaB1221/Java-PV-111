package step.learning.oop;

public class Book extends Literature{
    private String author;

    public Book(String author,String title) {
        super(title);
        this.author = author;

    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCard(){
        return String.format("Book. Author: '%s'.Title: '%s'",this.getAuthor(),super.getTitle());
    }
}
