package com.chat.TwilioChat.returnDto;

public class LoginReturnDto {
	
	
	private String name;
	private String token;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public LoginReturnDto(String name, String token) {
		super();
		this.name = name;
		this.token = token;
	}

	public LoginReturnDto() {
		super();
	}
	@Override
	public String toString() {
		return "LoginReturnDto [name=" + name + ", token=" + token + "]";
	}
	
	

}

