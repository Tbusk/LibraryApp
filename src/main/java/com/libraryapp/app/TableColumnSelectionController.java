package com.libraryapp.app;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;

import java.util.*;

public class TableColumnSelectionController {

    @FXML private ListView<CheckBox> checklist;
    List<String> tableColumns = List.of(
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
    Set<String> defaultColumns = new HashSet<>(List.of(
            "Current Title",
            "Author",
            "Average Rating",
            "Small Cover Image",
            "Original Publication Year",
            "ISBN13"));
    HashMap<String, TableColumn<?, ?>> tableCol = new HashMap<>();
    private MainPageController mainPageController;

    protected void setMainPageController(MainPageController mainPageController) {
        this.mainPageController = mainPageController;
    }

    public void initialize() {

        for (String tableColumn : tableColumns) {
            CheckBox checkBox = new CheckBox(tableColumn);
            checklist.getItems().add(checkBox);

            if(defaultColumns.contains(tableColumn)) {
                checkBox.setSelected(true);
            }

            checkBox.setOnAction(new EventHandler<ActionEvent>() {
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
