package com.libraryapp.app;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {

    private final FileChooser fileChooser = new FileChooser();
    private List books;
    private Alert messagePopup = new Alert(Alert.AlertType.INFORMATION);
    private final int ROWS_PER_PAGE = 10;
    FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
    private ObservableList bookObservableList;
    private int pageIndex = ROWS_PER_PAGE - 1;
    private int toIndex = ROWS_PER_PAGE - 1;
    private int fromIndex = 0;
    private long importtime = 0;
    private File selectedFile;
    int timeTaken = 0;
    
    

    //variables for system performance 
    private long startTime = 0;
    private long endTime   = 0;
    
    @FXML private Label headerText;
    @FXML private TableView<Book> booksTable;
    @FXML private TableColumn<Book, String> title;
    @FXML private TableColumn<Book, String> author;
    @FXML private TableColumn<Book, Float> averageRating;
    @FXML private TableColumn<Book, Short> originalPublicationYear;
    @FXML private TableColumn<Book, String> ISBN13;
    @FXML private TableColumn<Book, String> smallSizedImage;
    // hidden columns by default
    @FXML private TableColumn<Book, String> ISBN;
    @FXML private TableColumn<Book, String> oldTitle;
    @FXML private TableColumn<Book, Integer> bookID;
    @FXML private TableColumn<Book, Integer> goodReadsBookID;
    @FXML private TableColumn<Book, Integer> bestBookID;
    @FXML private TableColumn<Book, Integer> workID;
    @FXML private TableColumn<Book, String> languageCode;
    @FXML private TableColumn<Book, Integer> totalNumberOfBooks;
    @FXML private TableColumn<Book, Integer> totalRatings;
    @FXML private TableColumn<Book, Integer> totalWorkRatings;
    @FXML private TableColumn<Book, Integer> totalWorkTextReviews;
    @FXML private TableColumn<Book, Integer> totalOneStarReviews;
    @FXML private TableColumn<Book, Integer> totalTwoStarReviews;
    @FXML private TableColumn<Book, Integer> totalThreeStarReviews;
    @FXML private TableColumn<Book, Integer> totalFourStarReviews;
    @FXML private TableColumn<Book, Integer> totalFiveStarReviews;
    @FXML private ToggleGroup listToggler;
    @FXML RadioButton useArrayList;
    @FXML RadioButton useLinkedList;

    @FXML private Button importFromFileButton;
    @FXML private Button nextPageButton;
    @FXML private Button previousPageButton;
    @FXML private Label currentPageNumberFXML;
    @FXML private Label totalNumberOfPagesFXML;
    @FXML private Button selectTableColumns;
    @FXML private Button search;
    @FXML private ChoiceBox searchFilter;
    @FXML private TextField searchBox;
    
    @FXML private Label systemtimes;
    @FXML private Button performance;


    /**
     * Runs during initialization. If there is anything that needs to run during initialization, it'll go here.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hideUnusedTableColumns();
        // Adds a .csv filter to the file chooser and sets the initial directory of the file chooser
        fileChooser.getExtensionFilters().add(extensionFilter);
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + "/Downloads"));
        useArrayList.setSelected(true); // sets default selection for lists
    }

    @FXML private void searchList() {
        // Method to search list and return type
    	
        BookImporter bookImporter = new BookImporter();

        if(searchBox.getText().equals("")) {

            loadTable();

        } else if (useArrayList.isSelected()){

            ArrayList<Book> bookArrayList = new ArrayList<>(books);
            start();
            timeTaken = bookImporter.searchBinary(bookArrayList, searchBox.getText(), (String) searchFilter.getSelectionModel().getSelectedItem());
            end();

        } else if (useLinkedList.isSelected()) {

            LinkedList<Book> bookLinkedList = new LinkedList<>(books);
            start();
            timeTaken = bookImporter.searchLinear(bookLinkedList, searchBox.getText(), (String) searchFilter.getSelectionModel().getSelectedItem());
            end();
        }

        System.out.println("Selection: " + (String) searchFilter.getSelectionModel().getSelectedItem());
        System.out.println("Searched Text: " + searchBox.getText());
        System.out.println("Index: " + timeTaken);

        long totalTime = getTotalTime();
        systemtimes.setVisible(true);
        systemtimes.setText(totalTime + "ms"); // change the text into time
        booksTable.getItems().clear();
        booksTable.getItems().add((Book) books.get(timeTaken));

    }


    /**
     * Opens window allowing columns to be shown or hidden based on the user's preferences.
     */
    @FXML private void openChecklistWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("table-column-selection.fxml"));
            Parent root = loader.load();
            TableColumnSelectionController tableColumnSelectionController = loader.getController();
            tableColumnSelectionController.setMainPageController(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Select Columns");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Display the next ten items on the list if available in the booksTable table
     */
    @FXML private void nextPage() {

        System.out.println("CURRENT: Page Index: " + pageIndex + ", fromIndex: " + fromIndex + ", toIndex: " + toIndex);
        fromIndex = pageIndex;
        if(pageIndex == 0) {
            toIndex = fromIndex + ROWS_PER_PAGE - 1;
        }
        else if(fromIndex + ROWS_PER_PAGE < bookObservableList.size()) {
            toIndex = fromIndex + ROWS_PER_PAGE;
            fromIndex++;
            booksTable.setItems(FXCollections.observableArrayList(bookObservableList.subList(fromIndex, Math.min(toIndex + 1,bookObservableList.size()))));

        } else if (fromIndex + ROWS_PER_PAGE == bookObservableList.size()) {
            toIndex = fromIndex + ROWS_PER_PAGE;
            fromIndex++;
            booksTable.setItems(FXCollections.observableArrayList(bookObservableList.subList(fromIndex, Math.min(toIndex + 1,bookObservableList.size()))));
        }else if(fromIndex == bookObservableList.size() - 1) {
            fromIndex = bookObservableList.size() - ROWS_PER_PAGE;
            toIndex = bookObservableList.size() - 1;
            booksTable.setItems(FXCollections.observableArrayList(bookObservableList.subList(fromIndex, Math.min(toIndex + 1,bookObservableList.size()))));
        }

        // Setting page index
        if(pageIndex == 0) {
            pageIndex = fromIndex + ROWS_PER_PAGE - 1;
        }
        else if (bookObservableList.size() > pageIndex + ROWS_PER_PAGE) {
            pageIndex += ROWS_PER_PAGE;
        } else if (bookObservableList.size() == toIndex) {
            pageIndex = toIndex;
        }
        System.out.println("AFTER: Page Index: " + pageIndex + ", fromIndex: " + fromIndex + ", toIndex: " + toIndex);

        setCurrentPageNumber();
        booksTable.refresh();
    }

    /**
     * Display the previous ten items on the list if available in the booksTable table
     */
    @FXML private void previousPage() {
        System.out.println("CURRENT: Page Index: " + pageIndex + ", fromIndex: " + fromIndex + ", toIndex: " + toIndex);
        if(pageIndex - ROWS_PER_PAGE > 0) {
            pageIndex -= ROWS_PER_PAGE;
            toIndex = pageIndex;
            fromIndex = toIndex - ROWS_PER_PAGE + 1;
        } else {
            fromIndex = 0;
            toIndex = ROWS_PER_PAGE - 1;
            pageIndex = ROWS_PER_PAGE - 1;
        }
        // Setting items on the table to be between fromIndex and toIndex from the books list
        booksTable.setItems(FXCollections.observableArrayList(bookObservableList.subList(fromIndex, toIndex + 1)));
        System.out.println("AFTER: Page Index: " + pageIndex + ", fromIndex: " + fromIndex + ", toIndex: " + toIndex);
        setCurrentPageNumber();
        booksTable.refresh();
    }

    /**
     *
     */
    @FXML private void importBooksFromCsvFile() {
        selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null && booksTable.getItems() != null) {
        	//add system time
        	start();
            // Books Importing into ADT List format.  Can be converted to LinkedList or ArrayList.
            books = BookImporter.exportBooksToList(BookImporter.importBooksFromCSV(selectedFile.getAbsolutePath()));
            bookObservableList = FXCollections.observableArrayList(books);

            // Loading table, setting default values, setting current page number and total page numbers
            fromIndex = 0;
            toIndex = ROWS_PER_PAGE - 1;
            pageIndex = ROWS_PER_PAGE - 1;
            loadTable();
            setCurrentPageNumber();
            setTotalNumberOfPages();

            // Popup box telling user data is imported successfully.
            messagePopup.setTitle("Data Imported Successfully");
            messagePopup.setContentText("File " + selectedFile.getName() + "'s data has been added.");
            messagePopup.showAndWait();

            booksTable.refresh();
            //print system time
            end();
            importtime = getTotalTime();
            System.out.println("Total time to import: " + importtime + " ms");
        } else {

            // Popup box telling user data is not imported and the operation was canceled.
            messagePopup.setTitle("Data not Imported");
            messagePopup.setContentText("File selection operation canceled.");
            messagePopup.showAndWait();
        }
    }
    
    @FXML private void displayPerformancePopup() {
    	messagePopup.setTitle("System Performance");
        messagePopup.setContentText("Time Executed: " + importtime + " ms");
        messagePopup.showAndWait();
    }

    /**
     * Updates total page count in application
     */
    private void setTotalNumberOfPages() {
        totalNumberOfPagesFXML.setText(String.valueOf(getTotalNumberOfPages()));
    }

    /**
     * Updates current page number in application
     */
    private void setCurrentPageNumber() {
        currentPageNumberFXML.setText(String.valueOf(getCurrentPageNumber()));
    }

    /**
     * Gets total number of pages in application
     */
    private int getTotalNumberOfPages() {
        return (int)Math.ceil((double) bookObservableList.size() / ROWS_PER_PAGE);
    }

    /**
     * Gets current page number in application
     */
    private int getCurrentPageNumber() {
        if(fromIndex == 0) {
            return 1;
        } else if(toIndex + 1 ==  bookObservableList.size() || toIndex == bookObservableList.size()){
            return getTotalNumberOfPages();
        } else {
            return (int) Math.ceil(getTotalNumberOfPages() * (double) fromIndex / bookObservableList.size());
        }
    }

    /**
     * Sets up the table associated values.  The adding of the columns to the table is done with FXML.
     */
    private void loadTable() {

        setupRatingDataForTable();
        setupBookIdDataForTable();

        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        author.setCellValueFactory(new PropertyValueFactory<>("authors"));
        originalPublicationYear.setCellValueFactory(new PropertyValueFactory<>("originalPublicationYear"));
        smallSizedImage.setCellValueFactory(new PropertyValueFactory<>("smallSizedImageURL"));

        // Loads by default, but the following are hidden.
        oldTitle.setCellValueFactory(new PropertyValueFactory<>("originalTitle"));
        languageCode.setCellValueFactory(new PropertyValueFactory<>("languageCode"));
        totalNumberOfBooks.setCellValueFactory(new PropertyValueFactory<>("totalNumberOfBooks"));
        totalWorkTextReviews.setCellValueFactory(new PropertyValueFactory<>("totalWorkTextReviews"));

        booksTable.setItems(FXCollections.observableArrayList(bookObservableList.subList(fromIndex, toIndex + 1)));
        convertImageURLToImage();

        System.out.println("AFTER: Page Index: " + pageIndex + ", fromIndex: " + fromIndex + ", toIndex: " + toIndex);

        System.out.println("Table Loaded Successfully");
    }

    private void setupBookIdDataForTable() {
        workID.setCellValueFactory(cellValue -> new SimpleObjectProperty<>(cellValue.getValue().getIdentification().getWorkID()));
        bestBookID.setCellValueFactory(cellValue -> new SimpleObjectProperty<>(cellValue.getValue().getIdentification().getBestBookID()));
        goodReadsBookID.setCellValueFactory(cellValue -> new SimpleObjectProperty<>(cellValue.getValue().getIdentification().getGoodreadsBookID()));
        bookID.setCellValueFactory(cellValue -> new SimpleObjectProperty<>(cellValue.getValue().getIdentification().getBookID()));
        ISBN13.setCellValueFactory(cellValue -> new SimpleStringProperty(cellValue.getValue().getIdentification().getISBN13()));
        ISBN.setCellValueFactory(cellValue -> new SimpleStringProperty(cellValue.getValue().getIdentification().getISBN()));
    }

    private void setupRatingDataForTable() {
        averageRating.setCellValueFactory(cellValue -> new SimpleObjectProperty<>(cellValue.getValue().getBookRatings().getAverageRating()));
        totalFiveStarReviews.setCellValueFactory(cellValue -> new SimpleObjectProperty<>(cellValue.getValue().getBookRatings().getTotalFiveStarRating()));
        totalFourStarReviews.setCellValueFactory(cellValue -> new SimpleObjectProperty<>(cellValue.getValue().getBookRatings().getTotalFourStarRating()));
        totalThreeStarReviews.setCellValueFactory(cellValue -> new SimpleObjectProperty<>(cellValue.getValue().getBookRatings().getTotalThreeStarRating()));
        totalTwoStarReviews.setCellValueFactory(cellValue -> new SimpleObjectProperty<>(cellValue.getValue().getBookRatings().getTotalTwoStarRating()));
        totalOneStarReviews.setCellValueFactory(cellValue -> new SimpleObjectProperty<>(cellValue.getValue().getBookRatings().getTotalOneStarRating()));
        totalWorkRatings.setCellValueFactory(cellValue -> new SimpleObjectProperty<>(cellValue.getValue().getBookRatings().getTotalWorkRatings()));
        totalRatings.setCellValueFactory(cellValue -> new SimpleObjectProperty<>(cellValue.getValue().getBookRatings().getTotalRatings()));
    }

    /**
     * Converts the image urls to images in the table for the small images
     */
    private void convertImageURLToImage() {
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
        System.out.println("Converted URLs to Images.");
    }

    // Hides least important columns
    private void hideUnusedTableColumns() {
        ISBN.setVisible(false);
        oldTitle.setVisible(false);
        bookID.setVisible(false);
        goodReadsBookID.setVisible(false);
        bestBookID.setVisible(false);
        workID.setVisible(false);
        languageCode.setVisible(false);
        totalNumberOfBooks.setVisible(false);
        totalRatings.setVisible(false);
        totalWorkRatings.setVisible(false);
        totalWorkTextReviews.setVisible(false);
        totalOneStarReviews.setVisible(false);
        totalTwoStarReviews.setVisible(false);
        totalThreeStarReviews.setVisible(false);
        totalFourStarReviews.setVisible(false);
        totalFiveStarReviews.setVisible(false);
        //added label at the bottom when it comes to system times 
        systemtimes.setVisible(false);

    }

    // Below Methods are for setting visibility  of components
    protected void setSmallSizedImageColumnVisibility(boolean visible) {
        smallSizedImage.setVisible(visible);
    }
    protected void setOriginalPublicationYearColumnVisibility(boolean visible) {
        originalPublicationYear.setVisible(visible);
    }
    protected void setAverageRatingColumnVisibility(boolean visible) {
        averageRating.setVisible(visible);
    }
    protected void setAuthorColumnVisibility(boolean visible) {
        author.setVisible(visible);
    }
    protected void setCurrentTitleColumnVisibility(boolean visible) {
        title.setVisible(visible);
    }
    protected void setISBN13ColumnVisibility(boolean visible) {
        ISBN13.setVisible(visible);
    }
    protected void setISBNColumnVisibility(boolean visible) {
        ISBN.setVisible(visible);
    }

    protected void setOldTitleColumnVisibility(boolean visible) {
        oldTitle.setVisible(visible);
    }

    protected void setBookIDColumnVisibility(boolean visible) {
        bookID.setVisible(visible);
    }

    protected void setGoodReadsBookIDColumnVisibility(boolean visible) {
        goodReadsBookID.setVisible(visible);
    }

    protected void setBestBookIDColumnVisibility(boolean visible) {
        bestBookID.setVisible(visible);
    }

    protected void setWorkIDColumnVisibility(boolean visible) {
        workID.setVisible(visible);
    }

    protected void setLanguageCodeColumnVisibility(boolean visible) {
        languageCode.setVisible(visible);
    }

    protected void setBookTotalColumnVisibility(boolean visible) {
        totalNumberOfBooks.setVisible(visible);
    }

    protected void setTotalRatingsColumnVisibility(boolean visible) {
        totalRatings.setVisible(visible);
    }

    protected void setTotalWorkRatingsColumnVisibility(boolean visible) {
        totalWorkRatings.setVisible(visible);
    }

    protected void setTotalWorkTextReviewsColumnVisibility(boolean visible) {
        totalWorkTextReviews.setVisible(visible);
    }

    protected void setTotalOneStarReviewsColumnVisibility(boolean visible) {
        totalOneStarReviews.setVisible(visible);
    }

    protected void setTotalTwoStarReviewsColumnVisibility(boolean visible) {
        totalTwoStarReviews.setVisible(visible);
    }

    protected void setTotalThreeStarReviewsColumnVisibility(boolean visible) {
        totalThreeStarReviews.setVisible(visible);
    }

    protected void setTotalFourStarReviewsColumnVisibility(boolean visible) {
        totalFourStarReviews.setVisible(visible);
    }

    protected void setTotalFiveStarReviewsColumnVisibility(boolean visible) {
        totalFiveStarReviews.setVisible(visible);
    }

    //variables for system testing
    protected void start(){
      this.startTime = System.currentTimeMillis();
    }

    protected void end() {
      this.endTime   = System.currentTimeMillis();  
    }

    protected long getStartTime() {
      return this.startTime;
    }

    protected long getEndTime() {
      return this.endTime;
    }

    protected long getTotalTime() {
      return this.endTime - this.startTime;
    }


}