package com.lawencon.lmsjosepvictor.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lmsjosepvictor.dto.InsertResDto;
import com.lawencon.lmsjosepvictor.dto.UpdatePhotoResDto;
import com.lawencon.lmsjosepvictor.dto.UpdateResDto;
import com.lawencon.lmsjosepvictor.dto.user.ChangePasswordReqDto;
import com.lawencon.lmsjosepvictor.dto.user.ProfilePhotoUpdateReqDto;
import com.lawencon.lmsjosepvictor.dto.user.ProfileResDto;
import com.lawencon.lmsjosepvictor.dto.user.ProfileUpdateReqDto;
import com.lawencon.lmsjosepvictor.dto.user.UserInsertReqDto;
import com.lawencon.lmsjosepvictor.dto.user.UsersResDto;
import com.lawencon.lmsjosepvictor.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {

	private final UserService userService;

	UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping
	private ResponseEntity<List<UsersResDto>> getAll(String roleCode){
		final List<UsersResDto> responses = userService.getAllUsers(roleCode);
		
		return new ResponseEntity<> (responses, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<InsertResDto> insert(@RequestBody UserInsertReqDto data){
		final InsertResDto response = userService.insert(data);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PutMapping
	private ResponseEntity<UpdateResDto> updateProfile(@RequestBody ProfileUpdateReqDto data){
		final UpdateResDto response = userService.update(data);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PatchMapping
	private ResponseEntity<UpdatePhotoResDto> updateProfilePhoto(@RequestBody ProfilePhotoUpdateReqDto data){
		final UpdatePhotoResDto response = userService.updatePhoto(data);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PatchMapping("/change-password")
	private ResponseEntity<UpdateResDto> changePassword(@RequestBody ChangePasswordReqDto data) {
		final UpdateResDto response = userService.changePassword(data);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/detail")
	public ResponseEntity<ProfileResDto> getProfile(){
		final ProfileResDto response = userService.getUserDetail();
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
