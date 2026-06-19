package uns.ac.rs.chemlabinventory.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uns.ac.rs.chemlabinventory.model.Request;
import uns.ac.rs.chemlabinventory.model.Role;
import uns.ac.rs.chemlabinventory.model.User;
import uns.ac.rs.chemlabinventory.repository.RequestRepository;
import uns.ac.rs.chemlabinventory.repository.RoleRepository;
import uns.ac.rs.chemlabinventory.repository.UserRepository;

import java.util.List;

@Service
public class RequestService {

    private final RequestRepository requestRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public RequestService(RequestRepository requestRepository,
                          PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.requestRepository = requestRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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

    public void approveRegistrationRequest(Long requestId) {

        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Zahtev nije pronađen"));

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

    public void rejectRegistrationRequest(Long requestId) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Zahtev nije pronađen"));

        request.setStatus("REJECTED");
        requestRepository.save(request);
    }

    public List<Request> findAll() {
        return requestRepository.findAll();
    }
}