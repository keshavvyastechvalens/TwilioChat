package com.chat.TwilioChat.services;

import com.chat.TwilioChat.response.RestResponse;

public interface SoloChatService {

	RestResponse createConversation(long senderUserId, long receiverUserId);

}
