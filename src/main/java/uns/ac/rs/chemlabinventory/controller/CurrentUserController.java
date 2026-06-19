package uns.ac.rs.chemlabinventory.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CurrentUserController {

    @GetMapping("/api/current-user")
    public Map<String, String> currentUser(Authentication authentication) {

        String email = authentication.getName();

        String role = authentication.getAuthorities()
                .iterator()
                .next()
                .getAuthority()
                .replace("ROLE_", "");

        return Map.of(
                "email", email,
                "role", role
        );
    }
}