package uns.ac.rs.chemlabinventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uns.ac.rs.chemlabinventory.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
