package com.libraryapp.app;

public class BookIdentification {

    private int bookID;
    private int goodreadsBookID;
    private int bestBookID;
    private int workID;
    private String ISBN;
    private String ISBN13;

    public BookIdentification(int bookID, int goodreadsBookID, int bestBookID, int workID, String ISBN, String ISBN13) {
        this.bookID = bookID;
        this.goodreadsBookID = goodreadsBookID;
        this.bestBookID = bestBookID;
        this.workID = workID;
        this.ISBN = ISBN;
        this.ISBN13 = ISBN13;
    }

    public int getBookID() {
        return bookID;
    }

    public int getGoodreadsBookID() {
        return goodreadsBookID;
    }

    public int getBestBookID() {
        return bestBookID;
    }

    public int getWorkID() {
        return workID;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getISBN13() {
        return ISBN13;
    }
}
