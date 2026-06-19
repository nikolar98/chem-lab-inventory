package uns.ac.rs.chemlabinventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uns.ac.rs.chemlabinventory.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
