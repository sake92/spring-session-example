package ba.sake.ss.userservice.security;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import ba.sake.ss.common.SwaUserDetails;
import ba.sake.ss.userservice.model.User;
import ba.sake.ss.userservice.repository.UserRepository;

/**
 * Don't redirect to somewhere, rather return a User JSON with OK.
 */
@Component
public class RestAuthenticationSuccessHandler
        extends SavedRequestAwareAuthenticationSuccessHandler {

    private ObjectMapper mapper;
    private UserRepository userRepository;

    @Autowired
    public RestAuthenticationSuccessHandler(MappingJackson2HttpMessageConverter messageConverter,
            UserRepository userRepository) {
        this.mapper = messageConverter.getObjectMapper();
        this.userRepository = userRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        SwaUserDetails userDetails = SecurityUtils.currentUser();
        User user = userRepository.findOneByUsername(userDetails.getUsername());
        PrintWriter writer = response.getWriter();
        mapper.writeValue(writer, user);
        writer.flush();
    }
}
