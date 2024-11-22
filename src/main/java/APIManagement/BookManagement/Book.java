package APIManagement.BookManagement;

import java.util.ArrayList;

public class Book {
    private String title;
    private ArrayList<String> author;
    private String publishedDate;
    private String publisher;
    private String language;
    private String isbn;
    private int pages;
    private ArrayList<String> categories;
    private int avgRating; //0-5
    private int totalRating;
    private String description;
    private int bookCount;

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getAuthor() {
        return author;
    }

    public void setAuthor(ArrayList<String> author) {
        this.author = author;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    public int getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(int avgRating) {
        this.avgRating = avgRating;
    }

    public int getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(int totalRating) {
        this.totalRating = totalRating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBookCount() {
        return bookCount;
    }

    public void setBookCount(int bookCount) {
        this.bookCount = bookCount;
    }

    public void setBook(Book book) {
        this.publisher = book.getPublisher();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.publishedDate = book.getPublishedDate();
        this.language = book.getLanguage();
        this.isbn = book.getIsbn();
        this.pages = book.getPages();
        this.categories = book.getCategories();
        this.avgRating = book.getAvgRating();
        this.totalRating = book.getTotalRating();
        this.description = book.getDescription();
        this.bookCount = book.getBookCount();
    }

    public Book() {

    }

    public Book(Book book) {
        setBook(book);
    }
}
