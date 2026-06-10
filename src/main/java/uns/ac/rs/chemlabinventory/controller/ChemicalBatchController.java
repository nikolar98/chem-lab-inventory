package uns.ac.rs.chemlabinventory.controller;

import org.springframework.web.bind.annotation.*;
import uns.ac.rs.chemlabinventory.model.ChemicalBatch;
import uns.ac.rs.chemlabinventory.service.ChemicalBatchService;

import java.util.List;

@RestController
@RequestMapping("/api/chemical-batches")
public class ChemicalBatchController {

    private final ChemicalBatchService chemicalBatchService;

    public ChemicalBatchController(ChemicalBatchService chemicalBatchService) {
        this.chemicalBatchService = chemicalBatchService;
    }

    @GetMapping
    public List<ChemicalBatch> findAll() {
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
    public ChemicalBatch update(@PathVariable Long id, @RequestBody ChemicalBatch chemicalBatch) {
        chemicalBatch.setId(id);
        return chemicalBatchService.save(chemicalBatch);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        chemicalBatchService.delete(id);
    }
}