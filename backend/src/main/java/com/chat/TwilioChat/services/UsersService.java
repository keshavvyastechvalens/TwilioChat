package com.chat.TwilioChat.services;

import com.chat.TwilioChat.requestdto.LoginDto;
import com.chat.TwilioChat.requestdto.RegisterDto;
import com.chat.TwilioChat.response.RestResponse;

public interface UsersService {

	RestResponse registerUser(RegisterDto registerDto);

	RestResponse login(LoginDto loginDto);

	RestResponse logout(String header);

}
