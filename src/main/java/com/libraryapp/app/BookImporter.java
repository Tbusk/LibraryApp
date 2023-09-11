package com.libraryapp.app;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class BookImporter {

    private static List<Book> books;
    public static List<String> importBooksFromCSV() {
        List<String> lineText = new ArrayList<>();
        try {
            lineText = Files.readAllLines(
                    Paths.get("books_smallest.csv")
            );
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return lineText;
    }

    public static List<Book> exportBooksToList(List<String> lineText) {
        int bookID, goodreadsBookID, bestBookID, workID, totalNumberOfBooks;
        String ISBN, ISBN13, authors, originalTitle, title, languageCode, standardSizedImageURL, smallSizedImageURL;
        short originalPublicationYear;
        float averageRating;
        int totalRatings, totalWorkRatings, totalWorkTextReviews, totalOneStarRating, totalTwoStarRating;
        int totalThreeStarRating, totalFourStarRating, totalFiveStarRating;

        books = new ArrayList<>();
        Book book;
        for(String linesText : lineText) {
        if(linesText.startsWith("book_id")) {
            continue;
        }
            String[] splitupLineVals = linesText.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

            bookID = Integer.parseInt(splitupLineVals[0]);
            goodreadsBookID = Integer.parseInt(splitupLineVals[1]);
            bestBookID = Integer.parseInt(splitupLineVals[2]);
            workID = Integer.parseInt((splitupLineVals[3]));
            totalNumberOfBooks = Integer.parseInt(splitupLineVals[4]);
            ISBN = splitupLineVals[5];
            ISBN13 =  new BigDecimal(splitupLineVals[6]).toPlainString();
            authors = splitupLineVals[7].trim().replaceAll("\\s+", " ");
            originalPublicationYear = new BigDecimal(splitupLineVals[8]).shortValueExact();
            originalTitle = splitupLineVals[9].trim().replaceAll("\\s+", " ");
            title = splitupLineVals[10].trim().replaceAll("\\s+", " ");
            languageCode = splitupLineVals[11].trim().replaceAll("\\s+", " ").toUpperCase();
            averageRating = Float.parseFloat(splitupLineVals[12]);
            totalRatings = Integer.parseInt(splitupLineVals[13]);
            totalWorkRatings = Integer.parseInt(splitupLineVals[14]);
            totalWorkTextReviews = Integer.parseInt(splitupLineVals[15]);
            totalOneStarRating = Integer.parseInt(splitupLineVals[16]);
            totalTwoStarRating = Integer.parseInt(splitupLineVals[17]);
            totalThreeStarRating = Integer.parseInt(splitupLineVals[18]);
            totalFourStarRating = Integer.parseInt(splitupLineVals[19]);
            totalFiveStarRating = Integer.parseInt(splitupLineVals[20]);
            standardSizedImageURL = splitupLineVals[21];
            smallSizedImageURL = splitupLineVals[22];

            if(authors.startsWith("\"")) {
                authors = authors.substring(1, authors.length() - 1);
            }

            if(title.startsWith("\"")) {
                title = title.substring(1, title.length() - 1);
            }

            book = new Book(bookID, goodreadsBookID, bestBookID, workID, totalNumberOfBooks, ISBN, ISBN13, authors,
                    originalPublicationYear, originalTitle, title, languageCode, averageRating, totalRatings,
                    totalWorkRatings, totalWorkTextReviews, totalOneStarRating, totalTwoStarRating, totalThreeStarRating,
                    totalFourStarRating, totalFiveStarRating, standardSizedImageURL, smallSizedImageURL);
            books.add(book);
        }
        return books;
    }

    public void readFromLine(String lineText) {
        String text = "";
            for (int i = 0; i < lineText.length(); i++) {
                if (lineText.charAt(i) == '"') {
                    lineText.indexOf("\"", i);
                } else if (lineText.charAt(i) == ',') {
                    break;
                }
                text += lineText.charAt(i);
            }

    }

    public static void main (String[] args) {
        books =  new ArrayList<>(exportBooksToList(importBooksFromCSV()));
        // books = new LinkedList<>(exportBooksToList(importBooksFromCSV()));
        for(Book book : books) {
            System.out.println("Book ID: " + book.getBookID());
            System.out.println("Goodreads Book ID: " + book.getGoodreadsBookID());
            System.out.println("Best Book ID: " + book.getBestBookID());
            System.out.println("Work ID: " + book.getWorkID());
            System.out.println("Book Count: " + book.getTotalNumberOfBooks());
            System.out.println("ISBN: " + book.getISBN());
            System.out.println("ISBN13: " + book.getISBN13());
            System.out.println("Authors: " + book.getAuthors());
            System.out.println("Original Publication Year: " + book.getOriginalPublicationYear());
            System.out.println("Original Title: " + book.getOriginalTitle());
            System.out.println("Title: " + book.getTitle());
            System.out.println("Language Code: " + book.getLanguageCode());
            System.out.println("Average Rating: " + book.getAverageRating());
            System.out.println("Rating Count: " + book.getTotalRatings());
            System.out.println("Work Ratings Count: " + book.getTotalWorkRatings());
            System.out.println("Work Text Reviews Count: " + book.getTotalWorkTextReviews());
            System.out.println("Total One Star Ratings: " + book.getTotalOneStarRating());
            System.out.println("Total Two Star Ratings: " + book.getTotalTwoStarRating());
            System.out.println("Total Three Star Ratings: " + book.getTotalThreeStarRating());
            System.out.println("Total Four Star Ratings: " + book.getTotalFourStarRating());
            System.out.println("Total Five Star Ratings: " + book.getTotalFiveStarRating());
            System.out.println("Standard Image Size URL: " + book.getStandardSizedImageURL());
            System.out.println("Small Image Size URL: " + book.getSmallSizedImageURL());
            System.out.println();

        }
    }

}
