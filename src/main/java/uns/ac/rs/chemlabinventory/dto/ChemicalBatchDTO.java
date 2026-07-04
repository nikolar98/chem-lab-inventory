package uns.ac.rs.chemlabinventory.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ChemicalBatchDTO {

    private Long id;

    @NotNull(message = "Morate izabrati hemikaliju.")
    private Long chemicalId;

    private Long manufacturerId;
    private Long locationId;
    private Long responsibleUserId;

    private String chemicalName;
    private String manufacturerName;
    private String locationName;
    private String responsibleUserFullName;

    private LocalDate lastUsageDate;
    private Long lastUsageId;

    @Size(max = 100, message = "Čistoća ne sme biti duža od 100 karaktera.")
    private String purity;

    private BigDecimal packageSize;

    @Size(max = 20, message = "Jedinica pakovanja ne sme biti duža od 20 karaktera.")
    private String packageUnit;

    private Integer purchasedQuantity;

    private BigDecimal totalQuantity;
    private BigDecimal currentQuantity;

    private LocalDate expirationDate;

    private BigDecimal packagePrice;

    private BigDecimal totalPrice;

    private BigDecimal minimumQuantityAlarm;

    private boolean opened;

    @Size(max = 500, message = "Putanja do SDS fajla ne sme biti duža od 500 karaktera.")
    private String sdsFilePath;

    @Size(max = 500, message = "Putanja do sertifikata ne sme biti duža od 500 karaktera.")
    private String certificateFilePath;

    @Size(max = 500, message = "Napomena ne sme biti duža od 500 karaktera.")
    private String note;
}