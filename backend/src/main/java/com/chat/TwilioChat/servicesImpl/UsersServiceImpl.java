package com.chat.TwilioChat.servicesImpl;

import org.springframework.stereotype.Service;

import com.chat.TwilioChat.requestdto.LoginDto;
import com.chat.TwilioChat.requestdto.RegisterDto;
import com.chat.TwilioChat.response.RestResponse;
import com.chat.TwilioChat.services.UsersService;
@Service
public class UsersServiceImpl implements UsersService {

	@Override
	public RestResponse registerUser(RegisterDto registerDto) {
		

		return null;
	}

	@Override
	public RestResponse login(LoginDto loginDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestResponse logout(String header) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
