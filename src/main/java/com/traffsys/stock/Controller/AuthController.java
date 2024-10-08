package com.traffsys.stock.Controller;

import java.util.HashMap;




import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.traffsys.stock.Service.UserService;
import com.traffsys.stock.dto.ApiResponse;
import com.traffsys.stock.entity.JwtRequest;

import com.traffsys.stock.security.JwtHelper;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
    @Autowired
    private UserService userService;

    @Autowired
    private JwtHelper helper;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    public ApiResponse login(@RequestBody JwtRequest request, HttpServletResponse httpServletResponse) {
        try {
        	Authentication authentication = authenticationManager
    				.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
    		if (authentication.isAuthenticated()) {
    			//userService.authenticate(null, null, httpServletResponse)
    			String token = helper.generateToken(request.getUserName());
    			Map<String, Object> data = new HashMap<>();
    			data.put("username", request.getUserName());
    			data.put("token", token);
    			return new ApiResponse(1, "Success", data);
    		} else {
    			return new ApiResponse(0, "Unauthorized", null);
    		}
            
        } catch (BadCredentialsException e) {
          
            logger.error("Authentication failed for user: {}", request.getUserName(), e);
            throw e;
        }
    }

    private ApiResponse doAuthenticate(String username, String password, HttpServletResponse httpServletResponse) {
    	return userService.authenticate(username, password, httpServletResponse);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse> handleBadCredentialsException(BadCredentialsException ex) {
        logger.error("Handling BadCredentialsException: {}", ex.getMessage());
        return new ResponseEntity<>(new ApiResponse(0, "Unauthorized", null), HttpStatus.UNAUTHORIZED);
    }
    
    
 }












