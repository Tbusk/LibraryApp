package com.libraryapp.app;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;

public class TableColumnSelectionController {

    @FXML private ListView<CheckBox> checklist;

    private MainPageController mainPageController;

    protected void setMainPageController(MainPageController mainPageController) {
        this.mainPageController = mainPageController;
    }

    public void initialize() {

        for (String tableColumn : TableConstants.COLUMNS) {
            CheckBox checkBox = new CheckBox(tableColumn);
            checklist.getItems().add(checkBox);

            if(TableConstants.DEFAULT_VISIBLE_COLUMNS.contains(tableColumn)) {
                checkBox.setSelected(true);
            }

            checkBox.setOnAction(new EventHandler<>() {
                @Override
                public void handle(ActionEvent event) {
                    switch(checkBox.getText()) {
                        case "Current Title" -> mainPageController.setTableColumnVisibility(mainPageController.title, checkBox.isSelected());
                        case "Author" -> mainPageController.setTableColumnVisibility(mainPageController.author, checkBox.isSelected());
                        case "Average Rating" -> mainPageController.setTableColumnVisibility(mainPageController.averageRating, checkBox.isSelected());
                        case "Small Cover Image" -> mainPageController.setTableColumnVisibility(mainPageController.smallSizedImage, checkBox.isSelected());
                        case "Original Publication Year" -> mainPageController.setTableColumnVisibility(mainPageController.originalPublicationYear, checkBox.isSelected());
                        case "ISBN13" -> mainPageController.setTableColumnVisibility(mainPageController.ISBN13, checkBox.isSelected());
                        case "ISBN" -> mainPageController.setTableColumnVisibility(mainPageController.ISBN, checkBox.isSelected());
                        case "Original Title" -> mainPageController.setTableColumnVisibility(mainPageController.oldTitle, checkBox.isSelected());
                        case "Book ID" -> mainPageController.setTableColumnVisibility(mainPageController.bookID, checkBox.isSelected());
                        case "Good Reads Book ID" -> mainPageController.setTableColumnVisibility(mainPageController.goodReadsBookID, checkBox.isSelected());
                        case "Best Book ID" -> mainPageController.setTableColumnVisibility(mainPageController.bestBookID, checkBox.isSelected());
                        case "Work ID" -> mainPageController.setTableColumnVisibility(mainPageController.workID, checkBox.isSelected());
                        case "Language Code" -> mainPageController.setTableColumnVisibility(mainPageController.languageCode, checkBox.isSelected());
                        case "Total Number Of Books" -> mainPageController.setTableColumnVisibility(mainPageController.totalNumberOfBooks, checkBox.isSelected());
                        case "Total Ratings" -> mainPageController.setTableColumnVisibility(mainPageController.totalRatings, checkBox.isSelected());
                        case "Total Work Ratings" -> mainPageController.setTableColumnVisibility(mainPageController.totalWorkRatings, checkBox.isSelected());
                        case "Total Work Text Reviews" -> mainPageController.setTableColumnVisibility(mainPageController.totalWorkTextReviews, checkBox.isSelected());
                        case "Total One Star Reviews" -> mainPageController.setTableColumnVisibility(mainPageController.totalOneStarReviews, checkBox.isSelected());
                        case "Total Two Star Reviews" -> mainPageController.setTableColumnVisibility(mainPageController.totalTwoStarReviews, checkBox.isSelected());
                        case "Total Three Star Reviews" -> mainPageController.setTableColumnVisibility(mainPageController.totalThreeStarReviews, checkBox.isSelected());
                        case "Total Four Star Reviews" -> mainPageController.setTableColumnVisibility(mainPageController.totalFourStarReviews, checkBox.isSelected());
                        case "Total Five Star Reviews" -> mainPageController.setTableColumnVisibility(mainPageController.totalFiveStarReviews, checkBox.isSelected());
                    }
                }
            });

        }
    }
}
