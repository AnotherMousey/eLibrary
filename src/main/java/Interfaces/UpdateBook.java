package Interfaces;

import java.util.ArrayList;

public interface UpdateBook {
    public void updateTitle(String title);
    public void updateAuthor(ArrayList<String> author);
    public void updateISBN(String ISBN);
    public void updateYear(int year);
    public void updatePublisher(String publisher);
    public void updatePublishedDate(String publishedDate);
    public void updateLanguage(String language);
    public void updatePages(int pages);
    public void updateCategories(ArrayList<String> categories);
    public void updateDescription(String description);
    public void updateRating(int rating);
    public void updateAverageRating(int averageRating);
}
