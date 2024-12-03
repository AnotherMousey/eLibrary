package APIManagement.BookManagement;

import java.util.ArrayList;
import java.util.Date;

public class Book {
    private String title;
    private String publishedDate;
    private String publisher;
    private String language;
    private String isbn;
    private int pages;
    private ArrayList<String> categories;
    private int avgRating; //0-5
    private int totalRating;
    private String description;
    private int quantity;
    private String author;
    private Date issueDate;
    private Date returnDate;

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
        this.quantity = book.getQuantity();
        this.issueDate = book.getIssueDate();
        this.returnDate = book.getReturnDate();
    }

    public Book (String isbn, String title, String author, int quantity) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.quantity = quantity;
    }

    public Book (String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }

    public Book (String isbn, String title, Date issueDate, Date returnDate) {
        this.isbn = isbn;
        this.title = title;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }

    public Book (String isbn, String title, String author, Date issueDate, Date returnDate) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }

    public Book() {

    }

    public Book(Book book) {
        setBook(book);
    }
}
