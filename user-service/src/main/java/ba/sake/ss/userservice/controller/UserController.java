package ba.sake.ss.userservice.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ba.sake.ss.common.SwaUserDetails;
import ba.sake.ss.userservice.dto.CreateUserDTO;
import ba.sake.ss.userservice.model.User;
import ba.sake.ss.userservice.repository.UserRepository;
import ba.sake.ss.userservice.security.SecurityUtils;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/current")
    public User current() {
        SwaUserDetails currentUserDetails = SecurityUtils.currentUser();
        if (currentUserDetails != null) {
            User currentUser = userRepository.findOneByUsername(currentUserDetails.getUsername());
            return currentUser;
        } else {
            return null;
        }
    }

    @PostMapping
    public User create(@Valid @RequestBody CreateUserDTO createUserDTO) {
        User user = new User();
        user.setEmail(createUserDTO.getUsername());
        user.setUsername(createUserDTO.getUsername());
        user.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));
        user.setEmail(createUserDTO.getEmail());
        user.setFirstName(createUserDTO.getFirstName());
        user.setLastName(createUserDTO.getLastName());
        user = userRepository.save(user);
        return user;
    }

}
