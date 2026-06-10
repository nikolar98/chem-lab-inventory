package uns.ac.rs.chemlabinventory.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private Long id;

    @NotBlank(message = "Ime je obavezno.")
    @Size(max = 100, message = "Ime ne sme biti duže od 100 karaktera.")
    private String firstName;

    @NotBlank(message = "Prezime je obavezno.")
    @Size(max = 100, message = "Prezime ne sme biti duže od 100 karaktera.")
    private String lastName;

    @NotBlank(message = "Email je obavezan.")
    @Email(message = "Email format nije ispravan.")
    @Size(max = 150, message = "Email ne sme biti duži od 150 karaktera.")
    private String email;

    @NotNull(message = "Status aktivnosti je obavezan.")
    private Boolean active;

    @NotNull(message = "Uloga korisnika je obavezna.")
    private Long roleId;

    private String roleName;
}