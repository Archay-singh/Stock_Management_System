package com.traffsys.stock.entity;


public class JwtResponse {

	private String userName;
	private String jwtToken;
	
	
	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public JwtResponse(String userName, String jwtToken) {
		super();
		this.userName = userName;
		this.jwtToken = jwtToken;
	}

	public JwtResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "JwtResponse [userName=" + userName + ", jwtToken=" + jwtToken + "]";
	}

	public static Object builder() {
		// TODO Auto-generated method stub
		return null;
	}
	

	
}
