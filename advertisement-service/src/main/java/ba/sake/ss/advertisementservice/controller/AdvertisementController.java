package ba.sake.ss.advertisementservice.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ba.sake.ss.advertisementservice.model.User;
import ba.sake.ss.advertisementservice.repository.UserRepository;
import ba.sake.ss.advertisementservice.security.ForbiddenAccessException;
import ba.sake.ss.advertisementservice.security.SecurityUtils;
import ba.sake.ss.advertisementservice.security.UnauthenticatedException;
import ba.sake.ss.common.SwaUserDetails;

@RestController
@RequestMapping("/users")
public class AdvertisementController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{userId}/advertisements")
    @PreAuthorize("isAuthenticated()")
    public Object getAdvertisements(@PathVariable Long userId) {
        checkHasPermission(userId);
        Map<String, Object> map = new HashMap<>();
        map.put("title", "Worksssss!");
        return map;
    }

    /*
     * Throws ForbiddenAccessException if a user tries to access other user's resource.
     */
    private void checkHasPermission(Long userId) {
        SwaUserDetails currentUserDetails = SecurityUtils.currentUser();
        User currentUser = userRepository.findOneByUsername(currentUserDetails.getUsername());
        if (currentUser == null) {
            throw new UnauthenticatedException();
        }
        if (userId != currentUser.getId()) {
            throw new ForbiddenAccessException();
        }
    }

}
