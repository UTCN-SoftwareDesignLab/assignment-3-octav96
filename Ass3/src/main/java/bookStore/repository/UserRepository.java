package bookStore.repository;

import bookStore.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {
    List<User> findAll();

    User save(User user);
    @Transactional
    void deleteByUsername(String username);

    User findByUsername(String username);

   // User findById(Long id);

    User getOne(Long id);
    @Transactional
    @Modifying
    @Query("update User u set u.address = ?1 where u.username = ?2")
    void updateUser(String address, String username);

    Optional<User> findById(Long id);
}
