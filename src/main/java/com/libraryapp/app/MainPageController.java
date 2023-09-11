package com.libraryapp.app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainPageController implements Initializable{
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

    private List books = BookImporter.exportBooksToList(BookImporter.importBooksFromCSV());
    private ObservableList<Book> bookObservableList = FXCollections.observableArrayList(books);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        author.setCellValueFactory(new PropertyValueFactory<Book, String>("authors"));
        averageRating.setCellValueFactory(new PropertyValueFactory<Book, Float>("averageRating"));
        originalPublicationYear.setCellValueFactory(new PropertyValueFactory<Book, Short>("originalPublicationYear"));
        ISBN13.setCellValueFactory(new PropertyValueFactory<Book, String>("ISBN13"));
        booksTable.setItems(bookObservableList);
    }
}