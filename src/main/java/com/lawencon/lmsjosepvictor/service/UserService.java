package com.lawencon.lmsjosepvictor.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.lawencon.lmsjosepvictor.dto.InsertResDto;
import com.lawencon.lmsjosepvictor.dto.UpdatePhotoResDto;
import com.lawencon.lmsjosepvictor.dto.UpdateResDto;
import com.lawencon.lmsjosepvictor.dto.login.LoginReqDto;
import com.lawencon.lmsjosepvictor.dto.login.LoginResDto;
import com.lawencon.lmsjosepvictor.dto.user.ChangePasswordReqDto;
import com.lawencon.lmsjosepvictor.dto.user.ProfilePhotoUpdateReqDto;
import com.lawencon.lmsjosepvictor.dto.user.ProfileResDto;
import com.lawencon.lmsjosepvictor.dto.user.ProfileUpdateReqDto;
import com.lawencon.lmsjosepvictor.dto.user.UserInsertReqDto;
import com.lawencon.lmsjosepvictor.dto.user.UsersResDto;

public interface UserService extends UserDetailsService{

	List<UsersResDto> getAllUsers(String roleCode);

	UpdatePhotoResDto updatePhoto(ProfilePhotoUpdateReqDto data);

	UpdateResDto update(ProfileUpdateReqDto data);

	InsertResDto insert(UserInsertReqDto data);

	LoginResDto login(LoginReqDto data);

	ProfileResDto getUserDetail();

	UpdateResDto changePassword(ChangePasswordReqDto data);
}
