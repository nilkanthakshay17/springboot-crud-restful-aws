package com.axee.springboot.api.controller;

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

import com.axee.springboot.api.entity.User;
import com.axee.springboot.api.exception.ResourceNotFoundException;
import com.axee.springboot.api.repository.UserRepository;

@RestController
@RequestMapping("/api/v1")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	//Get All Users
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers(){
		 List<User> responseUsersList=userRepository.findAll();
		 return ResponseEntity.status(HttpStatus.OK).body(responseUsersList);
	}
	
	//Get User By Id
	@GetMapping("/users/{id}")
	public ResponseEntity<?> getUserById(@PathVariable int id){
		User returnedUser=userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User with ID : "+id+" not found","Invalid ID"));
		
		return ResponseEntity.status(HttpStatus.OK).body(returnedUser);
	}
	
	//Create User
	@PostMapping("/users")
	public ResponseEntity<?> createUser(@RequestBody User userInput){
		User savedUser=userRepository.save(userInput);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	}
	//Update User
	@PutMapping("/users/{id}")
	public ResponseEntity<?> updateUser(@RequestBody User userInput,@PathVariable int id){
		User userToUpdate=userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User with ID : "+id+" not found", "Invalid ID"));
		
		userToUpdate.setFirstName(userInput.getFirstName());
		userToUpdate.setLastName(userInput.getLastName());
		userToUpdate.setEmail(userInput.getEmail());
		
		User updatedUser=userRepository.save(userToUpdate);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(updatedUser);
	}
	
	//Delete User
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable int id){
		User userToDelete=userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User with ID : "+id+" not found", "Invalid ID"));
		userRepository.delete(userToDelete);
		return ResponseEntity.status(HttpStatus.CREATED).body("Deleted User : "+id);
	}
	
}
