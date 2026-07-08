package uns.ac.rs.chemlabinventory.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uns.ac.rs.chemlabinventory.dto.ConsumptionDTO;
import uns.ac.rs.chemlabinventory.model.Consumption;
import uns.ac.rs.chemlabinventory.service.ConsumptionService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/consumptions")
public class ConsumptionController {

    private final ConsumptionService consumptionService;

    public ConsumptionController(ConsumptionService consumptionService) {
        this.consumptionService = consumptionService;
    }

    @GetMapping
    public List<ConsumptionDTO> findAll() {
        return consumptionService.findAll().stream()
                .map(ConsumptionDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Consumption findById(@PathVariable Long id) {
        return consumptionService.findById(id);
    }

    @GetMapping("/batch/{batchId}")
    public List<Consumption> findByChemicalBatch(@PathVariable Long batchId) {
        return consumptionService.findByChemicalBatchId(batchId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        consumptionService.delete(id);
    }

    @PostMapping
    public ResponseEntity<ConsumptionDTO> save(@Valid @RequestBody ConsumptionDTO consumptionDTO) {
        ConsumptionDTO savedDTO = consumptionService.createFromDTO(consumptionDTO);
        return ResponseEntity.ok(savedDTO);
    }
}