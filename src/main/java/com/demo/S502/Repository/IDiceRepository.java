package com.demo.S502.Repository;





import org.springframework.data.mongodb.repository.MongoRepository;

import com.demo.S502.dto.PlayerResponseDto;


public interface IDiceRepository extends MongoRepository<PlayerResponseDto, String> {

}
