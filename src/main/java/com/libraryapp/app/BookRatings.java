package com.libraryapp.app;

public class BookRatings {

    private float averageRating;
    private int totalRatings;
    private int totalWorkRatings;
    private int totalOneStarRating;
    private int totalTwoStarRating;
    private int totalThreeStarRating;
    private int totalFourStarRating;
    private int totalFiveStarRating;

    public BookRatings(float averageRating, int totalRatings, int totalWorkRatings, int totalOneStarRating, int totalTwoStarRating, int totalThreeStarRating, int totalFourStarRating, int totalFiveStarRating) {
        this.averageRating = averageRating;
        this.totalRatings = totalRatings;
        this.totalWorkRatings = totalWorkRatings;
        this.totalOneStarRating = totalOneStarRating;
        this.totalTwoStarRating = totalTwoStarRating;
        this.totalThreeStarRating = totalThreeStarRating;
        this.totalFourStarRating = totalFourStarRating;
        this.totalFiveStarRating = totalFiveStarRating;
    }

    public float getAverageRating() {
        return averageRating;
    }

    public int getTotalRatings() {
        return totalRatings;
    }

    public int getTotalWorkRatings() {
        return totalWorkRatings;
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
}
