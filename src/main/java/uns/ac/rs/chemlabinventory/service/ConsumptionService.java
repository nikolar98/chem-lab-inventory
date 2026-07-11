package uns.ac.rs.chemlabinventory.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uns.ac.rs.chemlabinventory.dto.ConsumptionDTO;
import uns.ac.rs.chemlabinventory.model.ChemicalBatch;
import uns.ac.rs.chemlabinventory.model.Consumption;
import uns.ac.rs.chemlabinventory.model.Location;
import uns.ac.rs.chemlabinventory.model.User;
import uns.ac.rs.chemlabinventory.repository.ChemicalBatchRepository;
import uns.ac.rs.chemlabinventory.repository.ConsumptionRepository;
import uns.ac.rs.chemlabinventory.repository.LocationRepository;
import uns.ac.rs.chemlabinventory.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConsumptionService {

    private final ConsumptionRepository consumptionRepository;
    private final ChemicalBatchRepository chemicalBatchRepository;
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;

    public ConsumptionService(ConsumptionRepository consumptionRepository, ChemicalBatchRepository chemicalBatchRepository,
                              UserRepository userRepository, LocationRepository locationRepository) {
        this.consumptionRepository = consumptionRepository;
        this.chemicalBatchRepository = chemicalBatchRepository;
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
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

    @Transactional
    public ConsumptionDTO createFromDTO(ConsumptionDTO dto) {
        ChemicalBatch batch = chemicalBatchRepository.findById(dto.getChemicalBatchId())
                .orElseThrow(() -> new RuntimeException("Serija hemikalije nije pronađena."));

        if (batch.getCurrentQuantity().compareTo(dto.getQuantity()) < 0) {
            throw new RuntimeException("Nema dovoljno hemikalije na stanju! Trenutno stanje: " + batch.getCurrentQuantity() + " " + batch.getPackageUnit());
        }

        batch.setCurrentQuantity(batch.getCurrentQuantity().subtract(dto.getQuantity()));
        batch.setOpened(true);
        if (dto.getLocationName() != null && !dto.getLocationName().isBlank()) {
            Location location = locationRepository.findByNameIgnoreCase(dto.getLocationName().trim())
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Lokacija nije pronađena: " + dto.getLocationName()
                            )
                    );

            batch.setLocation(location);
        }
        chemicalBatchRepository.save(batch);

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Korisnik nije pronađen."));

        Consumption consumption = new Consumption();
        consumption.setChemicalBatch(batch);
        consumption.setUser(user);
        consumption.setQuantity(dto.getQuantity());
        consumption.setUnit(dto.getUnit());
        consumption.setDateTaken(LocalDateTime.now());
        consumption.setNote(dto.getNote());
        consumption.setPurpose(dto.getPurpose());

        consumption = consumptionRepository.save(consumption);

        return new ConsumptionDTO(consumption);
    }
}