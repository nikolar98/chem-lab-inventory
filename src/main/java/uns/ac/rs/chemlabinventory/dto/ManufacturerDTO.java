package uns.ac.rs.chemlabinventory.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManufacturerDTO {
    private Long id;

    @NotBlank(message = "Naziv proizvođača je obavezan.")
    @Size(max = 150, message = "Naziv proizvođača ne sme biti duži od 150 karaktera.")
    private String name;
}