package bookStore.repository;

import bookStore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book save(Book book);
    List<Book> findAll();
    List<Book> findAllByGenre(String genre);
    List<Book> findAllByTitle(String title);
    List<Book> findAllByAuthor(String author);
    List<Book> findAllByQuantity(Long quantity);
    Book findByIsbn(String isbn);
    @Transactional
    @Modifying
    @Query("update Book b set b.quantity = ?1 where b.isbn = ?2")
    void updateBook(Long quantity, String isbn);

    @Transactional
    void deleteByIsbn(String isbn);




}
