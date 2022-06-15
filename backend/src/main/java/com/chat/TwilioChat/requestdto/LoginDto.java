package com.chat.TwilioChat.requestdto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class LoginDto {
	@NotNull(message = "username can not be null")
	@NotEmpty(message = "username can not be empty")
	String userName;
	@NotEmpty(message = "password can not be null")
	@NotNull(message = "password can not be empty")
	String password;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LoginDto(
			@NotNull(message = "username can not be null") @NotEmpty(message = "username can not be empty") String userName,
			@NotEmpty(message = "password can not be null") @NotNull(message = "password can not be empty") String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	public LoginDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "LoginDto [userName=" + userName + ", password=" + password + "]";
	}
	
}
