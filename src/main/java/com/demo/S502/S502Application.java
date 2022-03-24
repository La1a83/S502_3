package com.demo.S502;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.demo.S502.Repository.IRoleRepository;
import com.demo.S502.dto.Role;


@SpringBootApplication

public class S502Application {

	
	
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(S502Application.class, args);
	}
	
	
	@Bean
	CommandLineRunner init(IRoleRepository roleRepository) {

	    return args -> {

	        Role adminRole = roleRepository.findByRole("ADMIN");
	        if (adminRole == null) {
	            Role newAdminRole = new Role();
	            newAdminRole.setRole("ADMIN");
	            roleRepository.save(newAdminRole);
	        }
	    };

	}

}
