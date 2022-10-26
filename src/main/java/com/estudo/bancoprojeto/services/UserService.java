package com.estudo.bancoprojeto.services;

import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.estudo.bancoprojeto.models.UserModel;
import com.estudo.bancoprojeto.repositories.UserRepository;
import com.estudo.bancoprojeto.services.exceptions.DatabaseException;
import com.estudo.bancoprojeto.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public UserModel insert(UserModel obj) {
		return userRepository.save(obj);
	}

    @Transactional
    public UserModel save(UserModel userModel) {
        return userRepository.save(userModel);
    }

    public Page<UserModel> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public Optional<UserModel> findById(UUID userId) {
        return userRepository.findById(userId);
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

//    @Transactional
//    public void delete(UserModel userModel) {
//        userRepository.delete(userModel);
//    }
    
    
    public void delete(UUID userId) {
		try {
		userRepository.deleteById(userId);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(userId);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
		
	/*
	@Transactional(rollbackOn = Exception.class) 
	public String insertDto(GestorDTO dto) { 
	    Object bCryptPasswordEncoder;
		GestorDTO.setSenha(bCryptPasswordEncoder
	           .encode(dto.getSenha())); 
	    return insert(new Gestor(dto)).getNomeEstabelecimento(); 
	}
	*/
		
	}
}