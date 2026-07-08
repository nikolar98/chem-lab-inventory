package uns.ac.rs.chemlabinventory.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uns.ac.rs.chemlabinventory.model.Consumption;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ConsumptionDTO {

    private Long id;

    @NotNull(message = "Identifikator zalihe je obavezan.")
    private Long chemicalBatchId;

    private String chemicalName;

    @NotNull(message = "Korisnik koji preuzima je obavezan.")
    private Long userId;

    private String userFullName;

    @NotNull(message = "Količina potrošnje je obavezna.")
    private BigDecimal quantity;

    @NotBlank(message = "Jedinica mere je obavezna.")
    @Size(max = 20, message = "Jedinica mere ne sme biti duža od 20 karaktera.")
    private String unit;

    @NotNull(message = "Datum preuzimanja je obavezan.")
    private LocalDateTime dateTaken;

    @Size(max = 500, message = "Napomena ne sme biti duža od 500 karaktera.")
    private String note;

    private String purpose;
    private String locationName;
    private String bottleStatusAfter;
    private Integer fullPackagesCount;
    private BigDecimal openPackageRemainder;

    public ConsumptionDTO(Consumption consumption) {
        this.id = consumption.getId();
        this.quantity = consumption.getQuantity();
        this.unit = consumption.getUnit();
        this.dateTaken = consumption.getDateTaken();
        this.note = consumption.getNote();
        this.purpose = consumption.getPurpose();

        if (consumption.getChemicalBatch() != null) {
            this.chemicalBatchId = consumption.getChemicalBatch().getId();
            this.chemicalName = consumption.getChemicalBatch().getChemical().getName();
            this.bottleStatusAfter = consumption.getChemicalBatch().getBottleStatus();

            if (consumption.getChemicalBatch().getLocation() != null) {
                this.locationName = consumption.getChemicalBatch().getLocation().getName();
            }
        }

        if (consumption.getUser() != null) {
            this.userId = consumption.getUser().getId();
            this.userFullName = consumption.getUser().getFirstName() + " " + consumption.getUser().getLastName();
        }
    }
}