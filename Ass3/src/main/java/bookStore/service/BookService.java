package bookStore.service;

import bookStore.dto.BookDTO;
import bookStore.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> findAll();
    List<Book> getAll();
    Book create(BookDTO book);
    //Book save(BookDTO book);
    List<Book> findAllByGenre(String genre);
    List<Book> findAllByAuthor(String author);
    List<Book> findAllByTitle(String title);
    List<Book> findAllByQuantity(Long quantity);
    Book findByIsbn(String isbn);
    void deleteByIsbn(String isbn);

    void updateBook(Long quantity, String isbn);
}
