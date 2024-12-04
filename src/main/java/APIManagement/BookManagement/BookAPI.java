package APIManagement.BookManagement;

import java.io.*;
import java.net.*;
import java.util.*;

import javafx.scene.control.Alert;
import org.json.simple.*;
import org.json.simple.parser.*;
import APIManagement.API;

public class BookAPI implements API {
    private static final String googleBook = "https://www.googleapis.com/books/v1/volumes?";
    private static final String personalKey = "AIzaSyCHkwZjMHLM8ZbtSvqJ4TRRqPxSUT4inuQ";
    private String API;
    private static URL url;

    private void encode(String query) {
        this.API = googleBook + "q=" + query + "&key=" + personalKey;
    }

    private int connect(BookQuery bookQuery) throws IOException {
        encode(bookQuery.getQuery());
        url = new URL(API);

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.connect();

        return con.getResponseCode();
    }

    /**
     * Get book info via google book
     * @param bookQuery querytype is 1 of the following: intitle, inauthor, inpublisher, subject, isbn, lccn, oclc
     * see more on https://developers.google.com/books/docs/v1/using#WorkingVolumes
     * @return arraylist of books satisfied
     */
    public ArrayList<Book> getBookInfo(BookQuery bookQuery) throws IOException, ParseException {
        ArrayList<Book> bookshelf = new ArrayList<>();

        int responseCode = connect(bookQuery);
        if(responseCode == HttpURLConnection.HTTP_OK) {
            System.out.println("Status: Connection success");

            StringBuilder fileResponse = new StringBuilder();
            Scanner scanner = new Scanner(url.openStream());

            while(scanner.hasNext()) {
                fileResponse.append(scanner.nextLine());
            }

            String file = fileResponse.toString();

            scanner.close();

            JSONParser parser = new JSONParser();
            JSONObject dataFile = (JSONObject) parser.parse(file);
            JSONArray data = (JSONArray) dataFile.get("items");
            if(data == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Book does not exist!");
                alert.showAndWait();
                return bookshelf;
            }
            for (Object datum : data) {
                Book newBook = new Book();
                JSONObject currentBook = (JSONObject) datum;
                JSONObject volumeInfo = (JSONObject) currentBook.get("volumeInfo");

                //get Title of the book
                newBook.setTitle(volumeInfo.get("title").toString());

                //get author if exists
                if(volumeInfo.containsKey("authors")) {
                    JSONArray authors = (JSONArray) volumeInfo.get("authors");
                    newBook.setAuthor(authors.get(0).toString());
                }

                //get isbn if exists
                if(volumeInfo.containsKey("industryIdentifiers")) {
                    JSONArray isbns = (JSONArray) volumeInfo.get("industryIdentifiers");
                    for (Object isbn : isbns) {
                        JSONObject isbnInfo = (JSONObject) isbn;
                        if (isbnInfo.get("type").toString().equals("ISBN_10")) {
                            newBook.setIsbn(isbnInfo.get("identifier").toString());
                        }
                    }
                }

                bookshelf.add(newBook);
            }
        }
        else {
            System.out.println("Status: Connection failed");
        }
        return bookshelf;
    }
}


