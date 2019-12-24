package ba.sake.ss.userservice.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ba.sake.ss.common.SwaUserDetails;

public class SecurityUtils {

    public static SwaUserDetails currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        } else {
            Object principal = authentication.getPrincipal();
            if (principal instanceof SwaUserDetails)
                return (SwaUserDetails) principal;
            else
                return null;
        }
    }

}
