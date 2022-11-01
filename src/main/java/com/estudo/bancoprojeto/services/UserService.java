package com.estudo.bancoprojeto.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.estudo.bancoprojeto.models.UserModel;
import com.estudo.bancoprojeto.repositories.UserRepository;
import com.estudo.bancoprojeto.services.exceptions.DatabaseException;
import com.estudo.bancoprojeto.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
    private UserRepository userRepository;
    
	public List<UserModel> findAll() {
		return userRepository.findAll();
	}
	
	public UserModel findById(Integer id) {
		Optional<UserModel> obj = userRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
    public UserModel insert(UserModel obj) {
		return userRepository.save(obj);
	}	
    
    public void delete(Integer id) {
		try {
		userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
    
//    @Transactional
//    public void delete(UserModel userModel) {
//        userRepository.delete(userModel);
//    }
	
	public UserModel update(Integer id, UserModel obj) {
		try {
			UserModel entity = userRepository.getReferenceById(id);
			updateData(entity, obj);
			return userRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(UserModel entity, UserModel obj) {
		entity.setId(obj.getId());
		entity.setUsername(obj.getUsername());
		entity.setPassword(obj.getPassword());
	}
	
}