package uns.ac.rs.chemlabinventory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "requests", schema = "chem_lab_inventory")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 50)
    @NotNull
    @Column(name = "type", nullable = false, length = 50)
    private String type;

    @Size(max = 30)
    @NotNull
    @ColumnDefault("'PENDING'")
    @Column(name = "status", nullable = false, length = 30)
    private String status;

    @Size(max = 200)
    @Column(name = "full_name", length = 200)
    private String fullName;

    @Size(max = 150)
    @Column(name = "email", length = 150)
    private String email;

    @JsonIgnore
    @Size(max = 255)
    @Column(name = "password")
    private String password;

    @Size(max = 500)
    @Column(name = "comment", length = 500)
    private String comment;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
    @Column(name = "chemical_batch_id")
    private Long chemicalBatchId;

    @Column(name = "requested_quantity", precision = 12, scale = 3)
    private BigDecimal requestedQuantity;

    @Column(name = "purpose", length = 255)
    private String purpose;

    @Column(name = "needed_date")
    private LocalDate neededDate;
}