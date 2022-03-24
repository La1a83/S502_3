package com.demo.S502.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.demo.S502.dto.User;

public interface IUserRepository  extends MongoRepository <User, String>{
	
	User findByEmail(String email);

}
