package uns.ac.rs.chemlabinventory.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
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
    private LocalDate dateTaken;

    @Size(max = 500, message = "Napomena ne sme biti duža od 500 karaktera.")
    private String note;
}