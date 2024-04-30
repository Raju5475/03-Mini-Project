package com.raju.repo;



import org.springframework.data.jpa.repository.JpaRepository;

import com.raju.entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {

	
	  // Query method to find a user by email
    UserEntity findByEmail(String email);

    // Query method to find a user by email and password
    UserEntity findByEmailAndPassword(String email, String password);
}
