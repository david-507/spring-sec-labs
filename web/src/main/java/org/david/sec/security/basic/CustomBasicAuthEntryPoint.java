package org.david.sec.security.basic;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.david.sec.errors.ApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomBasicAuthEntryPoint extends BasicAuthenticationEntryPoint {
    private static final String REALM_NAME = "localhost";
    private final ObjectMapper mapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {

        response.addHeader(HttpHeaders.WWW_AUTHENTICATE, "Basic realm=\"" + getRealmName() + "\"");
        response.setContentType("application/json");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        ApiError error = new ApiError(HttpStatus.UNAUTHORIZED, authException.getMessage());
        String errorStr = mapper.writeValueAsString(error);

        response.getWriter().println(errorStr);
    }

    @PostConstruct
    public void initRealname() {
        setRealmName(REALM_NAME);
    }
}
