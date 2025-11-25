package hu.nje.java.gfekak.java.gyakorlat.security;

import hu.nje.java.gfekak.java.gyakorlat.data.User;
import hu.nje.java.gfekak.java.gyakorlat.data.UserRepository;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author danielbodi
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email: [%s] not found".formatted(email)));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole()));
    }
}
