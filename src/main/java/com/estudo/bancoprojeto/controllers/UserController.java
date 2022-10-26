package com.estudo.bancoprojeto.controllers;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    
    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder, UserService userService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    	this.userService = userService;
    }

//  @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/salvar")
    public ResponseEntity<UserModel> salvar(@RequestBody UserModel userModel) {
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        return ResponseEntity.ok(userRepository.save(userModel));
    }

//  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/listarTodos")
    public ResponseEntity<Page<UserModel>> getAllUsers(@PageableDefault(page = 0, size = 10, sort = "userId", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll(pageable));
    }

//  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/{userId}")
    public ResponseEntity<Object> getOneParkingSpot(@PathVariable(value = "userId") UUID userId){
        Optional<UserModel> parkingSpotModelOptional = userService.findById(userId);
        if (!parkingSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotModelOptional.get());
    }
    
//   @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{userId}")
	public ResponseEntity<Void> delete(@PathVariable UUID userId) {
		userService.delete(userId);
		return ResponseEntity.noContent().build();
	}

//   @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/{userId}")
	public ResponseEntity<UserModel> update(@PathVariable UUID userId, @RequestBody UserModel obj) {
		obj = userService.update(userId, obj);
		return ResponseEntity.ok().body(obj);
	}

}