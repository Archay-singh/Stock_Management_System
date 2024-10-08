package com.traffsys.stock.Service;


import com.traffsys.stock.dto.ApiResponse;
import com.traffsys.stock.entity.User;

import jakarta.servlet.http.HttpServletResponse;

public interface UserService {

	ApiResponse createUser(User user);

	ApiResponse deleteUser(Long id);

	ApiResponse authenticate(String userName,String Password, HttpServletResponse httpServletResponse);

	
}
