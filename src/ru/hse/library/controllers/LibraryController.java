package ru.hse.library.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import ru.hse.library.Book;
import ru.hse.library.Main;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class LibraryController implements Initializable {

    @FXML
    Label content;

    @FXML
    TextField searchBar;

    @FXML
    ScrollPane pane;

    @FXML
    ComboBox<String> searchMode;

    private ArrayList<Book> cachedList = new ArrayList<>();

    @Deprecated
    public void update_old() {

        if(Main.books.size() == 0) {
            content.setText("В вашей библиотеке нет книг");
            return;
        }

        // If nothing to update, do not update
        if(cachedList.equals(Main.books)) return;

        content.setText("");
        for(Book book : Main.books) {

            if(!(searchBar.getText().equals("")))
            {
                System.out.println(book.getTitle() +
                        (book.getTitle().contains(searchBar.getText()) ? " contains " : " does not contain ") +
                        searchBar.getText());
            }

            // Search bar empty                      : TRUE
            // Search bar not empty, query found     : TRUE
            // Search bar not empty, query not found : FALSE
            if (searchBar.getText().equals("") || book.getTitle().contains(searchBar.getText())) {
                content.setText(content.getText() + book.toString() + "\n\n");
            }
        }

        if(content.getText().equals(""))
            content.setText("Ничего не найдено");

        // Cache the list
        cachedList = (ArrayList<Book>) Main.books.clone();
    }

    public void update() {
        if(Main.books.size() == 0) {
            pane.setContent(new Label("В вашей библиотеке нет книг"));
            return;
        }

        // If nothing to update, do not update
        if(cachedList.equals(Main.books)) return;

        // Construct the table of books
        GridPane table = new GridPane();
        table.setGridLinesVisible(true);
        table.add(new Label("Автор"), 0, 0);
        table.add(new Label("Название"), 1, 0);
        table.add(new Label("Издатель"), 2, 0);
        table.add(new Label("Страниц"), 3, 0);
        table.add(new Label("Жанр"), 4, 0);
        table.add(new Label("Цена"), 5, 0);

        for(int i = 0; i < Main.books.size(); i++) {

            if(!(searchBar.getText().equals("")))
            {
                System.out.println(Main.books.get(i).getTitle() +
                        (Main.books.get(i).getTitle().contains(searchBar.getText()) ? " contains " : " does not contain ") +
                        searchBar.getText());
            }

            if(searchMode.getValue() == null) {
                table.add(new Label(Main.books.get(i).getAuthor()), 0, i + 1);
                table.add(new Label(Main.books.get(i).getTitle()), 1, i + 1);
                table.add(new Label(Main.books.get(i).getPublisher()), 2, i + 1);
                table.add(new Label(Integer.toString(Main.books.get(i).getPages())), 3, i + 1);
                table.add(new Label(Main.books.get(i).getGenre()), 4, i + 1);
                table.add(new Label(Integer.toString(Main.books.get(i).getPrice())), 5, i + 1);
                Button deleteThis = new Button("Удалить");
                final Book thisBook = Main.books.get(i);
                deleteThis.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Main.books.remove(thisBook);
                    }
                });
                table.add(deleteThis, 6, i + 1);
                continue;
            }

            switch(searchMode.getValue()) {
                case "По названию":
                    if (searchBar.getText().equals("") || Main.books.get(i).getTitle().contains(searchBar.getText())) {
                        table.add(new Label(Main.books.get(i).getAuthor()), 0, i + 1);
                        table.add(new Label(Main.books.get(i).getTitle()), 1, i + 1);
                        table.add(new Label(Main.books.get(i).getPublisher()), 2, i + 1);
                        table.add(new Label(Integer.toString(Main.books.get(i).getPages())), 3, i + 1);
                        table.add(new Label(Main.books.get(i).getGenre()), 4, i + 1);
                        table.add(new Label(Integer.toString(Main.books.get(i).getPrice())), 5, i + 1);
                        Button deleteThis = new Button("Удалить");
                        final Book thisBook = Main.books.get(i);
                        deleteThis.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                Main.books.remove(thisBook);
                            }
                        });
                        table.add(deleteThis, 6, i + 1);
                    }
                    break;
                case "По автору":
                    if (searchBar.getText().equals("") || Main.books.get(i).getAuthor().contains(searchBar.getText())) {
                        table.add(new Label(Main.books.get(i).getAuthor()), 0, i + 1);
                        table.add(new Label(Main.books.get(i).getTitle()), 1, i + 1);
                        table.add(new Label(Main.books.get(i).getPublisher()), 2, i + 1);
                        table.add(new Label(Integer.toString(Main.books.get(i).getPages())), 3, i + 1);
                        table.add(new Label(Main.books.get(i).getGenre()), 4, i + 1);
                        table.add(new Label(Integer.toString(Main.books.get(i).getPrice())), 5, i + 1);
                        Button deleteThis = new Button("Удалить");
                        final Book thisBook = Main.books.get(i);
                        deleteThis.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                Main.books.remove(thisBook);
                            }
                        });
                        table.add(deleteThis, 6, i + 1);
                    }
                    break;
                case "По издателю":
                    if (searchBar.getText().equals("") || Main.books.get(i).getPublisher().contains(searchBar.getText())) {
                        table.add(new Label(Main.books.get(i).getAuthor()), 0, i + 1);
                        table.add(new Label(Main.books.get(i).getTitle()), 1, i + 1);
                        table.add(new Label(Main.books.get(i).getPublisher()), 2, i + 1);
                        table.add(new Label(Integer.toString(Main.books.get(i).getPages())), 3, i + 1);
                        table.add(new Label(Main.books.get(i).getGenre()), 4, i + 1);
                        table.add(new Label(Integer.toString(Main.books.get(i).getPrice())), 5, i + 1);
                        Button deleteThis = new Button("Удалить");
                        final Book thisBook = Main.books.get(i);
                        deleteThis.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                Main.books.remove(thisBook);
                            }
                        });
                        table.add(deleteThis, 6, i + 1);
                    }
                    break;
                case "По кол-ву страниц (больше)":
                    if (searchBar.getText().equals("") || Main.books.get(i).getPages() >= Integer.parseInt(searchBar.getText())) {
                        table.add(new Label(Main.books.get(i).getAuthor()), 0, i + 1);
                        table.add(new Label(Main.books.get(i).getTitle()), 1, i + 1);
                        table.add(new Label(Main.books.get(i).getPublisher()), 2, i + 1);
                        table.add(new Label(Integer.toString(Main.books.get(i).getPages())), 3, i + 1);
                        table.add(new Label(Main.books.get(i).getGenre()), 4, i + 1);
                        table.add(new Label(Integer.toString(Main.books.get(i).getPrice())), 5, i + 1);
                        Button deleteThis = new Button("Удалить");
                        final Book thisBook = Main.books.get(i);
                        deleteThis.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                Main.books.remove(thisBook);
                            }
                        });
                        table.add(deleteThis, 6, i + 1);
                    }
                    break;
                case "По кол-ву страниц (меньше)":
                    if (searchBar.getText().equals("") || Main.books.get(i).getPages() <= Integer.parseInt(searchBar.getText())) {
                        table.add(new Label(Main.books.get(i).getAuthor()), 0, i + 1);
                        table.add(new Label(Main.books.get(i).getTitle()), 1, i + 1);
                        table.add(new Label(Main.books.get(i).getPublisher()), 2, i + 1);
                        table.add(new Label(Integer.toString(Main.books.get(i).getPages())), 3, i + 1);
                        table.add(new Label(Main.books.get(i).getGenre()), 4, i + 1);
                        table.add(new Label(Integer.toString(Main.books.get(i).getPrice())), 5, i + 1);
                        Button deleteThis = new Button("Удалить");
                        final Book thisBook = Main.books.get(i);
                        deleteThis.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                Main.books.remove(thisBook);
                            }
                        });
                        table.add(deleteThis, 6, i + 1);
                    }
                    break;
                case "По жанру":
                    if (searchBar.getText().equals("") || Main.books.get(i).getGenre().contains(searchBar.getText())) {
                        table.add(new Label(Main.books.get(i).getAuthor()), 0, i + 1);
                        table.add(new Label(Main.books.get(i).getTitle()), 1, i + 1);
                        table.add(new Label(Main.books.get(i).getPublisher()), 2, i + 1);
                        table.add(new Label(Integer.toString(Main.books.get(i).getPages())), 3, i + 1);
                        table.add(new Label(Main.books.get(i).getGenre()), 4, i + 1);
                        table.add(new Label(Integer.toString(Main.books.get(i).getPrice())), 5, i + 1);
                        Button deleteThis = new Button("Удалить");
                        final Book thisBook = Main.books.get(i);
                        deleteThis.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                Main.books.remove(thisBook);
                            }
                        });
                        table.add(deleteThis, 6, i + 1);
                    }
                    break;
                case "По цене (дешевле)":
                    if (searchBar.getText().equals("") || Main.books.get(i).getPrice() <= Integer.parseInt(searchBar.getText())) {
                        table.add(new Label(Main.books.get(i).getAuthor()), 0, i + 1);
                        table.add(new Label(Main.books.get(i).getTitle()), 1, i + 1);
                        table.add(new Label(Main.books.get(i).getPublisher()), 2, i + 1);
                        table.add(new Label(Integer.toString(Main.books.get(i).getPages())), 3, i + 1);
                        table.add(new Label(Main.books.get(i).getGenre()), 4, i + 1);
                        table.add(new Label(Integer.toString(Main.books.get(i).getPrice())), 5, i + 1);
                        Button deleteThis = new Button("Удалить");
                        final Book thisBook = Main.books.get(i);
                        deleteThis.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                Main.books.remove(thisBook);
                            }
                        });
                        table.add(deleteThis, 6, i + 1);
                    }
                    break;
                case "По цене (дороже)":
                    if (searchBar.getText().equals("") || Main.books.get(i).getPrice() >= Integer.parseInt(searchBar.getText())) {
                        table.add(new Label(Main.books.get(i).getAuthor()), 0, i + 1);
                        table.add(new Label(Main.books.get(i).getTitle()), 1, i + 1);
                        table.add(new Label(Main.books.get(i).getPublisher()), 2, i + 1);
                        table.add(new Label(Integer.toString(Main.books.get(i).getPages())), 3, i + 1);
                        table.add(new Label(Main.books.get(i).getGenre()), 4, i + 1);
                        table.add(new Label(Integer.toString(Main.books.get(i).getPrice())), 5, i + 1);
                        Button deleteThis = new Button("Удалить");
                        final Book thisBook = Main.books.get(i);
                        deleteThis.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                Main.books.remove(thisBook);
                            }
                        });
                        table.add(deleteThis, 6, i + 1);
                    }
                    break;
            }
        }

        for(Node n : table.getChildren()){
            GridPane.setMargin(n, new Insets(10, 10, 10, 10));
        }

        ((AnchorPane) pane.getParent()).getScene().getWindow().setWidth(pane.getWidth() + 15);
        pane.setContent(table);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        searchMode.setItems(FXCollections.observableArrayList(
                "По названию",
                "По автору",
                "По издателю",
                "По кол-ву страниц (больше)",
                "По кол-ву страниц (меньше)",
                "По жанру",
                "По цене (дешевле)",
                "По цене (дороже)"
        ));
    }
}
