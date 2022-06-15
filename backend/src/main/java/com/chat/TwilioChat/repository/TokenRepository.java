package com.chat.TwilioChat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.chat.TwilioChat.model.Token;



public interface TokenRepository extends JpaRepository<Token, Long> {

}
