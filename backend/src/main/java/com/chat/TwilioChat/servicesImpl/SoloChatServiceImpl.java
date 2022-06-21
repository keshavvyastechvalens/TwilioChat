package com.chat.TwilioChat.servicesImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.StubValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.chat.TwilioChat.model.SoloChat;
import com.chat.TwilioChat.model.Users;
import com.chat.TwilioChat.repository.SoloChatRepository;
import com.chat.TwilioChat.repository.UsersRepository;
import com.chat.TwilioChat.requestdto.MessageDto;
import com.chat.TwilioChat.response.DataResponse;
import com.chat.TwilioChat.response.RestResponse;
import com.chat.TwilioChat.services.SoloChatService;
import com.chat.TwilioChat.util.NoUserExistException;
import com.ctc.wstx.shaded.msv_core.datatype.xsd.Comparator;
import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.jwt.accesstoken.AccessToken;
import com.twilio.jwt.accesstoken.ChatGrant;
import com.twilio.rest.conversations.v1.Conversation;
import com.twilio.rest.conversations.v1.conversation.Message;
import com.twilio.rest.conversations.v1.conversation.Participant;

@Service
public class SoloChatServiceImpl implements SoloChatService{

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	SoloChatRepository soloChatRepository;

	@Value("${ACCOUNT_SID}")
	private String acc_sid;

	@Value("${TWILIO_API_KET}")
	private String twilioApiKey;
	
	@Value("${TWILIO_API_SECRET}")
	private String twilioApiSecret;
	
	@Value("${SERVICE_SID}")
	private String serviceSid;

	@Value("${AUTH_TOKEN}")
	private String auth_token;

	@Override
	public RestResponse createConversation(long senderUserId, long receiverUserId) throws NoUserExistException, CloneNotSupportedException {
		Twilio.init(acc_sid, auth_token);
		Optional<Users> senderCheck = usersRepository.findById(senderUserId);
		Optional<Users> receiverCheck = usersRepository.findById(receiverUserId);

		if (!senderCheck.isPresent()) {
			throw new NoUserExistException("Sender Not Found");
		}

		if (!receiverCheck.isPresent()) {
			throw new NoUserExistException("Receiver Not Found");
		}

		Users sender = senderCheck.get();

		Users receiver = receiverCheck.get();
		// here condition for alredy existing convo should be given
		
SoloChat conversationExistCheck = soloChatRepository.findBySenderIdAndReceiverId(sender.getId(),receiver.getId());
SoloChat conversationExistCheckAgain = soloChatRepository.findBySenderIdAndReceiverId(receiver.getId(), sender.getId());

		if (conversationExistCheck != null) {
	 return new DataResponse(409, "CONVERSATION ALREADY EXIST !", conversationExistCheck);
	 } else if (conversationExistCheckAgain != null) {
		SoloChat soloChat1 = (SoloChat)conversationExistCheckAgain.clone();
		System.out.println(soloChat1);
		soloChat1.setReceiver(conversationExistCheckAgain.getSender());
		soloChat1.setSender(conversationExistCheckAgain.getReceiver());
		 return new DataResponse(409, "CONVERSATION ALREADY EXIST !", soloChat1);
		 } else{

			String convoName = sender.getFirstName() + "-" + receiver.getFirstName();
			Conversation conversation = null;
			try {
				conversation = Conversation.creator().setFriendlyName(convoName).create();

			} catch (Exception e) {
				System.out.println(e);
				e.printStackTrace();
			}

			Participant senderParticipant = Participant.creator(conversation.getSid()).setIdentity(sender.getUserName())
					.create();
			Participant receiverParticipant = Participant.creator(conversation.getSid())
					.setIdentity(receiver.getUserName()).create();

			SoloChat soloChat = new SoloChat();

			soloChat.setSender(sender);

			soloChat.setReceiver(receiver);
			soloChat.setConversationId(conversation.getSid());

			soloChat.setSenderParticipantId(senderParticipant.getSid());
			soloChat.setReceiverParticipantId(receiverParticipant.getSid());
			SoloChat returnSoloChatObj = soloChatRepository.save(soloChat);

			return (new DataResponse(200, "CONVERSATION CREATED", returnSoloChatObj));

		}
	}

	@Override
	public RestResponse sendMessage(MessageDto messageDto, long senderUserId) {
		Twilio.init(acc_sid, auth_token);
		Optional<Users> senderCheck = usersRepository.findById(senderUserId);
		System.out.println(senderCheck.get().getUserName());
		Message message = Message.creator(messageDto.getConversationId()).setAuthor(senderCheck.get().getUserName()).setBody(messageDto.getMessageContant())
				.create();
		return new DataResponse(1000, "yoyo",message);
	}

	@Override
	public RestResponse fetchMessage(String conversationId, long senderUserId) {
		Twilio.init(acc_sid, auth_token);
		SoloChat soloChat = soloChatRepository.findBySenderIdAndConversationId(senderUserId,conversationId);
		if(soloChat==null)
		{
			 soloChat = soloChatRepository.findByReceiverIdAndConversationId(senderUserId,conversationId);
			if(soloChat==null)
		{
			return new DataResponse(400,"MISMATCH IN CONVERSATION AND SENDER",null);
		}
	}
		ResourceSet<Message> messages = Message.reader(conversationId).setOrder(Message.OrderType.DESC)
		.limit(10).read();
		ArrayList<Message> listMessages = new ArrayList<>();
		for(Message record : messages) {
			listMessages.add(record);
        }

		Message message = Message.fetcher(
			conversationId,
			"IM79cf7adad80549afac440ca6b4e5894c")
		.fetch();

	System.out.println(message.getDelivery());

		return new DataResponse(200, "All List Returned", listMessages);
	}

	@Override
	public String getAccessToken(String identity) {
		
	    ChatGrant grant = new ChatGrant();
	    grant.setServiceSid(serviceSid);

	    AccessToken token = new AccessToken.Builder(acc_sid, twilioApiKey, twilioApiSecret)
	        .identity(identity).grant(grant).build();

	    System.out.println(token.toJwt());
		
		return token.toJwt();
	}
	
	
	

}
