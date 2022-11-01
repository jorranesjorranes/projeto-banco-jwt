package com.estudo.bancoprojeto.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estudo.bancoprojeto.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {

	Optional<UserModel> findById(Integer id);
    Optional<UserModel> findByUsername(String username);
    
}
