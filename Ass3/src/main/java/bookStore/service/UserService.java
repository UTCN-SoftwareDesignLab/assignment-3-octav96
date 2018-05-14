package bookStore.service;

import bookStore.dto.UserDTO;
import bookStore.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public User create(UserDTO userDTO);
    public List<User> getAll();
    public List<User> findAll();
    public void deleteByUsername(String username);
    public void updateUser(String address, String username);

    public User findByUsername(String username);
    public User getOne(Long id);

    public Optional<User> findById(Long id);
}
