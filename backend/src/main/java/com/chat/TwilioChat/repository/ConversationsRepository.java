package com.chat.TwilioChat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chat.TwilioChat.model.Conversations;

public interface ConversationsRepository extends JpaRepository<Conversations, Long> {
	
}
