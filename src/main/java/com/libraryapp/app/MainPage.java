package com.libraryapp.app;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class MainPage extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-page.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());
        stage.setTitle("Library Management Application");
        stage.setScene(scene);
        stage.show();

        MainPageController mainPageController = loader.getController();
        mainPageController.setStage(stage);

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {

                // If anything needs to be done before closing the app, it'll go here.

                System.out.println("Closing application ...");
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}