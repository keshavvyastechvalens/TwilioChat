package com.chat.TwilioChat.services;
import com.chat.TwilioChat.requestdto.MessageDto;
import com.chat.TwilioChat.response.RestResponse;
import com.chat.TwilioChat.util.NoUserExistException;

public interface SoloChatService {

	RestResponse createConversation(long senderUserId, long receiverUserId) throws NoUserExistException;

	RestResponse sendMessage(MessageDto messageDto, long senderUserId);

	RestResponse fetchMessage(String channelSid, long senderUserId);

}
