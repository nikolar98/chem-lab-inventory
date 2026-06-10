package uns.ac.rs.chemlabinventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uns.ac.rs.chemlabinventory.model.Chemical;

public interface ChemicalRepository extends JpaRepository<Chemical, Long> {
}
