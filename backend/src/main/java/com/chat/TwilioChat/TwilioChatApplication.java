package com.chat.TwilioChat;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.twilio.jwt.accesstoken.AccessToken;
import com.twilio.jwt.accesstoken.ChatGrant;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class})
public class TwilioChatApplication {
			 
	public static void main(String[] args) {
		SpringApplication.run(TwilioChatApplication.class, args);
		
	}
	
	
	
	

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
