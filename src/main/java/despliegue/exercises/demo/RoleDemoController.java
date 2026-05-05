package despliegue.exercises.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class RoleDemoController {

    @GetMapping("/user/profile")
    public ResponseEntity<String> userProfile() {
        return ResponseEntity.ok("Perfil de usuario: Accesible por USER y ADMIN");
    }

    @GetMapping("/admin/dashboard")
    public ResponseEntity<String> adminDashboard() {
        return ResponseEntity.ok("Panel de Control: ¡Solo accesible por ADMIN!");
    }
}
