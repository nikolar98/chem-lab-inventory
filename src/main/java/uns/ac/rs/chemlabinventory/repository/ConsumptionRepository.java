package uns.ac.rs.chemlabinventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uns.ac.rs.chemlabinventory.model.Consumption;

import java.util.List;
import java.util.Optional;

public interface ConsumptionRepository extends JpaRepository<Consumption, Long> {

    List<Consumption> findByChemicalBatchIdOrderByDateTakenDesc(Long chemicalBatchId);

    Optional<Consumption> findFirstByChemicalBatchIdOrderByDateTakenDesc(Long chemicalBatchId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Consumption c WHERE c.user.id = :userId")
    void deleteByUserId(@Param("userId") Long userId);
}