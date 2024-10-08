package com.traffsys.stock.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.traffsys.stock.Repository.UserRepository;
import com.traffsys.stock.dto.ApiResponse;
import com.traffsys.stock.entity.User;
import com.traffsys.stock.security.JwtHelper;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;  
 

    @Override
    public ApiResponse createUser(User user) {
        if (user == null) {
            logger.error("User object is null");
            return new ApiResponse(0, "User object is null", null);
        }
        
        if (userRepo.existsByUserName(user.getUserName())) {
            logger.warn("User with username {} already exists", user.getUserName());
            return new ApiResponse(0, "User already exists", null);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User u = userRepo.save(user);
        logger.info("User {} created successfully", user.getUserName());
        return new ApiResponse(1, "Success", u); 
    }

    @Override
    public ApiResponse deleteUser(Long id) {
        if (id == null) {
            logger.error("User ID is null");
            return new ApiResponse(0, "User ID is null", null);
        }

        if (!userRepo.existsById(id)) {
            logger.warn("User with ID {} does not exist", id);
            return new ApiResponse(0, "Data does not exist", null);
        }

        userRepo.deleteById(id);
        logger.info("User with ID {} deleted successfully", id);
        return new ApiResponse(1, "Successfully deleted user", null);
    }

    @Override
    public ApiResponse authenticate(String userName, String password, HttpServletResponse httpServletResponse) {
    	
       
        if (userName == null || password == null) {
            logger.error("Username or password is null");
            return new ApiResponse(0, "Username or password is null", null);
        }

        User storedUser = userRepo.findByUserName(userName).get();

        if (storedUser != null && passwordEncoder.matches(password, storedUser.getPassword())) {
            logger.info("User {} authenticated successfully", userName);
            if(httpServletResponse != null)
            httpServletResponse.setHeader("Autorization", generateToken());
            return new ApiResponse(1, "Successfully logged in", null); 
        } else {
            logger.warn("Invalid login attempt for username {}", userName);
            return new ApiResponse(0, "Invalid username or password", null);
        }
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    String generateToken(){
    	String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        System.err.println(saltStr);
        saltStr =saltStr+"|"+LocalDateTime.now().plusMinutes(15).getNano()+"";
        saltStr = Base64.getEncoder().encodeToString(saltStr.getBytes());
        return saltStr;
    }
    
    
    private void decodeToken(String saltStr){
    	saltStr = Base64.getDecoder().decode(saltStr.getBytes()).toString();
    	String expiryTime = saltStr.split("|")[1];
    	System.err.println(expiryTime);
    	
    }
    class userLoginResponse{
    	public String username;
    	//TOOD add other info
    	public String token;
    }
}
