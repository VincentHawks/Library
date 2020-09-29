package ru.hse.library;

import java.io.Serializable;

public class Book implements Serializable{

    private static final long serialVersionUID = 1L;

    private String author;
    private String title;
    private String publisher;
    private int pages;
    private String genre;
    private int price;

    public Book(String author, String title, String publisher, int pages, String genre, int price) {
        this.author = author;
        this.title = title;
        this.publisher = publisher;
        this.pages = pages;
        this.genre = genre;
        this.price = price;
    }

    public Book() {
        this(null, null, null, 0, null, 0);
    }

    public String getAuthor() { return this.author; }
    public String getTitle() { return this.title; }
    public String getPublisher() { return this.publisher; }
    public int getPages() { return this.pages; }
    public String getGenre() { return this.genre; }
    public int getPrice() { return this.price; }

    public void setAuthor(String author) { this.author = author; }
    public void setTitle(String title) { this.title = title; }
    public void setPublisher(String publisher) { this.publisher = publisher; }
    public void setPages(int pages) { this.pages = pages; }
    public void setGenre(String genre) { this.genre = genre; }
    public void setPrice(int price) { this.price = price; }

    @Override
    public String toString() {
        return this.author +
                ", " + this.title +
                "\nИздательство " + this.publisher +
                "\n" + this.pages + " страниц, " +
                this.genre + ", цена " + this.price;
    }


}
