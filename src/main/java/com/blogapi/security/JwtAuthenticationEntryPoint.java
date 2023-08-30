package com.blogapi.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException { //this is coming from AuthenticationEntryPoint.
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    }
}
//JwtAuthenticationEntryPoint- all UNAUTHORIZED error msg is automatically come here and it will check token is valid or not ,if not then send one UNAUTHORIZED error msg.
//it will check token is valid or not thi work done by JwtAuthenticationEntryPoint.