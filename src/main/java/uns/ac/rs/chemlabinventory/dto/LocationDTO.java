package uns.ac.rs.chemlabinventory.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationDTO {
    private Long id;

    @NotBlank(message = "Naziv lokacije je obavezan.")
    @Size(max = 150, message = "Naziv lokacije ne sme biti duži od 150 karaktera.")
    private String name;

    @Size(max = 255, message = "Opis lokacije ne sme biti duži od 255 karaktera.")
    private String description;
}