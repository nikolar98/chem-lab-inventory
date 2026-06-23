package uns.ac.rs.chemlabinventory.service;

import org.springframework.stereotype.Service;
import uns.ac.rs.chemlabinventory.dto.ChemicalBatchDTO;
import uns.ac.rs.chemlabinventory.model.ChemicalBatch;
import uns.ac.rs.chemlabinventory.repository.ChemicalBatchRepository;

import java.util.List;

@Service
public class ChemicalBatchService {

    private final ChemicalBatchRepository chemicalBatchRepository;

    public ChemicalBatchService(ChemicalBatchRepository chemicalBatchRepository) {
        this.chemicalBatchRepository = chemicalBatchRepository;
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

    public List<ChemicalBatchDTO> findAll() {
        return chemicalBatchRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    private ChemicalBatchDTO toDto(ChemicalBatch batch) {
        ChemicalBatchDTO dto = new ChemicalBatchDTO();

        dto.setId(batch.getId());

        dto.setChemicalId(batch.getChemical().getId());
        dto.setChemicalName(batch.getChemical().getName());

        if (batch.getManufacturer() != null) {
            dto.setManufacturerId(batch.getManufacturer().getId());
            dto.setManufacturerName(batch.getManufacturer().getName());
        }

        if (batch.getLocation() != null) {
            dto.setLocationId(batch.getLocation().getId());
            dto.setLocationName(batch.getLocation().getName());
        }

        if (batch.getResponsibleUser() != null) {
            dto.setResponsibleUserId(batch.getResponsibleUser().getId());
            dto.setResponsibleUserFullName(
                    batch.getResponsibleUser().getFirstName() + " " +
                            batch.getResponsibleUser().getLastName()
            );
        }

        dto.setPurity(batch.getPurity());
        dto.setPackageSize(batch.getPackageSize());
        dto.setPackageUnit(batch.getPackageUnit());
        dto.setPurchasedQuantity(batch.getPurchasedQuantity());
        dto.setTotalQuantity(batch.getTotalQuantity());
        dto.setCurrentQuantity(batch.getCurrentQuantity());
        dto.setExpirationDate(batch.getExpirationDate());
        dto.setPackagePrice(batch.getPackagePrice());
        dto.setTotalPrice(batch.getTotalPrice());
        dto.setMinimumQuantityAlarm(batch.getMinimumQuantityAlarm());
        dto.setSdsFilePath(batch.getSdsFilePath());
        dto.setCertificateFilePath(batch.getCertificateFilePath());
        dto.setNote(batch.getNote());

        return dto;
    }
}