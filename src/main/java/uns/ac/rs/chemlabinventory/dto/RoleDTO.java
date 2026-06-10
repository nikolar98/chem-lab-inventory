package uns.ac.rs.chemlabinventory.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDTO {
    private Long id;

    @NotBlank(message = "Naziv uloge je obavezan.")
    @Size(max = 50, message = "Naziv uloge ne sme biti duži od 50 karaktera.")
    private String name;
}