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
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {

    private FileChooser fileChooser = new FileChooser();
    private int rowsPerPage = 5;
    FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");


    private List books;
    private ObservableList<Book> bookObservableList;

    @FXML
    Pagination pagination = new Pagination();
    private FilteredList<Book> filteredData;

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
    private TableColumn<Book, String> standardSizedImage;
    @FXML
    private Button importFromFileButton;


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
            standardSizedImage.setCellValueFactory(new PropertyValueFactory<Book, String>("standardSizedImageURL"));
            standardSizedImage.setCellFactory(column -> {
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

            title.setCellFactory(param -> {
                return new BookTableCell();
            });
            author.setCellFactory(param -> {
                return new BookTableCell();
            });

            pagination.setPageFactory(pageIndex -> {
                int fromIndex = pageIndex * rowsPerPage;
                int toIndex = Math.min(fromIndex + rowsPerPage, bookObservableList.size());
                booksTable.setItems(FXCollections.observableArrayList(filteredData.subList(fromIndex, toIndex)));
                return booksTable;
            });
            pagination.setPageCount((int) Math.ceil((double) bookObservableList.size() / rowsPerPage));

            booksTable.setItems(filteredData);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        fileChooser.getExtensionFilters().add(extensionFilter);
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")+"/Downloads"));
    }
}