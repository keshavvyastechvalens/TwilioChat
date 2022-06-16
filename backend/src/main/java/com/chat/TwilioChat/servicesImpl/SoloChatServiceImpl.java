package com.chat.TwilioChat.servicesImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat.TwilioChat.model.Users;
import com.chat.TwilioChat.repository.UsersRepository;
import com.chat.TwilioChat.response.RestResponse;
import com.chat.TwilioChat.services.SoloChatService;

@Service
public class SoloChatServiceImpl implements SoloChatService {

	@Autowired
	UsersRepository usersRepository;

	@Override
	public RestResponse createConversation(long senderUserId, long receiverUserId) {

		Optional<Users> senderCheck = usersRepository.findById(senderUserId);
		Optional<Users> receiverCheck = usersRepository.findById(senderUserId);

//		Conversation conversation = Conversation.creator().setFriendlyName("My First Conversation Room 2").create();

		
		
		return null;
	}

}
