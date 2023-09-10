module com.libraryapp.app {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.libraryapp.app to javafx.fxml;
    exports com.libraryapp.app;
}