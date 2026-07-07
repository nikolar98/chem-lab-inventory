package uns.ac.rs.chemlabinventory.controller;

import org.springframework.web.bind.annotation.*;
import uns.ac.rs.chemlabinventory.model.Consumption;
import uns.ac.rs.chemlabinventory.service.ConsumptionService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/consumptions")
public class ConsumptionController {

    private final ConsumptionService consumptionService;

    public ConsumptionController(ConsumptionService consumptionService) {
        this.consumptionService = consumptionService;
    }

    @GetMapping
    public List<Consumption> findAll() {
        return consumptionService.findAll();
    }

    @GetMapping("/{id}")
    public Consumption findById(@PathVariable Long id) {
        return consumptionService.findById(id);
    }

    @GetMapping("/batch/{batchId}")
    public List<Consumption> findByChemicalBatch(@PathVariable Long batchId) {
        return consumptionService.findByChemicalBatchId(batchId);
    }

    @PostMapping
    public Consumption save(@RequestBody Consumption consumption) {

        consumption.setDateTaken(LocalDateTime.now());

        return consumptionService.save(consumption);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        consumptionService.delete(id);
    }
}