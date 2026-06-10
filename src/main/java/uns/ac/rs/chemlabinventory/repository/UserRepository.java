package uns.ac.rs.chemlabinventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uns.ac.rs.chemlabinventory.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
