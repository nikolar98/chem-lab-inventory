package uns.ac.rs.chemlabinventory.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class ChemicalDTO {

    private Long id;

    @NotBlank(message = "Naziv hemikalije je obavezan.")
    @Size(max = 200, message = "Naziv ne sme biti duži od 200 karaktera.")
    private String name;

    @NotBlank(message = "Jedinica mere je obavezna.")
    @Size(max = 20, message = "Jedinica mere ne sme biti duža od 20 karaktera.")
    private String unit;

    private BigDecimal minimumQuantity;

    @Size(max = 500, message = "Opis ne sme biti duži od 500 karaktera.")
    private String description;
}