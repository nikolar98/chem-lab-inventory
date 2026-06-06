package uns.ac.rs.chemlabinventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uns.ac.rs.chemlabinventory.model.ChemicalBatch;

public interface ChemicalBatchRepository extends JpaRepository<ChemicalBatch, Integer> {
}
