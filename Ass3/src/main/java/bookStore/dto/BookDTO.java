package bookStore.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class BookDTO {
    @NotNull
    @Size(min=2, max=30, message = "AUTHOR must have between 2 and 30 characters")
    private String author;
    @NotNull
    @Size(min=2, max=30, message = "TITLE must have between 2 and 30 characters")
    private String title;
    @Pattern(regexp = "^[1-9]+$", message = "ISBN must contain only digits")
    @Size(min = 5, max = 5, message = "ISBN is wrong the wrong size")
    private String isbn;
    @NotNull
    private String genre;
    @NotNull
    @Min(0)
    private Long quantity;
    @NotNull
    private Double price;

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
}
