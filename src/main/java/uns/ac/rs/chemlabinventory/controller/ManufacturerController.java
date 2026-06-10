package uns.ac.rs.chemlabinventory.controller;

import org.springframework.web.bind.annotation.*;
import uns.ac.rs.chemlabinventory.model.Manufacturer;
import uns.ac.rs.chemlabinventory.service.ManufacturerService;

import java.util.List;

@RestController
@RequestMapping("/api/manufacturers")
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public List<Manufacturer> findAll() {
        return manufacturerService.findAll();
    }

    @GetMapping("/{id}")
    public Manufacturer findById(@PathVariable Long id) {
        return manufacturerService.findById(id);
    }

    @PostMapping
    public Manufacturer save(@RequestBody Manufacturer manufacturer) {
        return manufacturerService.save(manufacturer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        manufacturerService.delete(id);
    }
}