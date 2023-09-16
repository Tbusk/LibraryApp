package com.libraryapp.app;

public class Book {
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

    public Book(){
        this.title = "N/A";
        this.authors = "N/A";
        this.ISBN13 = "00000";
        this.originalPublicationYear = 2023;
        this.smallSizedImageURL = "https://s.gr-assets.com/assets/nophoto/book/50x75-a91bf249278a81aabab721ef782c4a74.png";
    }

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

    public String getAuthors() {
        return authors;
    }

    public short getOriginalPublicationYear() {
        return originalPublicationYear;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getTitle() {
        return title;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public float getAverageRating() {
        return averageRating;
    }

    public int getTotalNumberOfBooks() {
        return totalNumberOfBooks;
    }

    public int getTotalRatings() {
        return totalRatings;
    }

    public int getTotalWorkRatings() {
        return totalWorkRatings;
    }

    public int getTotalWorkTextReviews() {
        return totalWorkTextReviews;
    }

    public int getTotalOneStarRating() {
        return totalOneStarRating;
    }

    public int getTotalTwoStarRating() {
        return totalTwoStarRating;
    }

    public int getTotalThreeStarRating() {
        return totalThreeStarRating;
    }

    public int getTotalFourStarRating() {
        return totalFourStarRating;
    }

    public int getTotalFiveStarRating() {
        return totalFiveStarRating;
    }

    public String getStandardSizedImageURL() {
        return standardSizedImageURL;
    }

    public String getSmallSizedImageURL() {
        return smallSizedImageURL;
    }
}
