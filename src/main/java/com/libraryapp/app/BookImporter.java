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
        for (String linesText : lineText) {
            if (linesText.startsWith("book_id")) {
                continue;
            }
            String[] splitupLineVals = linesText.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

            bookID = Integer.parseInt(splitupLineVals[0]);
            goodreadsBookID = Integer.parseInt(splitupLineVals[1]);
            bestBookID = Integer.parseInt(splitupLineVals[2]);
            workID = Integer.parseInt((splitupLineVals[3]));
            totalNumberOfBooks = Integer.parseInt(splitupLineVals[4]);
            ISBN = splitupLineVals[5];

            if (splitupLineVals[6].length() > 0) {
                ISBN13 = new BigDecimal(splitupLineVals[6]).toPlainString();
            } else {
                ISBN13 = splitupLineVals[6];
            }

            authors = splitupLineVals[7].trim().replaceAll("\\s+", " ");

            if (splitupLineVals[8].length() > 0) {
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

            if (authors.startsWith("\"")) {
                authors = authors.substring(1, authors.length() - 1);
            }

            if (title.startsWith("\"")) {
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

    int binarySearch(ArrayList<String> arr, int x) {
        int left = 0, right = arr.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Check if x is present at mid
            //change these statements so they match with string arguments
            //if (arr.get(mid) == x)
            //return mid;

            // If x greater, ignore left half
            //change these statements so they match with string arguments
            //FIXME : CHANGE STATEMENTS SO THEY MATCH WITH STRING ARGUMENTS
            //if (arr.get(mid) < x)
            //left = mid + 1;

            // If x is smaller, ignore right half
            //else
            right = mid - 1;
        }

        // if we reach here, then element was
        // not present
        return -1;
    }

    public int searchLinear(LinkedList<Book> bookList, String textToLookFor, String type) {
            // Initializing the Linked List

            // Adding elements to the Linked List


            // Element to be searched

            // Initializing the answer to the index -1
            int ans = -1;
            String llElement = "";
            // Traversing through the Linked List
            for (int i = 0; i < bookList.size(); i++) {

                // Eztracting each element in
                // the Linked List
                switch (type) {
                    case "ISBN13" -> llElement = bookList.get(i).getISBN13();
                    case "Book ID" -> llElement = String.valueOf(bookList.get(i).getBookID());
                }

                //FIXME: NEEDS TO BE CHANGED TO INCORPERATE STRING ARGUMENTS
                if (llElement.equals(textToLookFor)) {
                    ans = i;
                    break;

                }
                // Checking if the element is present in the Linked
                // List

            }
        if (ans == -1) {
            System.out.println("Title not found");
            return ans;
        } else {
            System.out.println(
                    "The requested title found in Linked List at " + ans);
            return ans;
        }
    }
}
