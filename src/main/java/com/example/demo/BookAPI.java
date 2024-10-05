package com.example.demo;

import java.io.*;
import java.lang.reflect.Array;
import java.net.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class BookAPI{
    private static JSONObject data;
    private int countBook = 0;

    private static String encode(String keyword, String query) {
        String googleBook = "https://www.googleapis.com/books/v1/volumes?";
        String personalKey = "AIzaSyCHkwZjMHLM8ZbtSvqJ4TRRqPxSUT4inuQ";
        return googleBook + "q=" + keyword + "+" + query + ":keyes&key=" + personalKey;
    }

    /**
     * Get book info via google book
     * @param keyword keyword
     * @param query 1 of the following: intitle, inauthor, inpublisher, subject, isbn, lccn, oclc
     * see more on https://developers.google.com/books/docs/v1/using#WorkingVolumes
     * @return array of books satisfied
     */
    public static ArrayList<Book> getBookInfo(String keyword, String query) throws IOException, ParseException {
        ArrayList<Book> bookshelf = new ArrayList<>();

        String API = encode(keyword, query);
        URL url = new URL(API);

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.connect();

        int responseCode = con.getResponseCode();
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
            data = (JSONObject) parser.parse(file);
        }
        else {
            System.out.println("Status: Connection failed");
        }
    }
}


