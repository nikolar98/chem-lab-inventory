package uns.ac.rs.chemlabinventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uns.ac.rs.chemlabinventory.model.Consumption;

import java.util.List;
import java.util.Optional;

public interface ConsumptionRepository extends JpaRepository<Consumption, Long> {

    List<Consumption> findByChemicalBatchIdOrderByDateTakenDesc(Long chemicalBatchId);

    Optional<Consumption> findFirstByChemicalBatchIdOrderByDateTakenDesc(Long chemicalBatchId);

}