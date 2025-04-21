package application.impacto_manager_back.security.service;

import application.impacto_manager_back.exceptions.CustomException;
import application.impacto_manager_back.exceptions.Error;
import application.impacto_manager_back.security.config.UserAuthenticated;
import application.impacto_manager_back.security.repository.UserRepository;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        try {
            if (userRepository.findByUsername(usernameOrEmail).isPresent()) {
                return userRepository.findByUsername(usernameOrEmail)
                  .map(UserAuthenticated::new)
                  .orElseThrow(() -> new UsernameNotFoundException(usernameOrEmail));
            }
            
            if (userRepository.findByEmail(usernameOrEmail).isPresent()) {
                return userRepository.findByEmail(usernameOrEmail)
                  .map(UserAuthenticated::new)
                  .orElseThrow(() -> new UsernameNotFoundException(usernameOrEmail));
            }
        } catch (Exception e) {
            throw new CustomException(
              Error.builder()
              .title("Invalid username or email")
              .message(e.getMessage())
              .httpStatus(HttpStatus.UNAUTHORIZED)
              .build());
        }
        
        return null;
    }
}
