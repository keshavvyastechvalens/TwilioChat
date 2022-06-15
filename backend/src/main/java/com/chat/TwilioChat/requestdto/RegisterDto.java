package com.chat.TwilioChat.requestdto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RegisterDto {
	private long id;
	@NotNull(message = "USERNAME SHOULD NOT BE NULL")
	@NotEmpty(message = "USERNAME SHOULD NOT BE EMPTY")
	private String userName;
	@NotNull(message = "USERPASSWORD SHOULD NOT BE NULL")
	@NotEmpty(message = "USERPASSWORD SHOULD NOT BE EMPTY")
	private String password;
	@NotNull(message = "FIRSTNAME SHOULD NOT BE NULL")
	@NotEmpty(message = "FIRST SHOULD NOT BE EMPTY")
	private String firstName;
	@NotNull(message = "LASTNAME SHOULD NOT BE NULL")
	@NotEmpty(message = "LASTNAME SHOULD NOT BE EMPTY")
	private String lastName;
	@NotNull(message = "CONTACT SHOULD NOT BE NULL")
	@NotEmpty(message = "CONTACT SHOULD NOT BE EMPTY")
	private String contactNo;
	@NotNull(message = "Mail SHOLUD NOT BE NULL")
	@NotEmpty(message = "EMAIL SHOULD NOT BE EMPTY")
	@Email(message = "EMAIL SHOULD BE VALID")
	private String emailId;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public RegisterDto(long id,
			@NotNull(message = "USERNAME SHOULD NOT BE NULL") @NotEmpty(message = "USERNAME SHOULD NOT BE EMPTY") String userName,
			@NotNull(message = "USERPASSWORD SHOULD NOT BE NULL") @NotEmpty(message = "USERPASSWORD SHOULD NOT BE EMPTY") String password,
			@NotNull(message = "FIRSTNAME SHOULD NOT BE NULL") @NotEmpty(message = "FIRST SHOULD NOT BE EMPTY") String firstName,
			@NotNull(message = "LASTNAME SHOULD NOT BE NULL") @NotEmpty(message = "LASTNAME SHOULD NOT BE EMPTY") String lastName,
			@NotNull(message = "CONTACT SHOULD NOT BE NULL") @NotEmpty(message = "CONTACT SHOULD NOT BE EMPTY") String contactNo,
			@NotNull(message = "Mail SHOLUD NOT BE NULL") @NotEmpty(message = "EMAIL SHOULD NOT BE EMPTY") @Email(message = "EMAIL SHOULD BE VALID") String emailId) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactNo = contactNo;
		this.emailId = emailId;
	}
	public RegisterDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "RegisterDto [id=" + id + ", userName=" + userName + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + ", contactNo=" + contactNo + ", emailId=" + emailId + "]";
	}
	
	
	
	
	
}
