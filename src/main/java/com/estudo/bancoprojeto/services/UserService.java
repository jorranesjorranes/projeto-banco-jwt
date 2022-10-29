package com.estudo.bancoprojeto.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudo.bancoprojeto.models.UserModel;
import com.estudo.bancoprojeto.repositories.UserRepository;
import com.estudo.bancoprojeto.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
    private UserRepository userRepository;
    
	public List<UserModel> findAll() {
		return userRepository.findAll();
	}
	
	public UserModel findById(UUID userId) {
		Optional<UserModel> obj = userRepository.findById(userId);
		return obj.orElseThrow(() -> new ResourceNotFoundException(userId));
	}
	
    public UserModel insert(UserModel obj) {
		return userRepository.save(obj);
	}	
    
//    public void delete(UUID userId) {
//		try {
//		userRepository.deleteById(userId);
//		} catch (EmptyResultDataAccessException e) {
//			throw new ResourceNotFoundException(userId);
//		} catch (DataIntegrityViolationException e) {
//			throw new DatabaseException(e.getMessage());
//		}
//	}
    
    @Transactional
    public void delete(UserModel userModel) {
        userRepository.delete(userModel);
    }
	
	public UserModel update(UUID userId, UserModel obj) {
		try {
			UserModel entity = userRepository.getReferenceById(userId);
			updateData(entity, obj);
			return userRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(userId);
		}
	}

	private void updateData(UserModel entity, UserModel obj) {
		entity.setUsername(obj.getUsername());
		entity.setPassword(obj.getPassword());
	}
	
}