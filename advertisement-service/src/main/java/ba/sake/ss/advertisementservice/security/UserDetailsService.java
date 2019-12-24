package ba.sake.ss.advertisementservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ba.sake.ss.advertisementservice.model.User;
import ba.sake.ss.advertisementservice.repository.UserRepository;
import ba.sake.ss.common.SwaUserDetails;

@Component("userDetailsService")
public class UserDetailsService
        implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findOneByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " does not exist!");
        }
        return new SwaUserDetails(user);
    }

}
