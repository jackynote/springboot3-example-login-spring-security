package jackynote.pro;

import jackynote.pro.model.User;
import jackynote.pro.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class BulkAdminUser {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public BulkAdminUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Init first user for login
     */
    public void initRootAdmin() {
        String username = "jackynote";
        userRepository.findByUsername(username).orElseGet(() -> {

            User user = new User();
            user.setId(100000L);
            user.setUsername("jackynote");
            user.setPassword(passwordEncoder.encode("jackynote.pro"));
            user.setFirstName("Jacky");
            user.setRole("ADMIN");
            userRepository.save(user);

            log.info("Created root user: {}", user.getId());
            return user;
        });

    }
}
