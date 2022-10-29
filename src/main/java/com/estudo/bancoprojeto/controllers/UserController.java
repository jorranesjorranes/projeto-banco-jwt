package com.estudo.bancoprojeto.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudo.bancoprojeto.models.UserModel;
import com.estudo.bancoprojeto.repositories.UserRepository;
import com.estudo.bancoprojeto.services.UserService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/user")
public class UserController {

	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
    
	@Autowired
	private UserService userService;

    @GetMapping("/listarTodos")
    public ResponseEntity<List<UserModel>> listarTodos() {
        return ResponseEntity.ok(userRepository.findAll());
    }
    
    @PostMapping("/salvar")
    public ResponseEntity<UserModel> salvar(@RequestBody UserModel userModel) {
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        return ResponseEntity.ok(userRepository.save(userModel));
    }
    
//    @DeleteMapping(value = "/{userId}")
//	public ResponseEntity<Void> delete(@PathVariable UUID userId) {
//		userService.delete(userId);
//		return ResponseEntity.noContent().build();
//	}
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") UUID id){
        Optional<UserModel> userModelOptional = Optional.of(userService.findById(id));
        if (!userModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found.");
        }
        userService.delete(userModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("user deleted successfully.");
    }
	
	@PutMapping(value = "/{userId}")
	public ResponseEntity<UserModel> update(@PathVariable UUID userId, @RequestBody UserModel obj) {
		obj = userService.update(userId, obj);
		return ResponseEntity.ok().body(obj);
	}
}