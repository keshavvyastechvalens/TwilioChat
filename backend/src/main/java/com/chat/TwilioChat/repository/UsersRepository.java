package com.chat.TwilioChat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chat.TwilioChat.model.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {

    Users findByUserNameIgnoreCase(String username);

    Users findByEmailIdIgnoreCase(String emailId);

}
