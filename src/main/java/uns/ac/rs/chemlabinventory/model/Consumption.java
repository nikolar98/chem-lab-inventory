package uns.ac.rs.chemlabinventory.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "consumptions", schema = "chem_lab_inventory")
public class Consumption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "chemical_batch_id", nullable = false)
    private ChemicalBatch chemicalBatch;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @Column(name = "quantity", nullable = false, precision = 12, scale = 3)
    private BigDecimal quantity;

    @Size(max = 20)
    @NotNull
    @Column(name = "unit", nullable = false, length = 20)
    private String unit;

    @NotNull
    @Column(name = "date_taken", nullable = false)
    private LocalDate dateTaken;

    @Size(max = 500)
    @Column(name = "note", length = 500)
    private String note;

    @Size(max = 100)
    @Column(name = "purpose", length = 100)
    private String purpose;


}