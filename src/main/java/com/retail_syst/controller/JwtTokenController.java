package com.retail_syst.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.retail_syst.helper.JwtUtilRetai;
import com.retail_syst.model.JwtRequest;
import com.retail_syst.model.JwtResponse;
import com.retail_syst.service.CustomeUserDetailsService;

@RestController 
public class JwtTokenController {

	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomeUserDetailsService customeUserDetailsService; 
	
	@Autowired
	private JwtUtilRetai jwtRetail;
	
	@RequestMapping(value = "/generate-token",method=RequestMethod.POST)
	ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtrequest) throws Exception{
		System.out.println("Inside JwtRequest() :: ");
		System.out.println("jwtrequest object :: "+jwtrequest);
		
		try {

            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtrequest.getUserName(), jwtrequest.getPassword()));


        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            throw new Exception("User Not Found :;");
        }catch (BadCredentialsException e)
        {
            e.printStackTrace();
            throw new Exception("Bad Credentials");
        }


 
        UserDetails userDetails = this.customeUserDetailsService.loadUserByUsername(jwtrequest.getUserName());

        String token = this.jwtRetail.generateToken(userDetails);
        System.out.println("JWT " + token);

        //{"token":"value"}


        return ResponseEntity.ok( new JwtResponse(token));
		
		
		
		
	}
}
