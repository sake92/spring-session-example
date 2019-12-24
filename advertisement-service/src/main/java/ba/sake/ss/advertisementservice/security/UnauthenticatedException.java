package ba.sake.ss.advertisementservice.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class UnauthenticatedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

}
