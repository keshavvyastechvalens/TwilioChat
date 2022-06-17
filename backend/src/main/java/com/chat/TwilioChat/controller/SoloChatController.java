package com.chat.TwilioChat.controller;

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
	
	
	@PostMapping("/sendMessage")
	public RestResponse sendMessage(@Valid @RequestBody MessageDto messageDto, BindingResult result,HttpServletRequest req)
	{
		try {
			System.out.println(messageDto);
			if (result.getAllErrors() != null && !result.getAllErrors().isEmpty()){
                
				return new DataResponse(400, result.getAllErrors().get(0).getDefaultMessage(), null);
			}
			long senderUserId = Long.parseLong(req.getAttribute("id").toString());
			return soloChatService.sendMessage(messageDto,senderUserId);
		
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return null;
	}
	
	//MBffada358732f4183a3ee2e9afdea53f9
	//MBffada358732f4183a3ee2e9afdea53f9
	
	
	
	@GetMapping("/fetchMessage")
	public RestResponse fetchMessage(@RequestParam("channelSid") String channelSid,HttpServletRequest req)
	{
		long senderUserId = Long.parseLong(req.getAttribute("id").toString());
		return soloChatService.fetchMessage(channelSid,senderUserId);
				
				
				
				
	}
}
