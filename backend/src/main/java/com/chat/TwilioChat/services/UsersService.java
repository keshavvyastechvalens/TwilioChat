package com.chat.TwilioChat.services;

import com.chat.TwilioChat.requestdto.LoginDto;
import com.chat.TwilioChat.requestdto.RegisterDto;
import com.chat.TwilioChat.response.RestResponse;
import com.chat.TwilioChat.util.AlreadyExistException;

public interface UsersService {

	RestResponse registerUser(RegisterDto registerDto) throws AlreadyExistException;

	RestResponse login(LoginDto loginDto);

	RestResponse logout(String header);

}
