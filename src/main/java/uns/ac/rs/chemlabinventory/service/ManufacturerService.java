package uns.ac.rs.chemlabinventory.service;

import org.springframework.stereotype.Service;
import uns.ac.rs.chemlabinventory.model.Manufacturer;
import uns.ac.rs.chemlabinventory.repository.ManufacturerRepository;

import java.util.List;

@Service
public class ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerService(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    public List<Manufacturer> findAll() {
        return manufacturerRepository.findAll();
    }

    public Manufacturer findById(Long id) {
        return manufacturerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proizvodjač nije pronadjen."));
    }

    public Manufacturer save(Manufacturer manufacturer) {
        return manufacturerRepository.save(manufacturer);
    }

    public void delete(Long id) {
        manufacturerRepository.deleteById(id);
    }
}