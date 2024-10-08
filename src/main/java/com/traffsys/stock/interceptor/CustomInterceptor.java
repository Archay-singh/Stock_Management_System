package com.traffsys.stock.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomInterceptor implements HandlerInterceptor {

	  @Override
	    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		  
	        // Extract the token from the Authorization header
		  
	        String authorizationHeader = request.getHeader("Authorization");
	        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
	            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	            response.getWriter().write("Unauthorized: Missing or invalid token");
	            return false; 
	        }

	        String token = authorizationHeader.substring(7); 
	        // now validate the token
	        
	        if (!validateToken(token)) {
	            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	            response.getWriter().write("Unauthorized: Invalid token");
	            return false; 
	        }

	        return true; 
	    }

	    @Override
	    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	       
	    }

	    @Override
	    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
	       
	    }

	    
	    private boolean validateToken(String token) {
			return false;
	  
	    }
	}