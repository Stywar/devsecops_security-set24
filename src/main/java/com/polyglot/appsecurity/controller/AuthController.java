package com.polyglot.appsecurity.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.polyglot.appsecurity.dto.AuthRequest;
import com.polyglot.appsecurity.dto.AuthResponse;
import com.polyglot.appsecurity.jwt.JwtToken;
import com.polyglot.appsecurity.model.AccessModel;
import com.polyglot.appsecurity.service.AuthService;
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	AuthService authService;
	@Autowired
	private JwtToken jwtTokenCross;
	Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	@GetMapping
	public List<AccessModel> get() {
		
		return authService.getAcces();		

	}
	
	  @PostMapping()
	  public ResponseEntity<?> post(@RequestBody AuthRequest request) throws Exception {

	    logger.info("Post: UserName {} - Password {}", request.getUserName(), request.getPassword());

	    if (!authService.validatedCredentials(request.getUserName(), request.getPassword())) {
	      return new ResponseEntity<String>("INVALID_CREDENTIALS", HttpStatus.UNAUTHORIZED);
	    }
	    AuthResponse response = new AuthResponse(jwtTokenCross.generateToken(request), request.getUserName(), "1d");
	   
	    return ResponseEntity.ok(response);
	  }
	
}
