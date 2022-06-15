package com.chat.TwilioChat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chat.TwilioChat.model.Token;


@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

}
