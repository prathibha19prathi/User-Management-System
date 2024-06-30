package com.usermgm.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.usermgm.entity.User;
import com.usermgm.requestdto.UserRequest;
import com.usermgm.responesdto.UserResponse;
import com.usermgm.util.ResponseStructure;

public interface UserService {

	ResponseEntity<ResponseStructure<UserResponse>> addUser(UserRequest userRequest);

	ResponseEntity<ResponseStructure<List<UserResponse>>> gatAllUsers();

	ResponseEntity<ResponseStructure<UserResponse>> findUserById(Long id);

	ResponseEntity<ResponseStructure<UserResponse>> deleteUserById(Long id);

	ResponseEntity<ResponseStructure<UserResponse>> updateUserById(Long id, UserRequest userRequest);

}
