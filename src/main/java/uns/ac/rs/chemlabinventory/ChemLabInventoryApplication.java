package uns.ac.rs.chemlabinventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ChemLabInventoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChemLabInventoryApplication.class, args);
    }

}
