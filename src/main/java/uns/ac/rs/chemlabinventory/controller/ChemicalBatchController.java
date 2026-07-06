package uns.ac.rs.chemlabinventory.controller;

import org.springframework.web.bind.annotation.*;
import uns.ac.rs.chemlabinventory.dto.AlertStatsDTO;
import uns.ac.rs.chemlabinventory.dto.ChemicalBatchDTO;
import uns.ac.rs.chemlabinventory.model.ChemicalBatch;
import uns.ac.rs.chemlabinventory.service.ChemicalBatchService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chemical-batches")
public class ChemicalBatchController {

    private final ChemicalBatchService chemicalBatchService;

    public ChemicalBatchController(ChemicalBatchService chemicalBatchService) {
        this.chemicalBatchService = chemicalBatchService;
    }

    @GetMapping
    public List<ChemicalBatchDTO> getAllChemicalBatches() {
        return chemicalBatchService.findAll();
    }

    @GetMapping("/{id}")
    public ChemicalBatch findById(@PathVariable Long id) {
        return chemicalBatchService.findById(id);
    }

    @PostMapping
    public ChemicalBatch save(@RequestBody ChemicalBatch chemicalBatch) {
        return chemicalBatchService.save(chemicalBatch);
    }

    @PutMapping("/{id}")
    public ChemicalBatch update(@PathVariable Long id,
                                @RequestBody ChemicalBatch chemicalBatch) {
        chemicalBatch.setId(id);
        return chemicalBatchService.save(chemicalBatch);
    }

    @PutMapping("/{id}/quantity")
    public ChemicalBatch updateQuantity(@PathVariable Long id,
                                        @RequestBody Map<String, Object> body) {

        BigDecimal currentQuantity = new BigDecimal(body.get("currentQuantity").toString());
        String note = body.get("note") != null ? body.get("note").toString() : null;

        return chemicalBatchService.updateQuantityAndNote(id, currentQuantity, note);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        chemicalBatchService.delete(id);
    }

    @GetMapping("/stats")
    public AlertStatsDTO getStats() {
        return chemicalBatchService.getAlertStats();
    }
}