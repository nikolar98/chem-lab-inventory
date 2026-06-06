package uns.ac.rs.chemlabinventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uns.ac.rs.chemlabinventory.model.Manufacturer;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer> {
}
