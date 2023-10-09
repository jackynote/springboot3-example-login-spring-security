package jackynote.pro.services;

import jackynote.pro.model.AuthUser;
import jackynote.pro.model.User;
import jackynote.pro.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User admin = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Cannot found user"));
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + admin.getRole()));
        AuthUser authUser = new AuthUser(username, admin.getPassword(), authorities);
        authUser.setId(admin.getId().toString());
        authUser.setDisplayName(admin.getFirstName());

        return authUser;
    }
}
