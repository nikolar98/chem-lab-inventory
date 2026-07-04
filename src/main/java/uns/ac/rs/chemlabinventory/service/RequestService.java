package uns.ac.rs.chemlabinventory.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uns.ac.rs.chemlabinventory.model.*;
import uns.ac.rs.chemlabinventory.repository.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class RequestService {

    private final RequestRepository requestRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ChemicalBatchRepository chemicalBatchRepository;
    private final ConsumptionRepository consumptionRepository;

    public RequestService(RequestRepository requestRepository,
                          PasswordEncoder passwordEncoder,
                          UserRepository userRepository,
                          RoleRepository roleRepository,
                          ChemicalBatchRepository chemicalBatchRepository,
                          ConsumptionRepository consumptionRepository) {

        this.requestRepository = requestRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.chemicalBatchRepository = chemicalBatchRepository;
        this.consumptionRepository = consumptionRepository;
    }

    public Request createRegistrationRequest(String fullName, String email, String password) {
        Request request = new Request();

        request.setType("ACCOUNT_REGISTRATION");
        request.setStatus("PENDING");
        request.setFullName(fullName);
        request.setEmail(email);
        request.setPassword(passwordEncoder.encode(password));
        request.setComment("Zahtev za kreiranje naloga");

        return requestRepository.save(request);
    }

    public Request createChemicalUsageRequest(Long chemicalBatchId,
                                              BigDecimal requestedQuantity,
                                              String purpose,
                                              String comment,
                                              String userEmail) {

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Korisnik nije pronađen."));

        Request request = new Request();

        request.setType("CHEMICAL_USAGE");
        request.setStatus("PENDING");
        request.setChemicalBatchId(chemicalBatchId);
        request.setRequestedQuantity(requestedQuantity);
        request.setPurpose(purpose);
        request.setComment(comment);
        request.setEmail(user.getEmail());
        request.setFullName(user.getFirstName() + " " + user.getLastName());

        return requestRepository.save(request);
    }

    public void approveRequest(Long requestId) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Zahtev nije pronađen"));

        if ("ACCOUNT_REGISTRATION".equals(request.getType())) {
            approveRegistrationRequest(request);
            return;
        }

        if ("CHEMICAL_USAGE".equals(request.getType())) {
            approveChemicalUsageRequest(request);
            return;
        }

        throw new RuntimeException("Nepoznat tip zahteva: " + request.getType());
    }

    private void approveRegistrationRequest(Request request) {
        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("USER rola nije pronađena"));

        String[] nameParts = request.getFullName().split(" ", 2);

        User user = new User();
        user.setFirstName(nameParts[0]);
        user.setLastName(nameParts.length > 1 ? nameParts[1] : "");
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setActive(true);
        user.setRole(userRole);

        userRepository.save(user);

        request.setStatus("APPROVED");
        requestRepository.save(request);
    }

    private void approveChemicalUsageRequest(Request request) {
        ChemicalBatch batch = chemicalBatchRepository.findById(request.getChemicalBatchId())
                .orElseThrow(() -> new RuntimeException("Zaliha nije pronađena"));

        BigDecimal current = batch.getCurrentQuantity();
        BigDecimal requested = request.getRequestedQuantity();

        if (current == null || requested == null || requested.compareTo(current) > 0) {
            throw new RuntimeException("Nema dovoljno dostupne količine.");
        }

        batch.setCurrentQuantity(current.subtract(requested));
        batch.setOpened(true);
        chemicalBatchRepository.save(batch);

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Korisnik nije pronađen."));

        Consumption consumption = new Consumption();
        consumption.setChemicalBatch(batch);
        consumption.setUser(user);
        consumption.setQuantity(requested);
        consumption.setUnit(batch.getPackageUnit());
        consumption.setDateTaken(LocalDate.now());
        consumption.setPurpose(request.getPurpose());
        consumption.setNote(request.getComment());

        consumptionRepository.save(consumption);

        request.setStatus("APPROVED");
        requestRepository.save(request);
    }

    public void rejectRequest(Long requestId) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Zahtev nije pronađen"));

        request.setStatus("REJECTED");
        requestRepository.save(request);
    }

    public List<Request> findAll() {
        return requestRepository.findAll();
    }

    public List<Request> findByUserEmail(String email) {
        return requestRepository.findByEmailOrderByCreatedAtDesc(email);
    }
}