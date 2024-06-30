package com.usermgm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.usermgm.entity.User;
import com.usermgm.exception.UserNotFoundException;
import com.usermgm.mapper.UserMapper;
import com.usermgm.repository.UserRepository;
import com.usermgm.requestdto.UserRequest;
import com.usermgm.responesdto.UserResponse;
import com.usermgm.service.UserService;
import com.usermgm.util.ResponseStructure;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> addUser(UserRequest userRequest) {
		User user = userMapper.mapUserRequestToUser(userRequest, new User());
		User savedUser = userRepository.save(user);
		UserResponse userResponse = userMapper.mapUserToUserResponse(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<UserResponse>()
				.setStatuscode(HttpStatus.CREATED.value()).setMessage("User created").setData(userResponse));
	}

	@Override
	public ResponseEntity<ResponseStructure<List<UserResponse>>> gatAllUsers() {
		List<UserResponse> users = userRepository.findAll().stream().map(user-> userMapper.mapUserToUserResponse(user)).toList();
		return ResponseEntity.status(HttpStatus.FOUND)
				.body(new ResponseStructure<List<UserResponse>>().setStatuscode(HttpStatus.FOUND.value())
						.setMessage("All Users Are finded").setData(users));
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> findUserById(Long id) {
		return userRepository.findById(id).map(user -> {
			return ResponseEntity.status(HttpStatus.FOUND).body(new ResponseStructure<UserResponse>()
					.setStatuscode(HttpStatus.FOUND.value()).setMessage("User is founded").setData(userMapper.mapUserToUserResponse(user)));
		}).orElseThrow(() -> new UserNotFoundException("Id : " + id + ", is not present in database"));
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> deleteUserById(Long id) {
		return userRepository.findById(id).map(user -> {
			userRepository.delete(user);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseStructure<UserResponse>()
					.setStatuscode(HttpStatus.OK.value()).setMessage("User is deleted").setData(userMapper.mapUserToUserResponse(user)));
		}).orElseThrow(() -> new UserNotFoundException("Id : " + id + ", is not present in database"));
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> updateUserById(Long id, UserRequest userRequest) {
		return userRepository.findById(id).map(user1 -> {
			User user = userMapper.mapUserRequestToUser(userRequest, user1);
			user.setId(id);
			user1 = userRepository.save(user);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseStructure<UserResponse>()
					.setStatuscode(HttpStatus.OK.value()).setMessage("User is updated").setData(userMapper.mapUserToUserResponse(user1)));
		}).orElseThrow(() -> new UserNotFoundException("Id : " + id + ", is not present in database"));
	}

}
