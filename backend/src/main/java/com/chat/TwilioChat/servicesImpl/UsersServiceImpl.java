package com.chat.TwilioChat.servicesImpl;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
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
import com.chat.TwilioChat.util.AlreadyExistException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UsersServiceImpl implements UsersService {
	
	
	@Autowired
	TokenRepository tokenRepository;

	@Autowired
    ModelMapper modelMapper;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

@Autowired
UsersRepository usersRepository;




	@Override
	public RestResponse registerUser(RegisterDto registerDto) throws AlreadyExistException{
            Users u2 = null;
            u2 = this.findByUserName(registerDto.getUserName());
            if (u2 != null) {
                throw new AlreadyExistException("UserName " + registerDto.getUserName() + " already Exist");
            }

			u2 = this.findByEmailId(registerDto.getEmailId());
            if (u2 != null) {
                throw new AlreadyExistException("User Email " + registerDto.getEmailId() + " already Exist");
            }

            Users u = modelMapper.map(registerDto, Users.class);

            u.setPassword(bCryptPasswordEncoder.encode(registerDto.getPassword()));
            usersRepository.save(u);
            return new DataResponse(200, "User Created Successfully !", null);
	}

	@Override
	public RestResponse login(LoginDto loginDto) {
		 Users user = null; 
		 
		 	
		 	user = usersRepository.findByUserNameIgnoreCase(loginDto.getUserName());
	        if(user==null)
	        {
	        	user = usersRepository.findByEmailIdIgnoreCase(loginDto.getUserName());
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
	        loginReturnDto.setName(user.getFirstName()+" "+user.getLastName());
	        loginReturnDto.setUserName(user.getUserName());
	        loginReturnDto.setToken(token.getUserToken());
	        
	        List<Users> usersList = usersRepository.findAll();
	        
	        usersList.remove(user);
	        loginReturnDto.setUserList(usersList);
	        
	        return new DataResponse(StatusCode.SUCCESS, "LOGIN_SUCESSFULLY", loginReturnDto);
	}

	
	private String getToken(long id) {
		   
		String userId = ""+id;
		return (Jwts.builder()
		.setSubject(userId)
		.setExpiration(new Date(System.currentTimeMillis() + 86400000))
		.signWith(SignatureAlgorithm.HS256, "SecretKeyToGenJWTsSecretKeyToGenJWTsSecretKeyToGenJWTs")
		.compact());
		        
	}
	
	@Override
	public RestResponse logout(String userToken) {
		Token token = tokenRepository.findByUserToken(userToken); 
		if(token!=null)
		{	
			tokenRepository.delete(token);
			return new DataResponse(StatusCode.SUCCESS, "you are logge out", null);
		}
		else
		{
			return new DataResponse(StatusCode.ERROR,"Invalid token", null);
		}
		
	}
	
	public Users findByUserName(String username) {
        try {

            return usersRepository.findByUserNameIgnoreCase(username);

        } catch (Exception e) {
            return null;
        }
    }
	public Users findByEmailId(String emailId) {
        try {

            return usersRepository.findByEmailIdIgnoreCase(emailId);

        } catch (Exception e) {
            return null;
        }
    }
}
