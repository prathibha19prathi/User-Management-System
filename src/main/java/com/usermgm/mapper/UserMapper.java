package com.usermgm.mapper;

import org.springframework.stereotype.Component;

import com.usermgm.entity.User;
import com.usermgm.requestdto.UserRequest;
import com.usermgm.responesdto.UserResponse;

@Component
public class UserMapper {

	public User mapUserRequestToUser(UserRequest userRequest, User user) {
		user.setUsername(userRequest.getUsername());
		user.setEmail(userRequest.getEmail());
		user.setPassword(userRequest.getPassword());
		user.setContactNumber(userRequest.getContactNumber());
		return user;
	}

	public UserResponse mapUserToUserResponse(User user) {
		return UserResponse.builder()
				.id(user.getId())
				.username(user.getUsername())
				.email(user.getEmail())
				.contactNumber(user.getContactNumber())
				.build();
	}

}
