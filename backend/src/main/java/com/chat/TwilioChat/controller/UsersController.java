package com.chat.TwilioChat.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.BindingResultUtils;
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
@RequestMapping("/chat")
public class UsersController {	
	@Autowired
	UsersService usersService;

	@PostMapping("/registeruser")
	public RestResponse registerUser(@Valid @RequestBody RegisterDto registerDto, BindingResult result) {
		System.out.println("---------------");
		try {
			if (result.getAllErrors() != null && !result.getAllErrors().isEmpty()){
                    
				return new DataResponse(400, result.getAllErrors().get(0).getDefaultMessage(), null);
			}
			return usersService.registerUser(registerDto);
		} catch (Exception e) {
			return new DataResponse(500, e.getMessage(), null);
		}
	}

	@PostMapping("/login")
	public RestResponse login(@RequestBody LoginDto loginDto) {

		try {
			return usersService.login(loginDto);
		} catch (Exception e) {
			return new DataResponse(500, e.getMessage(), null);
		}
	}

	@DeleteMapping("/logout")
	public RestResponse logout(HttpServletRequest req) {

		try {
			return usersService.logout(req.getHeader("Authorization"));
		} catch (Exception e) {
			return new DataResponse(500, e.getMessage(), null);
		}
	}
}
