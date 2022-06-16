package com.chat.TwilioChat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chat.TwilioChat.response.RestResponse;
import com.chat.TwilioChat.services.SoloChatService;

@RestController
@RequestMapping("/chat")
public class SoloChatController {
	
	SoloChatService soloChatService;
	
	
	
	
	@GetMapping("/createConversation")
	public RestResponse createConversation(@RequestParam("receiverUserId") long receiverUserId, HttpServletRequest req)
	{
		try {
			long senderUserId = Long.parseLong(req.getAttribute("id").toString());
			return soloChatService.createConversation(senderUserId,receiverUserId);

		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return null;
	}
}
