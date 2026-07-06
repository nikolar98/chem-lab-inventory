package uns.ac.rs.chemlabinventory.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import uns.ac.rs.chemlabinventory.dto.UserDTO;
import uns.ac.rs.chemlabinventory.model.User;
import uns.ac.rs.chemlabinventory.service.UserService;

import java.util.Map;

@RestController
public class CurrentUserController {

    private final UserService userService;

    public CurrentUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/current-user")
    public Map<String, String> currentUser(Authentication authentication) {

        String email = authentication.getName();

        User user = userService.findByEmail(email);

        String role = authentication.getAuthorities()
                .iterator()
                .next()
                .getAuthority()
                .replace("ROLE_", "");

        return Map.of(
                "email", email,
                "role", role,
                "firstName", user.getFirstName(),
                "lastName", user.getLastName()
        );
    }
}