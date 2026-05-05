package despliegue.exercises.user;

import despliegue.exercises.models.AuditLog;
import despliegue.exercises.models.Ticket;
import despliegue.exercises.models.TicketStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminFeatureController {

    private final AdminFeatureService service;

    @GetMapping("/tickets")
    public ResponseEntity<List<Ticket>> getAllTickets() {
        return ResponseEntity.ok(service.getAllTickets());
    }

    @PatchMapping("/tickets/{id}/status")
    public ResponseEntity<Ticket> updateTicketStatus(
            @PathVariable String id, 
            @RequestBody Map<String, String> statusMap,
            Authentication authentication
    ) {
        TicketStatus status = TicketStatus.valueOf(statusMap.get("status"));
        return ResponseEntity.ok(service.updateTicketStatus(id, status, authentication.getName()));
    }

    @GetMapping("/audit")
    public ResponseEntity<List<AuditLog>> getAuditLogs() {
        return ResponseEntity.ok(service.getAuditLogs());
    }
}
