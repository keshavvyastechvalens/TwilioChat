package com.chat.TwilioChat.servicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.conversations.v1.Conversation;
import com.twilio.rest.conversations.v1.conversation.Message;
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
	public RestResponse createConversation(long senderUserId, long receiverUserId) throws NoUserExistException {
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
		SoloChat conversationExistCheck = soloChatRepository.findBySenderIdAndReceiverId(sender.getId(),
				receiver.getId());

		if (conversationExistCheck != null) {
			return new DataResponse(409, "CONVERSATION ALREADY EXIST !", conversationExistCheck);
		} else {

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
		Message message = Message.creator(messageDto.getConversationId()).setAuthor(senderCheck.get().getUserName()).setBody(messageDto.getMessageContant())
				.create();

		return new DataResponse(1000, "yoyo", message);
	}

	@Override
	public RestResponse fetchMessage(String channelSid, long senderUserId) {
		Twilio.init(acc_sid, auth_token);
		ResourceSet<Message> messages = Message.reader(channelSid).limit(20).read();
		ArrayList<Message> listMessages = new ArrayList<>();
		for(Message record : messages) {
			listMessages.add(record);
            System.out.println(record);
        }
		return new DataResponse(200, "All List Returned", listMessages);
	}

}
