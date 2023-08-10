package com.lawencon.lmsjosepvictor.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lawencon.lmsjosepvictor.constant.RoleCode;
import com.lawencon.lmsjosepvictor.dao.FileDao;
import com.lawencon.lmsjosepvictor.dao.ProfileDao;
import com.lawencon.lmsjosepvictor.dao.RoleDao;
import com.lawencon.lmsjosepvictor.dao.UserDao;
import com.lawencon.lmsjosepvictor.dto.InsertResDto;
import com.lawencon.lmsjosepvictor.dto.UpdatePhotoResDto;
import com.lawencon.lmsjosepvictor.dto.UpdateResDto;
import com.lawencon.lmsjosepvictor.dto.file.FileDto;
import com.lawencon.lmsjosepvictor.dto.login.LoginReqDto;
import com.lawencon.lmsjosepvictor.dto.login.LoginResDto;
import com.lawencon.lmsjosepvictor.dto.user.ChangePasswordReqDto;
import com.lawencon.lmsjosepvictor.dto.user.ProfilePhotoUpdateReqDto;
import com.lawencon.lmsjosepvictor.dto.user.ProfileResDto;
import com.lawencon.lmsjosepvictor.dto.user.ProfileUpdateReqDto;
import com.lawencon.lmsjosepvictor.dto.user.UserInsertReqDto;
import com.lawencon.lmsjosepvictor.dto.user.UsersResDto;
import com.lawencon.lmsjosepvictor.exception.CustomException;
import com.lawencon.lmsjosepvictor.model.File;
import com.lawencon.lmsjosepvictor.model.Profile;
import com.lawencon.lmsjosepvictor.model.Role;
import com.lawencon.lmsjosepvictor.model.User;
import com.lawencon.lmsjosepvictor.service.EmailService;
import com.lawencon.lmsjosepvictor.service.PrincipalService;
import com.lawencon.lmsjosepvictor.service.UserService;
import com.lawencon.lmsjosepvictor.util.GeneratorUtil;

@Service
public class UserServiceImpl implements UserService{
	
	private final UserDao userDao;
	private final ProfileDao profileDao;
	private final FileDao fileDao;
	private final RoleDao roleDao;
	private final EmailService emailService;
	private final PasswordEncoder passwordEncoder;
	private final PrincipalService principalService;
	
	@PersistenceContext
	private EntityManager em;
	
