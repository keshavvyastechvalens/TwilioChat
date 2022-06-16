package com.chat.TwilioChat.filter;
import java.io.IOException;
import java.util.Optional;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chat.TwilioChat.model.Users;
import com.chat.TwilioChat.repository.UsersRepository;

import io.jsonwebtoken.Jwts;

@Component
public class CROSfilter implements Filter{


	
	@Autowired
	UsersRepository usersRepository;
	
	
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS");
		response.setHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept, Authorization, type");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.addIntHeader("Access-Control-Max-Age", 180);	
		System.out.println("--------------------------");

		try 
		{
			HttpServletRequest httpRequest = (HttpServletRequest)req;
			if(httpRequest.getHeader("Authorization")==null)
			{
				System.out.println("--------------------------");
				chain.doFilter(req, res);
			}
			else
			{
				String id = Jwts.parser().setSigningKey("SecretKeyToGenJWTsSecretKeyToGenJWTsSecretKeyToGenJWTs").parseClaimsJws(httpRequest.getHeader("Authorization")).getBody().getSubject();				
				long userId = Long.parseLong(id);
				Optional<Users> u = usersRepository.findById(userId);
			
				System.out.println(id+"+++++++++++++++++++++++++++++++++++++++++++++++++++");
				if(!u.isPresent())
				{
					throw new IOException("USER NOT FOUND");
				}
				req.setAttribute("id", id);
				chain.doFilter(req, res);
			}	
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	@Override
	public void destroy() {
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

}


