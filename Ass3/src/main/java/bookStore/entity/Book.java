package bookStore.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String author;
    private String title;
    private String isbn;
    private String genre;
    private Long quantity;
    private Double price;

    public Book() {
    }

    public Book(String author, String title, String isbn, String genre, Long quantity, Double price) {

        this.author = author;
        this.title = title;
        this.isbn = isbn;
        this.genre = genre;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String toString(){
        return "Title: " + this.title + ", Author: " + this.author + ", ISBN: " + this.isbn +
                ", genre: " + this.genre + ", price: " + this.price + ", quantity: " + this.quantity;
    }
}
