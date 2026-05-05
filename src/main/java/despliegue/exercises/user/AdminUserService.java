package despliegue.exercises.user;

import despliegue.exercises.models.User;
import despliegue.exercises.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminUserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getStatus() == null) user.setStatus("A");
        return repository.save(user);
    }

    public User update(String id, User userDetails) {
        User user = repository.findById(id).orElseThrow();
        user.setFirstname(userDetails.getFirstname());
        user.setLastname(userDetails.getLastname());
        user.setEmail(userDetails.getEmail());
        user.setRole(userDetails.getRole());
        return repository.save(user);
    }

    public User patchStatus(String id, String status) {
        User user = repository.findById(id).orElseThrow();
        user.setStatus(status);
        return repository.save(user);
    }
}
