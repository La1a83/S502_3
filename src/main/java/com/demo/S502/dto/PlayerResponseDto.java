package com.demo.S502.dto;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;





@Document (collection = "Player")
public class PlayerResponseDto extends ResponseDto {

	
	
	
	
	@Id
	private String id;
    private String name;
    private LocalDateTime createdAt= LocalDateTime.now();
    private List<RoundResponseDto> rounds = new ArrayList<RoundResponseDto>();
    private double success;
    
   
    
    
    //Getters and Setters

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name.isEmpty()) {
			this.name = "Anònim";
		} else {
			this.name = name;
		}
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public List<RoundResponseDto> getRounds() {
		return rounds;
	}

	public void setRounds(List<RoundResponseDto> rounds) {
		this.rounds = rounds;
	}
	
	
	public double getSuccess() {
		return success;
	}


	public void setSuccess(double success) {
		this.success = success;
	}


    //Mètode ToString
	@Override
	public String toString() {
		return "PlayerResponseDto [id=" + id + ", name=" + name + ", createdAt=" + createdAt + ", rounds=" + rounds
				+ ", success=" + success + "%]";
	}
}