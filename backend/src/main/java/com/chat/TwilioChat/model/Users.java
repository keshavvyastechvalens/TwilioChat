package com.chat.TwilioChat.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
@Entity
public class Users {
	@Id
	@GeneratedValue(generator = "user_generator")
	@SequenceGenerator(
			name = "user_generator",
			sequenceName = "user_sequence",
			initialValue = 100000
			)
	private long id;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String contactNo;
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
	@Override
	public String toString() {
		return "Users [id=" + id + ", userName=" + userName + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", contactNo=" + contactNo + ", emailId=" + emailId + "]";
	}
	public Users(long id, String userName, String password, String firstName, String lastName, String contactNo,
			String emailId) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactNo = contactNo;
		this.emailId = emailId;
	}
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
