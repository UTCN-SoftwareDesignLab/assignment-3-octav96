package bookStore.service;

import bookStore.dto.BookDTO;
import bookStore.entity.Book;

import java.util.List;

public class CachingBookService implements BookService{
    private BookService origin;

    public CachingBookService(BookService origin) {
        this.origin = origin;
    }

    @Override
    public List<Book> getAll() {
        return origin.getAll();
    }

    @Override
    public Book create(BookDTO bookDTO) {
        return origin.create(bookDTO);
    }

    @Override
    public List<Book> findAll() {
        return origin.findAll();
    }

    @Override
    public List<Book> findAllByGenre(String genre) {
        return origin.findAllByGenre(genre);
    }

    @Override
    public List<Book> findAllByAuthor(String author) {
        return origin.findAllByAuthor(author);
    }

    @Override
    public List<Book> findAllByTitle(String title) {
        return origin.findAllByTitle(title);
    }

    @Override
    public void deleteByIsbn(String isbn) {
        origin.deleteByIsbn(isbn);
    }

    @Override
    public void updateBook(Long quantity, String isbn) {
        origin.updateBook(quantity, isbn);
    }

    @Override
    public List<Book> findAllByQuantity(Long quantity){
        return origin.findAllByQuantity(quantity);
    }

    @Override
    public Book findByIsbn(String isbn) {
        return origin.findByIsbn(isbn);
    }
}
