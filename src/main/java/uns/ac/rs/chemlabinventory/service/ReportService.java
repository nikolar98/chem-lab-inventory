package uns.ac.rs.chemlabinventory.service;

import net.sf.jasperreports.engine.*;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;

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
}