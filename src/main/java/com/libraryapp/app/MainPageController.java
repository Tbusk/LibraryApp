package com.libraryapp.app;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {
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

    private List books = BookImporter.exportBooksToList(BookImporter.importBooksFromCSV());
    private ObservableList<Book> bookObservableList = FXCollections.observableArrayList(books);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
                        imageView.setFitHeight(146);
                        imageView.setFitWidth(98);
                        setGraphic(imageView);
                    }
                }
            };
        });
        title.setCellFactory(param -> { return new BookTableCell(); });
        author.setCellFactory(param -> { return new BookTableCell(); });
        booksTable.setItems(bookObservableList);
    }
}