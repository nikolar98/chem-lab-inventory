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
@Table(name = "chemical_batches", schema = "chem_lab_inventory")
public class ChemicalBatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "chemical_id", nullable = false)
    private Chemical chemical;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsible_user_id")
    private User responsibleUser;

    @Size(max = 100)
    @Column(name = "purity", length = 100)
    private String purity;

    @Column(name = "package_size", precision = 12, scale = 3)
    private BigDecimal packageSize;

    @Size(max = 20)
    @Column(name = "package_unit", length = 20)
    private String packageUnit;

    @Column(name = "purchased_quantity")
    private Integer purchasedQuantity;

    @Column(name = "total_quantity", precision = 12, scale = 3)
    private BigDecimal totalQuantity;

    @Column(name = "current_quantity", precision = 12, scale = 3)
    private BigDecimal currentQuantity;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "price", precision = 12, scale = 2)
    private BigDecimal price;

    @Column(name = "minimum_quantity_alarm", precision = 12, scale = 3)
    private BigDecimal minimumQuantityAlarm;

    @Size(max = 500)
    @Column(name = "sds_file_path", length = 500)
    private String sdsFilePath;

    @Size(max = 500)
    @Column(name = "certificate_file_path", length = 500)
    private String certificateFilePath;

    @Size(max = 500)
    @Column(name = "note", length = 500)
    private String note;


}