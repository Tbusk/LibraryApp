package com.libraryapp.app;

public class Book {

    private BookIdentification bookIdentification;
    private BookRatings bookRatings;
    private String authors;
    private short originalPublicationYear;
    private String originalTitle;
    private String title;
    private int totalWorkTextReviews;
    private String languageCode;
    private int totalNumberOfBooks;
    private String standardSizedImageURL;
    private String smallSizedImageURL;

    public Book(){
        this.title = "N/A";
        this.authors = "N/A";
        this.originalPublicationYear = 2023;
        this.smallSizedImageURL = "https://s.gr-assets.com/assets/nophoto/book/50x75-a91bf249278a81aabab721ef782c4a74.png";
    }

    public Book(BookIdentification bookIdentification, BookRatings bookRatings, String authors, short originalPublicationYear, String originalTitle, String title, int totalWorkTextReviews, String languageCode, int totalNumberOfBooks, String standardSizedImageURL, String smallSizedImageURL) {
        this.bookIdentification = bookIdentification;
        this.bookRatings = bookRatings;
        this.authors = authors;
        this.originalPublicationYear = originalPublicationYear;
        this.originalTitle = originalTitle;
        this.title = title;
        this.totalWorkTextReviews = totalWorkTextReviews;
        this.languageCode = languageCode;
        this.totalNumberOfBooks = totalNumberOfBooks;
        this.standardSizedImageURL = standardSizedImageURL;
        this.smallSizedImageURL = smallSizedImageURL;
    }

    public BookIdentification getIdentification() {
        return this.bookIdentification;
    }

    public BookRatings getBookRatings() {
        return this.bookRatings;
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

    public int getTotalNumberOfBooks() {
        return totalNumberOfBooks;
    }

    public String getStandardSizedImageURL() {
        return standardSizedImageURL;
    }

    public String getSmallSizedImageURL() {
        return smallSizedImageURL;
    }

    public int getTotalWorkTextReviews() {
        return totalWorkTextReviews;
    }
}
