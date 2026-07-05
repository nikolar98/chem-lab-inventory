package uns.ac.rs.chemlabinventory.service;

import net.sf.jasperreports.engine.*;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    private final DataSource dataSource;

    public ReportService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public byte[] generateChemicalInventoryValueReport() {

        try (
                Connection connection = dataSource.getConnection();
                InputStream reportStream =
                        getClass().getResourceAsStream(
                                "/reports/chemical_cost_report.jrxml")
        ) {

            JasperReport jasperReport =
                    JasperCompileManager.compileReport(reportStream);

            JasperPrint jasperPrint =
                    JasperFillManager.fillReport(
                            jasperReport,
                            null,
                            connection
                    );

            return JasperExportManager.exportReportToPdf(jasperPrint);

        } catch (Exception e) {
            throw new RuntimeException("Greška pri generisanju PDF izveštaja", e);
        }
    }

    public byte[] generateCustomChemicalReport(
            String fromDate,
            String toDate,
            List<Long> chemicalIds,
            boolean includePotrosnja,
            boolean includeStanje,
            boolean includeCena) {

        try (
                Connection connection = dataSource.getConnection();
                InputStream reportStream = getClass().getResourceAsStream("/reports/chemical_cost_report.jrxml")
        ) {
            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("fromDate", java.sql.Date.valueOf(fromDate));
            parameters.put("toDate", java.sql.Date.valueOf(toDate));
            parameters.put("chemicalIds", chemicalIds);
            parameters.put("includePotrosnja", includePotrosnja);
            parameters.put("includeStanje", includeStanje);
            parameters.put("includeCena", includeCena);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
            return JasperExportManager.exportReportToPdf(jasperPrint);

        } catch (Exception e) {
            throw new RuntimeException("Greška pri generisanju PDF izveštaja", e);
        }
    }
}