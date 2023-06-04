package com.test.demo.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.test.demo.service.UserService;
import com.test.demo.handler.*;
import com.test.demo.payload.response.MessageResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {
	
	private UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	

  @GetMapping
  public ResponseEntity<?> userAccess() {
	  ResponseSuccessHandler res = new ResponseSuccessHandler();
	  res.setBody(userService.getAllUsers());
	  return ResponseEntity.ok(res);
  }

}
