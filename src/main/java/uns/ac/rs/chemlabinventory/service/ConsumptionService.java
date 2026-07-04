package uns.ac.rs.chemlabinventory.service;

import org.springframework.stereotype.Service;
import uns.ac.rs.chemlabinventory.model.Consumption;
import uns.ac.rs.chemlabinventory.repository.ConsumptionRepository;

import java.util.List;

@Service
public class ConsumptionService {

    private final ConsumptionRepository consumptionRepository;

    public ConsumptionService(ConsumptionRepository consumptionRepository) {
        this.consumptionRepository = consumptionRepository;
    }

    public List<Consumption> findAll() {
        return consumptionRepository.findAll();
    }

    public Consumption findById(Long id) {
        return consumptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Zapis o potrošnji nije pronadjen."));
    }

    public Consumption save(Consumption consumption) {
        return consumptionRepository.save(consumption);
    }

    public void delete(Long id) {
        consumptionRepository.deleteById(id);
    }

    public List<Consumption> findByChemicalBatchId(Long batchId) {
        return consumptionRepository.findByChemicalBatchIdOrderByDateTakenDesc(batchId);
    }
}