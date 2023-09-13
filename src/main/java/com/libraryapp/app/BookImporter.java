package com.libraryapp.app;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class BookImporter {

    private static List<Book> books;
    public static List<String> importBooksFromCSV(String pathToFile) {
        List<String> lineText = new ArrayList<>();
        try {
            lineText = Files.readAllLines(
                    Paths.get(pathToFile)
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

            if(splitupLineVals[6].length() > 0) {
                ISBN13 =  new BigDecimal(splitupLineVals[6]).toPlainString();
            } else {
                ISBN13 = splitupLineVals[6];
            }

            authors = splitupLineVals[7].trim().replaceAll("\\s+", " ");

            if(splitupLineVals[8].length() > 0) {
                originalPublicationYear = new BigDecimal(splitupLineVals[8]).shortValueExact();
            } else {
                originalPublicationYear = 0;
            }

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
}
