package com.app.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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

import com.app.payloads.ApiResponse;
import com.app.payloads.UserDto;
import com.app.services.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	@Autowired
	private UserService  userService;
	
	//Post-create user
	@PostMapping("/")
	public  ResponseEntity<UserDto> createUser( @Valid @RequestBody UserDto userDto){
		UserDto createUserDto =this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
	}
	
	//Put -update user
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> update( @Valid @RequestBody UserDto userDto,@PathVariable("userId") Integer userId){
		UserDto updateUser =this.userService.updateUser(userDto,userId);
		return ResponseEntity.ok(updateUser);
		
	}
	
	//Delete-delete user
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable ("userId") Integer userId){
		//ResponseEntity<?> deleteUser =this.deleteUser(userId);
		this.userService.deleteUser(userId);
		return  new ResponseEntity(new ApiResponse("User deleted successfully",true),HttpStatus.OK);
	}

	
	//Get- user get(all user)
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	//Get- user get(single user)
		@GetMapping("/{userId}")
		public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId){
			return ResponseEntity.ok(this.userService.getUserById(userId));
		}

}
