package despliegue.exercises.user;

import despliegue.exercises.models.AuditLog;
import despliegue.exercises.models.Ticket;
import despliegue.exercises.models.TicketStatus;
import despliegue.exercises.repositories.AuditRepository;
import despliegue.exercises.repositories.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminFeatureService {

    private final TicketRepository ticketRepository;
    private final AuditRepository auditRepository;

    // --- Gestión de Tickets (Global) ---
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket updateTicketStatus(String ticketId, TicketStatus status, String adminEmail) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow();
        ticket.setStatus(status);
        
        // Registrar auditoría
        logAction("CHANGE_TICKET_STATUS", adminEmail, "Ticket ID: " + ticketId + " movido a " + status);
        
        return ticketRepository.save(ticket);
    }

    // --- Auditoría ---
    public List<AuditLog> getAuditLogs() {
        return auditRepository.findAllByOrderByTimestampDesc();
    }

    public void logAction(String action, String email, String details) {
        AuditLog log = AuditLog.builder()
                .action(action)
                .userEmail(email)
                .details(details)
                .timestamp(LocalDateTime.now())
                .build();
        auditRepository.save(log);
    }
}
