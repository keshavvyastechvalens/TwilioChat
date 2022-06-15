package com.chat.TwilioChat.servicesImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.chat.TwilioChat.model.Users;
import com.chat.TwilioChat.repository.UsersRepository;
import com.chat.TwilioChat.requestdto.LoginDto;
import com.chat.TwilioChat.requestdto.RegisterDto;
import com.chat.TwilioChat.response.DataResponse;
import com.chat.TwilioChat.response.RestResponse;
import com.chat.TwilioChat.services.UsersService;
import com.chat.TwilioChat.util.AlreadyExistException;
@Service
public class UsersServiceImpl implements UsersService {
	@Autowired
    ModelMapper modelMapper;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
@Autowired
UsersRepository usersRepository;

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
		// TODO Auto-generated method stub
		return null;
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
