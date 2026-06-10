package uns.ac.rs.chemlabinventory.controller;

import org.springframework.web.bind.annotation.*;
import uns.ac.rs.chemlabinventory.model.Chemical;
import uns.ac.rs.chemlabinventory.service.ChemicalService;

import java.util.List;

@RestController
@RequestMapping("/api/chemicals")
public class ChemicalController {

    private final ChemicalService chemicalService;

    public ChemicalController(ChemicalService chemicalService) {
        this.chemicalService = chemicalService;
    }

    @GetMapping
    public List<Chemical> findAll() {
        return chemicalService.findAll();
    }

    @GetMapping("/{id}")
    public Chemical findById(@PathVariable Long id) {
        return chemicalService.findById(id);
    }

    @PostMapping
    public Chemical save(@RequestBody Chemical chemical) {
        return chemicalService.save(chemical);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        chemicalService.delete(id);
    }
}
