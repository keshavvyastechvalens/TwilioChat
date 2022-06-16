package com.chat.TwilioChat.returnDto;

import java.util.List;

import com.chat.TwilioChat.model.Users;

public class LoginReturnDto {
	
	
	private String name;
	private String userName;
	private String token;
	private List<Users> userList;
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
	public List<Users> getUserList() {
		return userList;
	}
	public void setUserList(List<Users> userList) {
		this.userList = userList;
	}
	@Override
	public String toString() {
		return "LoginReturnDto [name=" + name + ", userName=" + userName + ", token=" + token + ", userList=" + userList
				+ "]";
	}
	public LoginReturnDto(String name, String userName, String token, List<Users> userList) {
		super();
		this.name = name;
		this.userName = userName;
		this.token = token;
		this.userList = userList;
	}
	public LoginReturnDto() {
		super();
	}
	
}

