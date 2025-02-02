package com.libraryapp.app;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import javafx.scene.control.Alert;

public class BookImporter {

	private Alert messagePopup = new Alert(Alert.AlertType.INFORMATION);
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

            BookIdentification bookIdentification = new BookIdentification(bookID, goodreadsBookID, bestBookID, workID, ISBN, ISBN13);
            BookRatings bookRatings = new BookRatings(averageRating, totalRatings, totalWorkRatings, totalOneStarRating, totalTwoStarRating, totalThreeStarRating, totalFourStarRating, totalFiveStarRating);

            book = new Book(bookIdentification, bookRatings, authors, originalPublicationYear, originalTitle, title, totalWorkTextReviews, languageCode, totalNumberOfBooks, standardSizedImageURL, smallSizedImageURL);
            books.add(book);
            
            
        }
        return books;
    }

    public int searchBinary(ArrayList<Book> bookList, String textToLookFor, String type) {

        // Initializing the answer to the index -1
        int ans = -1;
        String llElement = "";
        // Traversing through the Linked List
        for (int i = 0; i < bookList.size(); i++) {

            // Eztracting each element in
            // the Array List
            switch (type) {
                case "ISBN13" -> llElement = bookList.get(i).getIdentification().getISBN13();
                case "Book ID" -> llElement = String.valueOf(bookList.get(i).getIdentification().getBookID());
                case "Title" -> llElement = bookList.get(i).getTitle();
                case "Author" -> llElement = bookList.get(i).getAuthors();
                case "ISBN" -> llElement = bookList.get(i).getIdentification().getISBN();
                case "Average Rating" -> llElement = String.valueOf(bookList.get(i).getBookRatings().getAverageRating());
                case "Original Publication Year" -> llElement = String.valueOf(bookList.get(i).getOriginalPublicationYear());
            }

            if (llElement.equals(textToLookFor)) {
                ans = i;
                break;

            }
            // Checking if the element is present in the Linked
            // List

        }
    if (ans == -1) {
    	messagePopup.setTitle("Requested info not found.");
        messagePopup.setContentText("Requested info not found.");
        messagePopup.showAndWait();
        return ans;
    } else {
        
    	//message pop-out that counts the number of result
    	System.out.println(
                "The requested title found in Linked List at " + ans);
        return ans;
    }
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
                    case "ISBN13" -> llElement = bookList.get(i).getIdentification().getISBN13();
                    case "Book ID" -> llElement = String.valueOf(bookList.get(i).getIdentification().getBookID());
                    case "Title" -> llElement = bookList.get(i).getTitle();
                    case "Author" -> llElement = bookList.get(i).getAuthors();
                    case "ISBN" -> llElement = bookList.get(i).getIdentification().getISBN();
                    case "Average Rating" -> llElement = String.valueOf(bookList.get(i).getBookRatings().getAverageRating());
                    case "Original Publication Year" -> llElement = String.valueOf(bookList.get(i).getOriginalPublicationYear());
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
