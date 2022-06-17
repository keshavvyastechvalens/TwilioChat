package com.chat.TwilioChat.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chat.TwilioChat.model.SoloChat;
import com.twilio.rest.conversations.v1.Conversation;

public interface SoloChatRepository extends JpaRepository<SoloChat, Long> {

    SoloChat findBySenderIdAndReceiverId(long id, long id2);


	SoloChat findBySenderIdAndConversationId(long senderUserId, String conversationId);


	
}
