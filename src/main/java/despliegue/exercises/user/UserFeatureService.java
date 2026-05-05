package despliegue.exercises.user;

import despliegue.exercises.models.Ticket;
import despliegue.exercises.models.TicketStatus;
import despliegue.exercises.models.User;
import despliegue.exercises.repositories.TicketRepository;
import despliegue.exercises.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserFeatureService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    // --- Gestión de Perfil ---
    public User updateProfile(String email, User profileData) {
        User user = userRepository.findByEmail(email).orElseThrow();
        user.setFirstname(profileData.getFirstname());
        user.setLastname(profileData.getLastname());
        return userRepository.save(user);
    }

    // --- Gestión de Tickets ---
    public Ticket createTicket(String email, Ticket ticket) {
        User user = userRepository.findByEmail(email).orElseThrow();
        ticket.setUserId(user.getId());
        ticket.setStatus(TicketStatus.ABIERTO);
        ticket.setCreatedAt(LocalDateTime.now());
        return ticketRepository.save(ticket);
    }

    public List<Ticket> getMyTickets(String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        return ticketRepository.findByUserId(user.getId());
    }

    // --- Consulta de Disponibilidad (Mock) ---
    public Map<String, Object> checkAvailability() {
        return Map.of(
            "disponibilidad", "Alta",
            "slots_libres", 15,
            "proxima_fecha_disponible", LocalDateTime.now().plusDays(1).toString()
        );
    }
}
