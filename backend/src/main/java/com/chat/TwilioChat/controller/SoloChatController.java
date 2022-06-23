package com.chat.TwilioChat.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chat.TwilioChat.model.Users;
import com.chat.TwilioChat.repository.UsersRepository;
import com.chat.TwilioChat.requestdto.MessageDto;
import com.chat.TwilioChat.response.DataResponse;
import com.chat.TwilioChat.response.RestResponse;
import com.chat.TwilioChat.services.SoloChatService;
import com.chat.TwilioChat.util.NoUserExistException;

@RestController
@RequestMapping("/chat")
public class SoloChatController {
	@Autowired
	SoloChatService soloChatService;
	
	@Autowired
	UsersRepository usersRepository;
	//this will create a new conversation
	@GetMapping("/createConversation")
	public RestResponse createConversation(@RequestParam("receiverUserId") long receiverUserId, HttpServletRequest req)
	{
		try {
			long senderUserId = Long.parseLong(req.getAttribute("id").toString());
					return soloChatService.createConversation(senderUserId,receiverUserId);
		} catch (NoUserExistException e) {
			return new DataResponse(404, e.getMessage(), null);
		}catch (Exception e) {
			return new DataResponse(500, e.getMessage(), null);
		}
	}
	
	//responsible to send a message to a conversation
	@PostMapping("/sendMessage")
	public RestResponse sendMessage(@Valid @RequestBody MessageDto messageDto, BindingResult result,HttpServletRequest req)
	{
		try {
			if (result.getAllErrors() != null && !result.getAllErrors().isEmpty()){
                
				return new DataResponse(400, result.getAllErrors().get(0).getDefaultMessage(), null);
			}
			long senderUserId = Long.parseLong(req.getAttribute("id").toString());
			
			
			return soloChatService.sendMessage(messageDto,senderUserId);
			
//			return soloChatService.sendMessage(messageDto,senderUserId);
		
		}
		catch(Exception e)
		{
			return new DataResponse(500, e.getMessage(), null);
		}
	}
	
	//MBffada358732f4183a3ee2e9afdea53f9
	//MBffada358732f4183a3ee2e9afdea53f9
	
	
	//fetching message from the conversation
	@GetMapping("/fetchMessage")
	public RestResponse fetchMessage(@RequestParam("conversationId") String conversationId,HttpServletRequest req)
	{
		try {
			long senderUserId = Long.parseLong(req.getAttribute("id").toString());
		return soloChatService.fetchMessage(conversationId,senderUserId);
		} catch (Exception e) {
			return new DataResponse(500, e.getMessage(), null);
		}
	}
	
	//getting the token
	@GetMapping("/token")
	public String getToken(HttpServletRequest req)
	{
		long userId = Long.parseLong(req.getAttribute("id").toString());
		Optional<Users> user = usersRepository.findById(userId);
		
		
		
		String identity = user.get().getUserName();
		if(identity==null || identity.isEmpty())
		{
			System.out.println("Invalid token");
		}
		
		try
		{
			return soloChatService.getAccessToken(identity);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		return null;

	}
}
