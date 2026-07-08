package uns.ac.rs.chemlabinventory.service;

import org.springframework.stereotype.Service;
import uns.ac.rs.chemlabinventory.dto.AlertStatsDTO;
import uns.ac.rs.chemlabinventory.dto.ChemicalBatchDTO;
import uns.ac.rs.chemlabinventory.model.ChemicalBatch;
import uns.ac.rs.chemlabinventory.repository.ChemicalBatchRepository;
import uns.ac.rs.chemlabinventory.repository.ConsumptionRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ChemicalBatchService {

    private final ChemicalBatchRepository chemicalBatchRepository;
    private final ConsumptionRepository consumptionRepository;

    public ChemicalBatchService(ChemicalBatchRepository chemicalBatchRepository, ConsumptionRepository consumptionRepository) {
        this.chemicalBatchRepository = chemicalBatchRepository;
        this.consumptionRepository = consumptionRepository;
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

    public ChemicalBatch updateQuantityAndNote(Long id, BigDecimal newQuantity, String note) {
        ChemicalBatch batch = findById(id);

        BigDecimal oldQuantity = batch.getCurrentQuantity();

        batch.setCurrentQuantity(newQuantity);
        batch.setNote(note);

        if (oldQuantity != null && newQuantity != null && newQuantity.compareTo(oldQuantity) < 0) {
            batch.setOpened(true);
        }

        return chemicalBatchRepository.save(batch);
    }

    private ChemicalBatchDTO toDto(ChemicalBatch batch) {
        ChemicalBatchDTO dto = new ChemicalBatchDTO();

        dto.setId(batch.getId());

        dto.setOpened(batch.getOpened());

        consumptionRepository.findFirstByChemicalBatchIdOrderByIdDesc(batch.getId())
                .ifPresent(consumption -> {
                    dto.setLastUsageDate(consumption.getDateTaken());
                    dto.setLastUsageId(consumption.getId());
                });

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

        if (batch.getCurrentQuantity() != null && batch.getPackageSize() != null
                && batch.getPackageSize().compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal[] divAndRem = batch.getCurrentQuantity().divideAndRemainder(batch.getPackageSize());
            dto.setFullPackagesCount(divAndRem[0].intValue());
            dto.setOpenPackageRemainder(divAndRem[1]);
            dto.setOpened(divAndRem[1].compareTo(BigDecimal.ZERO) > 0);
        } else {
            dto.setFullPackagesCount(batch.getPurchasedQuantity());
            dto.setOpenPackageRemainder(BigDecimal.ZERO);
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

        if (batch.getCurrentQuantity() != null && batch.getPackageSize() != null
                && batch.getPackageSize().compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal[] divAndRem = batch.getCurrentQuantity().divideAndRemainder(batch.getPackageSize());
            dto.setFullPackagesCount(divAndRem[0].intValue());
            dto.setOpenPackageRemainder(divAndRem[1]);
        } else {
            dto.setFullPackagesCount(batch.getPurchasedQuantity());
            dto.setOpenPackageRemainder(BigDecimal.ZERO);
        }

        return dto;
    }

    public AlertStatsDTO getAlertStats() {
        List<ChemicalBatch> all = chemicalBatchRepository.findAll();
        long opened = chemicalBatchRepository.countByOpenedTrue();

        long today = System.currentTimeMillis();
        long thirtyDaysMs = 30L * 24 * 60 * 60 * 1000;
        long critical = 0;
        long expiring = 0;

        for (ChemicalBatch b : all) {
            if (b.getMinimumQuantityAlarm() != null && b.getCurrentQuantity() != null) {
                if (b.getCurrentQuantity().compareTo(b.getMinimumQuantityAlarm()) <= 0) critical++;
            }
            if (b.getExpirationDate() != null) {
                long expDate = java.sql.Date.valueOf(b.getExpirationDate()).getTime();
                if (expDate < today) critical++;
                else if (expDate <= (today + thirtyDaysMs)) expiring++;
            }
        }
        return new AlertStatsDTO(critical, expiring, opened);
    }
}