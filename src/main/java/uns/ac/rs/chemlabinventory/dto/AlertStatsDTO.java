package uns.ac.rs.chemlabinventory.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AlertStatsDTO {
    private long critical;
    private long expiring;
    private long opened;
}
