package com.libraryapp.app;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import java.util.ArrayList;
import java.util.List;

public class TableColumnSelectionController {

    @FXML private ListView<CheckBox> checklist;
    List<String> tableColumns = new ArrayList<>();
    List<String> defaultColumns = new ArrayList<>();
    private MainPageController mainPageController;

    protected void setMainPageController(MainPageController mainPageController) {
        this.mainPageController = mainPageController;
    }

    public void initialize() {
        getCurrentTableColumns();
        for (String tableColumn : tableColumns) {
            CheckBox checkBox = new CheckBox(tableColumn);
            checklist.getItems().add(checkBox);

            switch(tableColumn) {
                case "Current Title",
                        "Author",
                        "Average Rating",
                        "Small Cover Image",
                        "Original Publication Year",
                        "ISBN13"  -> checkBox.setSelected(true);
            }

            checkBox.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    switch(checkBox.getText()) {
                        case "Current Title" -> mainPageController.setCurrentTitleColumnVisibility(checkBox.isSelected());
                        case "Author" -> mainPageController.setAuthorColumnVisibility(checkBox.isSelected());
                        case "Average Rating" -> mainPageController.setAverageRatingColumnVisibility(checkBox.isSelected());
                        case "Small Cover Image" -> mainPageController.setSmallSizedImageColumnVisibility(checkBox.isSelected());
                        case "Original Publication Year" -> mainPageController.setOriginalPublicationYearColumnVisibility(checkBox.isSelected());
                        case "ISBN13" -> mainPageController.setISBN13ColumnVisibility(checkBox.isSelected());
                        case "ISBN" -> mainPageController.setISBNColumnVisibility(checkBox.isSelected());
                        case "Original Title" -> mainPageController.setOldTitleColumnVisibility(checkBox.isSelected());
                        case "Book ID" -> mainPageController.setBookIDColumnVisibility(checkBox.isSelected());
                        case "Good Reads Book ID" -> mainPageController.setGoodReadsBookIDColumnVisibility(checkBox.isSelected());
                        case "Best Book ID" -> mainPageController.setBestBookIDColumnVisibility(checkBox.isSelected());
                        case "Work ID" -> mainPageController.setWorkIDColumnVisibility(checkBox.isSelected());
                        case "Language Code" -> mainPageController.setLanguageCodeColumnVisibility(checkBox.isSelected());
                        case "Total Number Of Books" -> mainPageController.setBookTotalColumnVisibility(checkBox.isSelected());
                        case "Total Ratings" -> mainPageController.setTotalRatingsColumnVisibility(checkBox.isSelected());
                        case "Total Work Ratings" -> mainPageController.setTotalWorkRatingsColumnVisibility(checkBox.isSelected());
                        case "Total Work Text Reviews" -> mainPageController.setTotalWorkTextReviewsColumnVisibility(checkBox.isSelected());
                        case "Total One Star Reviews" -> mainPageController.setTotalOneStarReviewsColumnVisibility(checkBox.isSelected());
                        case "Total Two Star Reviews" -> mainPageController.setTotalTwoStarReviewsColumnVisibility(checkBox.isSelected());
                        case "Total Three Star Reviews" -> mainPageController.setTotalThreeStarReviewsColumnVisibility(checkBox.isSelected());
                        case "Total Four Star Reviews" -> mainPageController.setTotalFourStarReviewsColumnVisibility(checkBox.isSelected());
                        case "Total Five Star Reviews" -> mainPageController.setTotalFiveStarReviewsColumnVisibility(checkBox.isSelected());
                    }
                }
            });

        }
    }

    public void getCurrentTableColumns() {
        tableColumns.clear();
        tableColumns.add("Current Title");
        tableColumns.add("Original Title");
        tableColumns.add("Author");
        tableColumns.add("Average Rating");
        tableColumns.add("Original Publication Year");
        tableColumns.add("ISBN13");
        tableColumns.add("ISBN");
        tableColumns.add("Small Cover Image");
        tableColumns.add("Book ID");
        tableColumns.add("Good Reads Book ID");
        tableColumns.add("Best Book ID");
        tableColumns.add("Work ID");
        tableColumns.add("Language Code");
        tableColumns.add("Total Number Of Books");
        tableColumns.add("Total Ratings");
        tableColumns.add("Total Work Ratings");
        tableColumns.add("Total Work Text Reviews");
        tableColumns.add("Total One Star Reviews");
        tableColumns.add("Total Two Star Reviews");
        tableColumns.add("Total Three Star Reviews");
        tableColumns.add("Total Four Star Reviews");
        tableColumns.add("Total Five Star Reviews");
    }


}
