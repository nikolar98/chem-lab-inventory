package uns.ac.rs.chemlabinventory.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uns.ac.rs.chemlabinventory.service.ReportService;

import java.time.LocalDate;
import java.util.List;

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
                        "attachment; filename=chemical_cost_report.pdf"
                )
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

    @GetMapping("/api/reports/custom")
    public ResponseEntity<byte[]> generateCustomReport(
            @RequestParam("fromDate") String fromDateStr,
            @RequestParam("toDate") String toDateStr,
            @RequestParam(value = "chemicalIds", required = false) List<Long> chemicalIds,
            @RequestParam(value = "includePotrosnja", defaultValue = "false") boolean includePotrosnja,
            @RequestParam(value = "includeStanje", defaultValue = "false") boolean includeStanje,
            @RequestParam(value = "includeCena", defaultValue = "false") boolean includeCena) {

        LocalDate fromDate = LocalDate.parse(fromDateStr);
        LocalDate toDate = LocalDate.parse(toDateStr);

        byte[] pdf = reportService.generateCustomChemicalReport(
                fromDateStr, toDateStr, chemicalIds, includePotrosnja, includeStanje, includeCena
        );

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Izvestaj_o_hemikalijama.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}