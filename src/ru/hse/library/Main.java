package ru.hse.library;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.*;
import java.util.ArrayList;


public class Main extends Application {

    public static ArrayList<Book> books;

    @FXML
    Label errorLabel;

    @Override
    public void start(Stage primaryStage) throws Exception{
        books = new ArrayList<>();
        Parent root = FXMLLoader.load(getClass().getResource("forms/mainWindow.fxml"));
        primaryStage.setTitle("Библиотека");
        primaryStage.setScene(new Scene(root, 230, 120));

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                // Save the library to the file
                try {
                    ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("save.bin"));
                    stream.writeObject(books);
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }
        });
        // Load a previously saved library if exists
        try {
            ObjectInputStream stream = new ObjectInputStream(new FileInputStream("save.bin"));
            books = (ArrayList<Book>) stream.readObject();
        } catch (IOException e) {
            // Ignore the case where the saved file does not exist
        } catch (ClassNotFoundException c) {
            errorLabel.setText("Сохраненная библиотека повреждена");
        }
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
