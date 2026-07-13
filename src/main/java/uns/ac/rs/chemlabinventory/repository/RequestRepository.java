package uns.ac.rs.chemlabinventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uns.ac.rs.chemlabinventory.model.Request;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request,Long> {
    List<Request> findByEmailOrderByCreatedAtDesc(String email);
}
