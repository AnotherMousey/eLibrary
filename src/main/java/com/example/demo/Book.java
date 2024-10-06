package com.example.demo;

public class Book {
    private String title;
    private String author;
    private int publishedDate;
    private String language;
    private String isbn;
    private int pages;
    private String[] categories;
    private int avgRating; //0-5
    private int totalRating;
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(int publishedDate) {
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

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
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

    public Book(String title, String author, int publishedDate, String language, String isbn, int pages,
                String[] categories, int avgRating, int totalRating, String description) {
        this.title = title;
        this.author = author;
        this.publishedDate = publishedDate;
        this.language = language;
        this.isbn = isbn;
        this.pages = pages;
        this.categories = categories;
        this.avgRating = avgRating;
        this.totalRating = totalRating;
        this.description = description;
    }
}
