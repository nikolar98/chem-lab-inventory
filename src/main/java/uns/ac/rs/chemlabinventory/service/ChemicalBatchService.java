package uns.ac.rs.chemlabinventory.service;

import org.springframework.stereotype.Service;
import uns.ac.rs.chemlabinventory.model.ChemicalBatch;
import uns.ac.rs.chemlabinventory.repository.ChemicalBatchRepository;

import java.util.List;

@Service
public class ChemicalBatchService {

    private final ChemicalBatchRepository chemicalBatchRepository;

    public ChemicalBatchService(ChemicalBatchRepository chemicalBatchRepository) {
        this.chemicalBatchRepository = chemicalBatchRepository;
    }

    public List<ChemicalBatch> findAll() {
        return chemicalBatchRepository.findAll();
    }

    public ChemicalBatch findById(Long id) {
        return chemicalBatchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Zaliha nije pronadjena."));
    }

    public ChemicalBatch save(ChemicalBatch chemicalBatch) {
        return chemicalBatchRepository.save(chemicalBatch);
    }

    public void delete(Long id) {
        chemicalBatchRepository.deleteById(id);
    }
}