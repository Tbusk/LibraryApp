package com.libraryapp.app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {

    private FileChooser fileChooser = new FileChooser();
    private int rowsPerPage = 10;
    FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
    private List books;
    private ObservableList<Book> bookObservableList;
    private FilteredList<Book> filteredData;

    private Stage stage;

    @FXML
    Pagination pagination = new Pagination();
    @FXML
    private Label headerText;
    @FXML
    private TableView<Book> booksTable;
    @FXML
    private TableColumn<Book, String> title;
    @FXML
    private TableColumn<Book, String> author;
    @FXML
    private TableColumn<Book, Float> averageRating;
    @FXML
    private TableColumn<Book, Short> originalPublicationYear;
    @FXML
    private TableColumn<Book, String> ISBN13;
    @FXML
    private TableColumn<Book, String> smallSizedImage;
    @FXML
    private Button importFromFileButton;
    @FXML
    private Button moveAheadTenPagesButton;
    @FXML
    private Button moveBackTenPagesButton;

    @FXML
    private void moveBackTenPages() {
        if ((pagination.getCurrentPageIndex() - 10) >= 0) {
            pagination.setCurrentPageIndex(pagination.getCurrentPageIndex() - 10);
        } else {
            pagination.setCurrentPageIndex(0);
        }
    }

    @FXML
    private void moveAheadTenPages() {
        if ((pagination.getCurrentPageIndex() + 10) <= pagination.getPageCount()) {
            pagination.setCurrentPageIndex(pagination.getCurrentPageIndex() + 10);
        } else {
            pagination.setCurrentPageIndex(pagination.getPageCount());
        }
    }


    @FXML
    private void importFromFile(ActionEvent event) {
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null && !(booksTable.getItems().equals(null))) {
            booksTable.refresh();
            books = BookImporter.exportBooksToList(BookImporter.importBooksFromCSV(selectedFile.getAbsolutePath()));
            bookObservableList = FXCollections.observableArrayList(books);
            filteredData = new FilteredList<>(bookObservableList);
            filteredData.setPredicate(s -> filteredData.indexOf(s) < rowsPerPage);

            pagination.setPageCount((int) Math.ceil((double) bookObservableList.size() / rowsPerPage));

            title.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
            author.setCellValueFactory(new PropertyValueFactory<Book, String>("authors"));
            averageRating.setCellValueFactory(new PropertyValueFactory<Book, Float>("averageRating"));
            originalPublicationYear.setCellValueFactory(new PropertyValueFactory<Book, Short>("originalPublicationYear"));
            ISBN13.setCellValueFactory(new PropertyValueFactory<Book, String>("ISBN13"));
            smallSizedImage.setCellValueFactory(new PropertyValueFactory<Book, String>("smallSizedImageURL"));
            smallSizedImage.setCellFactory(column -> {
                return new TableCell<Book, String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            ImageView imageView = new ImageView(item);
                            imageView.setPreserveRatio(true);
                            setGraphic(imageView);
                        }
                    }
                };
            });

            pagination.setPageFactory(pageIndex -> {
                int fromIndex = pageIndex * rowsPerPage;
                int toIndex = Math.min(fromIndex + rowsPerPage, filteredData.size());
                booksTable.setItems(FXCollections.observableArrayList(filteredData.subList(fromIndex, toIndex)));
                return booksTable;
            });
            pagination.setPageCount((int) Math.ceil((double) bookObservableList.size() / rowsPerPage));

            pagination.setVisible(true);
            moveAheadTenPagesButton.setVisible(true);
            moveBackTenPagesButton.setVisible(true);

        } else {
            if (bookObservableList != null) {
                bookObservableList.clear();
                pagination.setVisible(false);
                moveAheadTenPagesButton.setVisible(false);
                moveBackTenPagesButton.setVisible(false);

                booksTable.refresh();
            }
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (bookObservableList == null) {
            pagination.setVisible(false);
            moveAheadTenPagesButton.setVisible(false);
            moveBackTenPagesButton.setVisible(false);
        }
        fileChooser.getExtensionFilters().add(extensionFilter);
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + "/Downloads"));
    }
}