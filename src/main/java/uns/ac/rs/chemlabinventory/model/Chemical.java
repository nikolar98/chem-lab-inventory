package uns.ac.rs.chemlabinventory.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "chemicals", schema = "chem_lab_inventory")
public class Chemical {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 200)
    @NotNull
    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Size(max = 20)
    @NotNull
    @Column(name = "unit", nullable = false, length = 20)
    private String unit;

    @Column(name = "minimum_quantity", precision = 12, scale = 3)
    private BigDecimal minimumQuantity;

    @Size(max = 500)
    @Column(name = "description", length = 500)
    private String description;


}