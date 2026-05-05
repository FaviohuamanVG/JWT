package despliegue.exercises.user;

import despliegue.exercises.models.Ticket;
import despliegue.exercises.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserFeatureController {

    private final UserFeatureService service;

    @PutMapping("/profile")
    public ResponseEntity<User> updateProfile(Authentication authentication, @RequestBody User profileData) {
        return ResponseEntity.ok(service.updateProfile(authentication.getName(), profileData));
    }

    @PostMapping("/tickets")
    public ResponseEntity<Ticket> createTicket(Authentication authentication, @RequestBody Ticket ticket) {
        return ResponseEntity.ok(service.createTicket(authentication.getName(), ticket));
    }

    @GetMapping("/tickets")
    public ResponseEntity<List<Ticket>> getMyTickets(Authentication authentication) {
        return ResponseEntity.ok(service.getMyTickets(authentication.getName()));
    }

    @GetMapping("/availability")
    public ResponseEntity<Map<String, Object>> checkAvailability() {
        return ResponseEntity.ok(service.checkAvailability());
    }
}
