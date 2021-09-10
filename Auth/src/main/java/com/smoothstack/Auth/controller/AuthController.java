package com.smoothstack.Auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AuthController {

	// user has to manually accept a self-signed certificate before they can attempt
	// to log in
	@GetMapping("/login")
	public ResponseEntity<String> readTest() {
		return new ResponseEntity<>("SSL certificate accepted. You can now attempt to log in.", HttpStatus.OK);
	}

}
