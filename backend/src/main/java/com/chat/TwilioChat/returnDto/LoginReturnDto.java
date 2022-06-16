package com.chat.TwilioChat.returnDto;

public class LoginReturnDto {
	
	
	private String name;
	private String userName;
	private String token;
	
	
	public LoginReturnDto() {
		super();
	}
	public LoginReturnDto(String name, String userName, String token) {
		super();
		this.name = name;
		this.userName = userName;
		this.token = token;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String toString() {
		return "LoginReturnDto [name=" + name + ", userName=" + userName + ", token=" + token + "]";
	}
	
	

	
	

}

