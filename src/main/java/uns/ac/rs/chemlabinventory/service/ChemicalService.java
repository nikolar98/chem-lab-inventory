package uns.ac.rs.chemlabinventory.service;

import org.springframework.stereotype.Service;
import uns.ac.rs.chemlabinventory.model.Chemical;
import uns.ac.rs.chemlabinventory.repository.ChemicalRepository;

import java.util.List;

@Service
public class ChemicalService {

    private final ChemicalRepository chemicalRepository;

    public ChemicalService(ChemicalRepository chemicalRepository) {
        this.chemicalRepository = chemicalRepository;
    }

    public List<Chemical> findAll() {
        return chemicalRepository.findAll();
    }

    public Chemical findById(Long id) {
        return chemicalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hemikalija nije pronadjena."));
    }

    public Chemical save(Chemical chemical) {
        return chemicalRepository.save(chemical);
    }

    public void delete(Long id) {
        chemicalRepository.deleteById(id);
    }
}