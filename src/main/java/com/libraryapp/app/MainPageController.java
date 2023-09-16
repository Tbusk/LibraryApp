package com.libraryapp.app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
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
    private Alert messagePopup = new Alert(Alert.AlertType.INFORMATION);

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

            books = BookImporter.exportBooksToList(BookImporter.importBooksFromCSV(selectedFile.getAbsolutePath()));
            bookObservableList = FXCollections.observableArrayList(books);
            filteredData = new FilteredList<>(bookObservableList);

            loadTable();
            setupPagination(filteredData);
            booksTable.refresh();

            messagePopup.setTitle("Data Imported Successfully");
            messagePopup.setContentText("File " + selectedFile.getName() + "'s data has been added.");
            messagePopup.showAndWait();


        } else {
            messagePopup.setTitle("Data not Imported");
            messagePopup.setContentText("File selection operation canceled.");
            messagePopup.showAndWait();
        }
    }

    public void loadTable() {
        booksTable.maxHeightProperty().bind(booksTable.heightProperty());
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        author.setCellValueFactory(new PropertyValueFactory<>("authors"));
        averageRating.setCellValueFactory(new PropertyValueFactory<>("averageRating"));
        originalPublicationYear.setCellValueFactory(new PropertyValueFactory<>("originalPublicationYear"));
        ISBN13.setCellValueFactory(new PropertyValueFactory<>("ISBN13"));
        smallSizedImage.setCellValueFactory(new PropertyValueFactory<>("smallSizedImageURL"));
        //convertImageURLToImage();

        System.out.println("Table values set");
    }

    public void setupPagination(FilteredList<Book> filteredData) {
        filteredData.setPredicate(s -> filteredData.indexOf(s) < rowsPerPage);
        pagination.setPageFactory(pageIndex -> {
            int fromIndex = pageIndex * rowsPerPage;
            int toIndex = Math.min(fromIndex + rowsPerPage, filteredData.size());
            booksTable.setItems(FXCollections.observableArrayList(filteredData.subList(fromIndex, toIndex)));
            return booksTable;
        });
        pagination.setPageCount((int) Math.ceil((double) filteredData.size() / rowsPerPage));
        pagination.setVisible(true);
        moveAheadTenPagesButton.setVisible(true);
        moveBackTenPagesButton.setVisible(true);
        System.out.println("Pagination Set up");
    }

    public void convertImageURLToImage() {
        smallSizedImage.setCellFactory(column -> {
            return new TableCell<>() {
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
        System.out.println("Image Converted To URL.");
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        booksTable.autosize();

        pagination.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> {
            System.out.println("Page changed from: " + oldIndex + " to " + newIndex + ".");
        });

        if (bookObservableList == null) {
            System.out.println("observable list is null");
            pagination.setVisible(false);
            moveAheadTenPagesButton.setVisible(false);
            moveBackTenPagesButton.setVisible(false);
        }
        fileChooser.getExtensionFilters().add(extensionFilter);
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + "/Downloads"));
    }
}