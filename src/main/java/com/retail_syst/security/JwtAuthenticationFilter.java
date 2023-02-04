package com.retail_syst.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.retail_syst.helper.JwtUtilRetai;
import com.retail_syst.service.CustomeUserDetailsService;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtilRetai jwtutilretail;
	
	@Autowired
	private CustomeUserDetailsService customeuserdetailsservice;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String request_Token_Header = request.getHeader("Authorization");
		String username="null";
		String jwt_Token_Header="null";
		
		if(request_Token_Header!=null && request_Token_Header.startsWith("Bearer ")) {
			
			jwt_Token_Header=request_Token_Header.substring(7);
			
			try {
				
				username=this.jwtutilretail.getUsernameFromToken(jwt_Token_Header);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
	}

}
