package com.chat.TwilioChat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chat.TwilioChat.model.SoloChat;

public interface SoloChatRepository extends JpaRepository<SoloChat, Long> {
	
}
