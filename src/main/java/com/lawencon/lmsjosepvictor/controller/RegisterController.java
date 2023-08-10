package com.lawencon.lmsjosepvictor.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lmsjosepvictor.dto.InsertResDto;
import com.lawencon.lmsjosepvictor.dto.user.UserInsertReqDto;
import com.lawencon.lmsjosepvictor.service.UserService;

@RestController
@RequestMapping("register")
public class RegisterController {

	private final UserService userService;

	RegisterController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping
	public ResponseEntity<InsertResDto> insert(@RequestBody UserInsertReqDto data){
		final InsertResDto response = userService.insert(data);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
