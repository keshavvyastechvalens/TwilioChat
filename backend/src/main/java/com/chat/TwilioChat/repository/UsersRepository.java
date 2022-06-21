package com.chat.TwilioChat.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chat.TwilioChat.model.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
	
	public Users findByEmailIdIgnoreCase(String emailId);
	
	public Users findByUserNameIgnoreCase(String userName);
	
//	public Users findById(long userID);
	
	//public Optional<Users> findById(long userID);
}
