package com.chat.TwilioChat.servicesImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.chat.TwilioChat.model.SoloChat;
import com.chat.TwilioChat.model.Users;
import com.chat.TwilioChat.repository.SoloChatRepository;
import com.chat.TwilioChat.repository.UsersRepository;
import com.chat.TwilioChat.response.DataResponse;
import com.chat.TwilioChat.response.RestResponse;
import com.chat.TwilioChat.services.SoloChatService;
import com.twilio.Twilio;
import com.twilio.rest.conversations.v1.Conversation;
import com.twilio.rest.conversations.v1.conversation.Participant;

@Service
public class SoloChatServiceImpl implements SoloChatService {

	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	SoloChatRepository soloChatRepository;

	@Value("${ACCOUNT_SID}")
private String acc_sid;

	@Value("${AUTH_TOKEN}")
	private String auth_token;

	@Override
	public RestResponse createConversation(long senderUserId, long receiverUserId) {
		Twilio.init(acc_sid,auth_token);
		Optional<Users> senderCheck = usersRepository.findById(senderUserId);
		Optional<Users> receiverCheck = usersRepository.findById(receiverUserId);
		Users sender = senderCheck.get();
		
		Users receiver = receiverCheck.get();
		
		//here condition for alredy existing convo should be given
		
		String convoName = sender.getFirstName()+"-"+receiver.getFirstName();
		Conversation conversation = Conversation.creator().setFriendlyName(convoName).create();
		Participant senderParticipant = Participant.creator(conversation.getSid()).setIdentity(sender.getUserName())
				.create();
		Participant receiverParticipant = Participant.creator(conversation.getSid()).setIdentity(receiver.getUserName())
				.create();
			
		SoloChat soloChat = new SoloChat();
		
		soloChat.setSender(sender);
		
		soloChat.setReceiver(receiver);
		soloChat.setConversationId(conversation.getSid());
	
		soloChat.setSenderParticipantId(senderParticipant.getSid());
		soloChat.setReceiverParticipantId(receiverParticipant.getSid());
		SoloChat returnSoloChatObj = soloChatRepository.save(soloChat);
		
		return (new DataResponse(200,"CONVERSATION CREATED",returnSoloChatObj));
		
		
	}

}
