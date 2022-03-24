package com.demo.S502.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.demo.S502.dto.Role;

public interface IRoleRepository  extends MongoRepository <Role, String>{
	
	Role findByRole(String role);
	  

}
