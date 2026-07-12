package uns.ac.rs.chemlabinventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uns.ac.rs.chemlabinventory.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.active = :active WHERE u.id = :id")
    void updateUserActivity(@Param("id") Long id, @Param("active") boolean active);

    @Query("SELECT u FROM User u WHERE u.role.name = 'ADMIN' OR u.role.name = 'ROLE_ADMIN'")
    List<User> findAllAdmins();
}
