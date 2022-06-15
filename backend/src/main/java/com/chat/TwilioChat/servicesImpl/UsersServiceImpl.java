package com.chat.TwilioChat.servicesImpl;

<<<<<<< HEAD
import java.util.Date;

=======
import org.modelmapper.ModelMapper;
>>>>>>> c1acb30e4512bd727109b20ca333516b52f615cd
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import com.chat.TwilioChat.model.Token;
import com.chat.TwilioChat.model.Users;
import com.chat.TwilioChat.repository.TokenRepository;
=======
import com.chat.TwilioChat.model.Users;
>>>>>>> c1acb30e4512bd727109b20ca333516b52f615cd
import com.chat.TwilioChat.repository.UsersRepository;
import com.chat.TwilioChat.requestdto.LoginDto;
import com.chat.TwilioChat.requestdto.RegisterDto;
import com.chat.TwilioChat.response.DataResponse;
import com.chat.TwilioChat.response.RestResponse;
import com.chat.TwilioChat.response.StatusCode;
import com.chat.TwilioChat.returnDto.LoginReturnDto;
import com.chat.TwilioChat.services.UsersService;
<<<<<<< HEAD

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
=======
import com.chat.TwilioChat.util.AlreadyExistException;
@Service
public class UsersServiceImpl implements UsersService {
	@Autowired
    ModelMapper modelMapper;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
@Autowired
UsersRepository usersRepository;
>>>>>>> c1acb30e4512bd727109b20ca333516b52f615cd

	@Override
	public RestResponse registerUser(RegisterDto registerDto) throws AlreadyExistException{
		try {
            Users u2 = null;
            u2 = this.findByUserName(registerDto.getUserName());
            if (u2 != null) {
                throw new AlreadyExistException("User Name" + registerDto.getUserName() + " already Exist");
            }

			u2 = this.findByEmailId(registerDto.getEmailId());
            if (u2 != null) {
                throw new AlreadyExistException("User Email" + registerDto.getEmailId() + " already Exist");
            }

            Users u = modelMapper.map(registerDto, Users.class);

            u.setPassword(bCryptPasswordEncoder.encode(registerDto.getPassword()));
            usersRepository.save(u);
            return new DataResponse(200, "User Created Successfully !", null);

        } catch (Exception e) {
            return new DataResponse(500, "Server Error", null);
        }
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
