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
		
		
		
		
//	    // Required for all types of tokens
//	    String twilioAccountSid = "ACddc8345ce6df6276d3884ba859bc03be";
//	    String twilioApiKey = "SK458852483cc389f8486513aa7ac83a6b";
//	    String twilioApiSecret = "RHLdbsAgMFWgkUPOT5PaPVEIRTHNKAmS";
//
//	    String serviceSid = "ISf25c634cb89a4141838ae151cf1d19bd";
//	    String identity = "prabhanshu05";
//
//	    ChatGrant grant = new ChatGrant();
//	    grant.setServiceSid(serviceSid);
//
//	    AccessToken token = new AccessToken.Builder(twilioAccountSid, twilioApiKey, twilioApiSecret)
//	        .identity(identity).grant(grant).build();
//
//	    System.out.println(token.toJwt());
		
		
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
