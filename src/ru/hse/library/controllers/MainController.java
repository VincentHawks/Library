package ru.hse.library.controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class MainController {

    public void libraryButtonPressed() {

        try {
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(getClass().getResourceAsStream("../forms/libraryWindow.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Список книг");
            stage.setScene(new Scene(root, 400, 450));
            // Update the list of books upon showing the window
            stage.setOnShown(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    ((LibraryController)loader.getController()).update();
                }
            });
            // This should really be done via an event, but it works as it is
            stage.getScene().setOnMouseMoved(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    ((LibraryController)loader.getController()).update();
                }
            });
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void addButtonPressed() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../forms/addWindow.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Добавить книгу");
            stage.setScene(new Scene(root, 600, 250));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
