package com.usermgm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usermgm.entity.User;
import com.usermgm.requestdto.UserRequest;
import com.usermgm.responesdto.UserResponse;
import com.usermgm.service.UserService;
import com.usermgm.util.ErrorStructure;
import com.usermgm.util.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "User Endpoints", description = "Contains all the endpoints that are related to the user entity")
public class UserController {
     @Autowired
     public UserService userService;
     
     @Operation(description = "The endpoint is used to add the user data to the database",
    		 responses = {
    				 @ApiResponse(responseCode = "201", description = "User Created"),
    				 @ApiResponse(responseCode = "400", description = "Invalid Input", content = {
    						 @Content(schema = @Schema(oneOf = ErrorStructure.class))
    				 })
    		 })
     @PostMapping("/users")
     public ResponseEntity<ResponseStructure<UserResponse>> addUser(@RequestBody @Valid UserRequest userRequest){
    	return userService.addUser(userRequest);
     }
     
     @Operation(description = "The endpoint is used to find the all users data to the database",
    		 responses = {
    				 @ApiResponse(responseCode = "302", description = "User founded"),
    				 @ApiResponse(responseCode = "404", description = "Invalid Input", content = {
    						 @Content(schema = @Schema(oneOf = ErrorStructure.class))
    				 })
    		 })
     @GetMapping("/users")
     public ResponseEntity<ResponseStructure<List<UserResponse>>> getAllUsers(){
    	 return userService.gatAllUsers();
     }
     
     @Operation(description = "The endpoint is used to find the user data to the database",
    		 responses = {
    				 @ApiResponse(responseCode = "302", description = "User founded"),
    				 @ApiResponse(responseCode = "404", description = "Invalid Input", content = {
    						 @Content(schema = @Schema(oneOf = ErrorStructure.class))
    				 })
    		 })
     @GetMapping("/users/{id}")
     public ResponseEntity<ResponseStructure<UserResponse>> findUserById(@PathVariable Long id){
    	 return userService.findUserById(id);
     }
     
     @Operation(description = "The endpoint is used to delete the user data to the database",
    		 responses = {
    				 @ApiResponse(responseCode = "200", description = "User deleted"),
    				 @ApiResponse(responseCode = "404", description = "Invalid Id", content = {
    						 @Content(schema = @Schema(oneOf = ErrorStructure.class))
    				 })
    		 })
     @DeleteMapping("/users/{id}")
     public ResponseEntity<ResponseStructure<UserResponse>> deleteUserById(@PathVariable Long id){
    	 return userService.deleteUserById(id);
     }
     
     @Operation(description = "The endpoint is used to update the user data to the database",
    		 responses = {
    				 @ApiResponse(responseCode = "200", description = "User updated"),
    				 @ApiResponse(responseCode = "404", description = "Invalid Id", content = {
    						 @Content(schema = @Schema(oneOf = ErrorStructure.class))
    				 })
    		 })
     @PutMapping("/users/{id}")
     public ResponseEntity<ResponseStructure<UserResponse>> updateUserById(@PathVariable Long id, @RequestBody UserRequest userRequest){
    	 return userService.updateUserById(id, userRequest);
     }
}
