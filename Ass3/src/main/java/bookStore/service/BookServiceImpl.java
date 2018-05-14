package bookStore.service;

import bookStore.dto.BookDTO;
import bookStore.entity.Book;
import bookStore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService{
    private BookRepository bookRepository;


    @Autowired
    public BookServiceImpl(final BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book create(BookDTO bookDTO) {
        Book b = new Book(bookDTO.getAuthor(), bookDTO.getTitle(),
                bookDTO.getIsbn(), bookDTO.getGenre(),
                bookDTO.getQuantity(), bookDTO.getPrice());
        return bookRepository.save(b);
    }

    @Override
    public List<Book> findAll() {
        final Iterable<Book> books = bookRepository.findAll();
        List<Book> books1 = new ArrayList<>();
        books.forEach(books1::add);

        return books1;
    }

    @Override
    public List<Book> findAllByGenre(String genre) {
        final Iterable<Book> books = bookRepository.findAllByGenre(genre);
        List<Book> books1 = new ArrayList<>();
        books.forEach(books1::add);
        return books1;
    }

    @Override
    public List<Book> findAllByAuthor(String author) {
        final Iterable<Book> books = bookRepository.findAllByAuthor(author);
        List<Book> books1 = new ArrayList<>();
        books.forEach(books1::add);
        return books1;
    }

    @Override
    public List<Book> findAllByTitle(String title) {
        final Iterable<Book> books = bookRepository.findAllByTitle(title);
        List<Book> books1 = new ArrayList<>();
        books.forEach(books1::add);
        return books1;
    }

    @Override
    public void deleteByIsbn(String isbn) {
        bookRepository.deleteByIsbn(isbn);
    }

    @Override
    public void updateBook(Long quantity, String isbn) {
        bookRepository.updateBook(quantity, isbn);
    }

    @Override
    public List<Book> findAllByQuantity(Long quantity) {
        final Iterable<Book> books = bookRepository.findAllByQuantity(quantity);
        List<Book> books1 = new ArrayList<>();
        books.forEach(books1::add);
        return books1;
    }

    @Override
    public Book findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }
}
