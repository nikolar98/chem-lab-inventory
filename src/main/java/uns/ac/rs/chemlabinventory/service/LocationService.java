package uns.ac.rs.chemlabinventory.service;

import org.springframework.stereotype.Service;
import uns.ac.rs.chemlabinventory.model.Location;
import uns.ac.rs.chemlabinventory.repository.LocationRepository;

import java.util.List;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    public Location findById(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lokacija nije pronadjena."));
    }

    public Location save(Location location) {
        return locationRepository.save(location);
    }

    public void delete(Long id) {
        locationRepository.deleteById(id);
    }
}