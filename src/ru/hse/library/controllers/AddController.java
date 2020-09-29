package ru.hse.library.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ru.hse.library.Book;
import ru.hse.library.Main;

public class AddController {

    @FXML
    TextField authorField;
    @FXML
    TextField titleField;
    @FXML
    TextField publisherField;
    @FXML
    TextField pagesField;
    @FXML
    TextField genreField;
    @FXML
    TextField priceField;
    @FXML
    Label errorLabel;

    public void submitBook() {

        int pages, price;

        if(authorField.getText().equals("") ||
            titleField.getText().equals("") ||
            publisherField.getText().equals("") ||
            pagesField.getText().equals("") ||
            genreField.getText().equals("") ||
            priceField.getText().equals("")) {
            errorLabel.setText("Пожалуйста, заполните все поля");
            return;
        }

        try {
            pages = Integer.parseInt(pagesField.getText());
        } catch (NumberFormatException pagesFault) {
            errorLabel.setText("Количество страниц должно быть целым числом!");
            return;
        }

        try {
            price = Integer.parseInt(priceField.getText());
        } catch (NumberFormatException priceFault) {
            errorLabel.setText("Цена должна быть целым числом!");
            return;
        }

        Main.books.add(new Book(authorField.getText(),
                titleField.getText(),
                publisherField.getText(),
                pages,
                genreField.getText(),
                price));
        errorLabel.setText("");

        authorField.setText("");
        titleField.setText("");
        publisherField.setText("");
        pagesField.setText("");
        genreField.setText("");
        priceField.setText("");

    }

}
