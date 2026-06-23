package uns.ac.rs.chemlabinventory.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uns.ac.rs.chemlabinventory.model.User;
import uns.ac.rs.chemlabinventory.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Korisnik nije pronadjen."));
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

        user.setActive(requestedActive);
        return userRepository.save(user);
    }
}