package com.chat.TwilioChat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chat.TwilioChat.model.SoloChat;

public interface SoloChatRepository extends JpaRepository<SoloChat, Long> {

    SoloChat findBySenderIdAndReceiverId(long id, long id2);


	SoloChat findBySenderIdAndConversationId(long senderUserId, String conversationId);

    SoloChat findBySenderIdOrReceiverIdAndSenderIdOrReceiverId(long id, long id2, long id3, long id4);


    SoloChat findByReceiverIdAndConversationId(long senderUserId, String conversationId);


	
}
