package com.demo.S502.Service;


import java.util.List;
import java.util.TreeMap;
import com.demo.S502.dto.PlayerResponseDto;
import com.demo.S502.dto.RoundResponseDto;

public interface DicePlayService {
	
	
	public List <PlayerResponseDto> getAll();
	public PlayerResponseDto findById (String id);
	public void addPlayer(PlayerResponseDto player);
	public void updateName (PlayerResponseDto player);
	public RoundResponseDto newRound (String id);
	public void deletePlayer (String id);
	public void deleteAllRoundsByPlayerId(String id);
	public List<RoundResponseDto> getAllRoundsByIdPlayer(String id);
	public TreeMap<Double, String>  rankingSuccess();
	public PlayerResponseDto findLoser();
	public PlayerResponseDto findWinner();
	
}
