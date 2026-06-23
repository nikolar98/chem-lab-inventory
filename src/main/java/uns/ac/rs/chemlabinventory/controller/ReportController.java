package uns.ac.rs.chemlabinventory.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import uns.ac.rs.chemlabinventory.service.ReportService;

@RestController
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/api/reports/chemical-inventory-value")
    public ResponseEntity<byte[]> generateReport() {

        byte[] pdf =
                reportService.generateChemicalInventoryValueReport();

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=chemical_cost_report.pdf"
                )
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}