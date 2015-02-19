package users.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import users.model.User;
import users.repository.UserRepository;

@Service
public class SecurityService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public CurrentUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findOneByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " was not found"));
        return new CurrentUser(user);
    }
}
