package com.libraryapp.app;

public class Book implements Comparable<Book> {
    private int bookID;
    private int goodreadsBookID;
    private int bestBookID;
    private int workID;
    private String ISBN;
    private String ISBN13;
    private String authors;
    private short originalPublicationYear;
    private String originalTitle;
    private String title;
    private String languageCode;
    private float averageRating;
    private int totalNumberOfBooks;
    private int totalRatings;
    private int totalWorkRatings;
    private int totalWorkTextReviews;
    private int totalOneStarRating;
    private int totalTwoStarRating;
    private int totalThreeStarRating;
    private int totalFourStarRating;
    private int totalFiveStarRating;
    private String standardSizedImageURL;
    private String smallSizedImageURL;

    public Book(int bookID, int goodreadsBookID, int bestBookID, int workID, int totalNumberOfBooks, String ISBN, String ISBN13, String authors, short originalPublicationYear, String originalTitle, String title, String languageCode, float averageRating, int totalRatings, int totalWorkRatings, int totalWorkTextReviews, int totalOneStarRating, int totalTwoStarRating, int totalThreeStarRating, int totalFourStarRating, int totalFiveStarRating, String standardSizedImageURL, String smallSizedImageURL) {
        this.bookID = bookID;
        this.goodreadsBookID = goodreadsBookID;
        this.bestBookID = bestBookID;
        this.workID = workID;
        this.ISBN = ISBN;
        this.ISBN13 = ISBN13;
        this.authors = authors;
        this.originalPublicationYear = originalPublicationYear;
        this.originalTitle = originalTitle;
        this.title = title;
        this.languageCode = languageCode;
        this.averageRating = averageRating;
        this.totalNumberOfBooks = totalNumberOfBooks;
        this.totalRatings = totalRatings;
        this.totalWorkRatings = totalWorkRatings;
        this.totalWorkTextReviews = totalWorkTextReviews;
        this.totalOneStarRating = totalOneStarRating;
        this.totalTwoStarRating = totalTwoStarRating;
        this.totalThreeStarRating = totalThreeStarRating;
        this.totalFourStarRating = totalFourStarRating;
        this.totalFiveStarRating = totalFiveStarRating;
        this.standardSizedImageURL = standardSizedImageURL;
        this.smallSizedImageURL = smallSizedImageURL;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public int getGoodreadsBookID() {
        return goodreadsBookID;
    }

    public void setGoodreadsBookID(int goodreadsBookID) {
        this.goodreadsBookID = goodreadsBookID;
    }

    public int getBestBookID() {
        return bestBookID;
    }

    public void setBestBookID(int bestBookID) {
        this.bestBookID = bestBookID;
    }

    public int getWorkID() {
        return workID;
    }

    public void setWorkID(int workID) {
        this.workID = workID;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getISBN13() {
        return ISBN13;
    }

    public void setISBN13(String ISBN13) {
        this.ISBN13 = ISBN13;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public short getOriginalPublicationYear() {
        return originalPublicationYear;
    }

    public void setOriginalPublicationYear(short originalPublicationYear) {
        this.originalPublicationYear = originalPublicationYear;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }

    public int getTotalNumberOfBooks() {
        return totalNumberOfBooks;
    }

    public void setTotalNumberOfBooks(int totalNumberOfBooks) {
        this.totalNumberOfBooks = totalNumberOfBooks;
    }

    public int getTotalRatings() {
        return totalRatings;
    }

    public void setTotalRatings(int totalRatings) {
        this.totalRatings = totalRatings;
    }

    public int getTotalWorkRatings() {
        return totalWorkRatings;
    }

    public void setTotalWorkRatings(int totalWorkRatings) {
        this.totalWorkRatings = totalWorkRatings;
    }

    public int getTotalWorkTextReviews() {
        return totalWorkTextReviews;
    }

    public void setTotalWorkTextReviews(int totalWorkTextReviews) {
        this.totalWorkTextReviews = totalWorkTextReviews;
    }

    public int getTotalOneStarRating() {
        return totalOneStarRating;
    }

    public void setTotalOneStarRating(int totalOneStarRating) {
        this.totalOneStarRating = totalOneStarRating;
    }

    public int getTotalTwoStarRating() {
        return totalTwoStarRating;
    }

    public void setTotalTwoStarRating(int totalTwoStarRating) {
        this.totalTwoStarRating = totalTwoStarRating;
    }

    public int getTotalThreeStarRating() {
        return totalThreeStarRating;
    }

    public void setTotalThreeStarRating(int totalThreeStarRating) {
        this.totalThreeStarRating = totalThreeStarRating;
    }

    public int getTotalFourStarRating() {
        return totalFourStarRating;
    }

    public void setTotalFourStarRating(int totalFourStarRating) {
        this.totalFourStarRating = totalFourStarRating;
    }

    public int getTotalFiveStarRating() {
        return totalFiveStarRating;
    }

    public void setTotalFiveStarRating(int totalFiveStarRating) {
        this.totalFiveStarRating = totalFiveStarRating;
    }

    public String getStandardSizedImageURL() {
        return standardSizedImageURL;
    }

    public void setStandardSizedImageURL(String standardSizedImageURL) {
        this.standardSizedImageURL = standardSizedImageURL;
    }

    public String getSmallSizedImageURL() {
        return smallSizedImageURL;
    }

    public void setSmallSizedImageURL(String smallSizedImageURL) {
        this.smallSizedImageURL = smallSizedImageURL;
    }

    @Override
    public int compareTo(Book otherBook) {
        if (this.originalPublicationYear > otherBook.originalPublicationYear) {
            return 1;
        } else {
            return 0;
        }
    }
}
