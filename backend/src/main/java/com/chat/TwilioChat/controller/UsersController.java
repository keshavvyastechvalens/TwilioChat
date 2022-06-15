package com.chat.TwilioChat.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chat.TwilioChat.requestdto.LoginDto;
import com.chat.TwilioChat.requestdto.RegisterDto;
import com.chat.TwilioChat.response.DataResponse;
import com.chat.TwilioChat.response.RestResponse;
import com.chat.TwilioChat.services.UsersService;



@RestController
@RequestMapping("")
public class UsersController {
	
	@Autowired
	UsersService usersService;
	
	
	
	@PostMapping("/registeruser")
	public RestResponse registerUser(@RequestBody RegisterDto registerDto) {
		try {
			return usersService.registerUser(registerDto);			
		}
		catch(Exception e)
		{
			return new DataResponse(500,e.getMessage(),null);
		}
	}

	@PostMapping("/login")
	public RestResponse login(@RequestBody LoginDto loginDto) {
		
		try {
			return usersService.login(loginDto);	
		}
		catch(Exception e)
		{
			return new DataResponse(500,e.getMessage(),null);
		}
	}

	@DeleteMapping("/logout")
	public RestResponse logout(HttpServletRequest req) {
		
		try {
			return usersService.logout(req.getHeader("Authorization"));			
		}
		catch(Exception e)
		{
			return new DataResponse(500,e.getMessage(),null);
		}
	}
}
