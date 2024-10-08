package com.traffsys.stock.security;

import java.io.IOException; 
import java.io.PrintWriter;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter writer = response.getWriter();
        writer.println("Access Denied !! " + authException.getMessage());
        
    }
}































/**
This class handles authentication errors by implementing AuthenticationEntryPoint.
When a request is made without valid authentication, it responds with a 401 status code and a message indicating that access is denied.
This is useful for providing clear feedback to users when they try to access protected resources without proper authentication.

**/