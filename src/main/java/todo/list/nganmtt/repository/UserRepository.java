package todo.list.nganmtt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import todo.list.nganmtt.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<User> findByUsername(String username);

    Optional<User> getUserById(String id);
}