	public UserServiceImpl(UserDao userDao, ProfileDao profileDao, 
			FileDao fileDao, RoleDao roleDao, EmailService emailService, PrincipalService principalService, 
			PasswordEncoder passwordEncoder) {
		this.userDao = userDao;
		this.profileDao = profileDao;
		this.fileDao = fileDao;
		this.roleDao = roleDao;
		this.emailService = emailService;
		this.principalService = principalService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public List<UsersResDto> getAllUsers(String roleCode) {
		final List<UsersResDto> responses = new ArrayList<>();
		
		List<User> users = null;
		if (roleCode == null) {
			users = userDao.getAllUsers();
		} else {
			users = userDao.getUserByRoleCode(roleCode);
		}
		  
		users.forEach(u -> {
			final UsersResDto response = new UsersResDto();
			response.setId(u.getId());
			response.setName(u.getProfile().getProfileName());
			response.setPhone(u.getProfile().getProfilePhone());
			response.setAddress(u.getProfile().getProfileAddress());
			response.setRoleName(u.getRole().getRoleName());
			if (u.getIsActive()) {
				response.setActiveStatus("Active");
			} else {
				response.setActiveStatus("Not Active");
			}
			
			responses.add(response);
		});
		
		return responses;
	}

	@Transactional
	@Override
	public InsertResDto insert(UserInsertReqDto data) {
		final InsertResDto response = new InsertResDto();
		
		User newUser = null;
		final User user = new User();
		user.setEmail(data.getEmail());

		final Role role = roleDao.getById(data.getRoleId());
		
		final Profile profile = new Profile();
		profile.setProfileName(data.getProfileName());
		profile.setProfilePhone(data.getProfilePhone());
		profile.setProfileAddress(data.getProfileAddress());
		if (RoleCode.STUDENT.roleCode.equals(role.getRoleCode())) {
			profile.setCreatedBy(0L);
		} else if(RoleCode.TEACHER.roleCode.equals(role.getRoleCode())) {
			profile.setCreatedBy(principalService.getId());
		}
		profile.setCreatedBy(0L);
		
		if (data.getFile() != null && !data.getFile().isEmpty()) {
			final File newFile = new File();
			newFile.setFiles(data.getFile());
			newFile.setFileFormat(data.getFileFormat());
			if (RoleCode.STUDENT.roleCode.equals(role.getRoleCode())) {
				newFile.setCreatedBy(0L);
			} else if(RoleCode.TEACHER.roleCode.equals(role.getRoleCode())) {
				newFile.setCreatedBy(principalService.getId());
			}
			
			fileDao.insertFile(newFile);
			profile.setFile(newFile);
		}
		
		final Profile newProfile = profileDao.insertProfile(profile);
		
		final String randomPassword = GeneratorUtil.generateAlphaNum(5);
		if (RoleCode.STUDENT.roleCode.equals(role.getRoleCode())) {
			user.setPassword(passwordEncoder.encode(data.getPassword()));
		} else if(RoleCode.TEACHER.roleCode.equals(role.getRoleCode())) {
			user.setPassword(passwordEncoder.encode(randomPassword));
		}

		user.setProfile(newProfile);
		user.setRole(role);
		if (RoleCode.STUDENT.roleCode.equals(role.getRoleCode())) {
			user.setCreatedBy(0L);
		} else if(RoleCode.TEACHER.roleCode.equals(role.getRoleCode())) {
			user.setCreatedBy(principalService.getId());
		}

		newUser = userDao.insert(user);
		userDao.update(newUser);
		
		if(RoleCode.TEACHER.roleCode.equals(role.getRoleCode())) {
			final String body = "Hello, " + data.getProfileName() + ". Here is your password for your account : " + randomPassword;
			emailService.sendEmail("Account Password", body, data.getEmail());
		} else if(RoleCode.STUDENT.roleCode.equals(role.getRoleCode())) {
			final String body = "Hello, " + data.getProfileName() + ". You have successfully created account for learning management system. thankyou!";
			emailService.sendEmail("Account created Successfully", body, data.getEmail());
		}

		final User updateUser = userDao.getById(newUser.getId());
		updateUser.setCreatedBy(newUser.getId());
		updateUser.getProfile().setCreatedBy(newUser.getId());
		if(updateUser.getProfile().getFile() != null) {
			updateUser.getProfile().getFile().setCreatedBy(newUser.getId());	
		}

		response.setId(newUser.getId());
		response.setMessage("Account created successfully");
		
		return response;
	}

	@Transactional
	@Override
	public UpdateResDto update(ProfileUpdateReqDto data) {
		final UpdateResDto response = new UpdateResDto();
		User userDb = null;
		final Profile profileDb = profileDao.getById(data.getProfileId());
		profileDb.setProfileName(data.getProfileName());
		profileDb.setProfilePhone(data.getProfilePhone());
		profileDb.setProfileAddress(data.getProfileAddress());
		profileDb.setUpdatedBy(principalService.getId());

		userDb = userDao.getById(principalService.getId());
		userDb.setProfile(profileDb);
		userDb.setUpdatedBy(principalService.getId());
			
		response.setVer(userDb.getVer());
		response.setMessage("Profile updated successfully");
		return response;
	}

	@Transactional
	@Override
	public UpdatePhotoResDto updatePhoto(ProfilePhotoUpdateReqDto data) {
		UpdatePhotoResDto updateResDto = new UpdatePhotoResDto();
		final Profile profile = profileDao.getById(data.getProfileId());
		
		Long fileId = 0L;
		if(profile.getFile() != null) {
			fileId = profile.getFile().getId();
		}
			
		final File newFile = new File();
		newFile.setFiles(data.getFile());
		newFile.setFileFormat(data.getFileFormat());
		newFile.setCreatedBy(principalService.getId());
		fileDao.insertFile(newFile);
			
		profile.setFile(newFile);
		profile.setUpdatedBy(principalService.getId());
			
		profileDao.update(profile);
		
		updateResDto.setVer(profile.getVer());
		updateResDto.setFileId(profile.getFile().getId());
			
		fileDao.deleteFileById(fileId);	
			
		updateResDto.setMessage("Photo updated successfully");
		
		return updateResDto;
	}

	@Override
	public LoginResDto login(LoginReqDto data) {
		final LoginResDto loginResDto = new LoginResDto();
		
		final User user = userDao.getByEmail(data.getEmail());
		
		if(user != null) {
			loginResDto.setUserId(user.getId());
			loginResDto.setProfileName(user.getProfile().getProfileName());
			loginResDto.setRoleCode(user.getRole().getRoleCode());
			if(user.getProfile().getFile()!= null) {
				loginResDto.setFileId(user.getProfile().getFile().getId());	
			}
		}
		return loginResDto;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final User user = userDao.getByEmail(username);
		if(user != null) {
			return new org.springframework.security.core.userdetails.User(username, user.getPassword(), new ArrayList<>());
		} 
		throw new UsernameNotFoundException("Email not found!");
	}
	
	@Override
	public ProfileResDto getUserDetail() {
		final User user = userDao.getById(principalService.getId());
		
		final ProfileResDto response = new ProfileResDto();
		response.setEmail(user.getEmail());
		response.setName(user.getProfile().getProfileName());
		response.setPhone(user.getProfile().getProfilePhone());
		response.setAddress(user.getProfile().getProfileAddress());
		response.setRoleName(user.getRole().getRoleName());
		
		if(user.getProfile().getFile() != null) {
			final FileDto fileDto = new FileDto();
			fileDto.setFiles(user.getProfile().getFile().getFiles());
			fileDto.setFileFormat(user.getProfile().getFile().getFileFormat());
		}
		
		return response;
	}

	@Transactional
	@Override
	public UpdateResDto changePassword(ChangePasswordReqDto data) {
		final UpdateResDto response = new UpdateResDto();
		final User user = userDao.getById(principalService.getId());
		
		if(passwordEncoder.matches(data.getOldPassword(), user.getPassword())) {
			user.setPassword(passwordEncoder.encode(data.getNewPassword()));
			
			final User updatedUser = em.merge(user);
			em.flush();
			response.setVer(updatedUser.getVer());
			response.setMessage("Password Changed Successfully");
		} else {
			throw new CustomException("Error! old password did not match!");
		}
		return response;
	}
}
