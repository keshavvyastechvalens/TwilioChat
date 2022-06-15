package com.chat.TwilioChat.servicesImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.chat.TwilioChat.model.Token;
import com.chat.TwilioChat.model.Users;
import com.chat.TwilioChat.repository.TokenRepository;
import com.chat.TwilioChat.repository.UsersRepository;
import com.chat.TwilioChat.requestdto.LoginDto;
import com.chat.TwilioChat.requestdto.RegisterDto;
import com.chat.TwilioChat.response.DataResponse;
import com.chat.TwilioChat.response.RestResponse;
import com.chat.TwilioChat.response.StatusCode;
import com.chat.TwilioChat.returnDto.LoginReturnDto;
import com.chat.TwilioChat.services.UsersService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UsersServiceImpl implements UsersService {
	
	
	@Autowired
	TokenRepository tokenRepository;
	
	
	@Autowired
	UsersRepository userRepository ;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public RestResponse registerUser(RegisterDto registerDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestResponse login(LoginDto loginDto) {
		 Users user = null; 
		 
		 	String[ ] requestArray = {"userName","password"};
		 	
	
		 	
		 	user = userRepository.findByUserNameIgnoreCase(loginDto.getUserName());
	        if(user==null)
	        {
	        	user = userRepository.findByEmailIgnoreCase(loginDto.getUserName());
	        	if(user==null)
	        			return new DataResponse(StatusCode.INVALID_CREDENTIALS_STATUS, "USER_NOT_FOUND", null);
	        }
	        if(!(bCryptPasswordEncoder.matches(loginDto.getPassword(), user.getPassword())))
	        {
	            return new DataResponse(StatusCode.INVALID_CREDENTIALS_STATUS, "INCORRECT_PASSWORD", null);
	        }
	        Token token = new Token();   
	        token.setUserToken(this.getToken(user.getId()));
	        tokenRepository.save(token);
	        LoginReturnDto loginReturnDto = new LoginReturnDto();
	        loginReturnDto.setName(user.getUserName());
	        loginReturnDto.setToken(token.getUserToken());
	        return new DataResponse(StatusCode.SUCCESS, "LOGIN_SUCESSFULLY", loginReturnDto);
	}

	
	private String getToken(long id) {
		   
		String userId = ""+id;
		String token = Jwts.builder()
		                .setSubject(userId)
		                .setExpiration(new Date(System.currentTimeMillis() + 864_000_000))
		                .signWith(SignatureAlgorithm.HS512, "MustBeUniqueEverwhere")
		                .compact();
		        return token;
	}
	
	@Override
	public RestResponse logout(String header) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
