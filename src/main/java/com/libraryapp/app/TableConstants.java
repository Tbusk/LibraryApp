package com.libraryapp.app;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class TableConstants {

    public static final List<String> COLUMNS = List.of(
            "Current Title",
            "Original Title",
            "Author",
            "Average Rating",
            "Original Publication Year",
            "ISBN13",
            "ISBN",
            "Small Cover Image",
            "Book ID",
            "Good Reads Book ID",
            "Best Book ID",
            "Work ID",
            "Language Code",
            "Total Number Of Books",
            "Total Ratings",
            "Total Work Ratings",
            "Total Work Text Reviews",
            "Total One Star Reviews",
            "Total Two Star Reviews",
            "Total Three Star Reviews",
            "Total Four Star Reviews",
            "Total Five Star Reviews"
    );

    public static final Set<String> DEFAULT_VISIBLE_COLUMNS = new HashSet<>(List.of(
            "Current Title",
            "Author",
            "Average Rating",
            "Small Cover Image",
            "Original Publication Year",
            "ISBN13"));

    private TableConstants(){}
}
