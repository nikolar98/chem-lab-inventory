package uns.ac.rs.chemlabinventory.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uns.ac.rs.chemlabinventory.model.User;
import uns.ac.rs.chemlabinventory.repository.ConsumptionRepository;
import uns.ac.rs.chemlabinventory.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ConsumptionRepository consumptionRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, ConsumptionRepository consumptionRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.consumptionRepository = consumptionRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Korisnik nije pronadjen."));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Korisnik sa emailom " + email + " nije pronađen."));
    }

    public User save(User user) {
        if (user.getPassword() != null && !user.getPassword().startsWith("$2a$")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(user);
    }

    public User changeUserActivity(Long id, boolean requestedActive) {
        User user = findById(id);

        if (user.isActive() == requestedActive) {
            if (requestedActive) {
                throw new IllegalArgumentException("Nalog je već bio aktivan!");
            } else {
                throw new IllegalArgumentException("Nalog je već bio neaktivan!");
            }
        }

        userRepository.updateUserActivity(id, requestedActive);
        user.setActive(requestedActive);
        return userRepository.save(user);
    }

    @Transactional
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Korisnik ne postoji.");
        }

        consumptionRepository.deleteByUserId(id);
        userRepository.deleteById(id);
    }
}