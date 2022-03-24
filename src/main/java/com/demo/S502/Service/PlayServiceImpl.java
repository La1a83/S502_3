package com.demo.S502.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.S502.Repository.IDiceRepository;
import com.demo.S502.dto.PlayerResponseDto;
import com.demo.S502.dto.RoundResponseDto;






@Service
public class PlayServiceImpl implements DicePlayService {
	
	@Autowired
	IDiceRepository play;
	
	
	
	//Mètode per obtenir el llistat de tots els jugadors.
	@Override
	public List<PlayerResponseDto> getAll() {
		List<PlayerResponseDto> players = new ArrayList<>();
		play.findAll().forEach(player -> players.add(player));
		return players;
		
	}

	//Mètode per afegir un jugador.
	@Override
	public void addPlayer(PlayerResponseDto player) {
		boolean repeated = checkName(player.getName());
		if (repeated==false) {
			play.save(player);
		} 
		
	}

	//Mètode per executar una nova partida al jugador amb l'id passat per paràmetre.
	@Override
	public RoundResponseDto newRound(String id) {
		PlayerResponseDto player = findById(id);
		List<RoundResponseDto>rounds = player.getRounds();
		RoundResponseDto anotherRound = new RoundResponseDto();
		anotherRound.setDice1();
		anotherRound.setDice2();
		anotherRound.setSum();
		anotherRound.setWinsa();
		rounds.add(anotherRound);
		//Cada cop que el jugador executa una nova partida, s'actualitza el percentatge mig d'èxit.
		player.setSuccess(calcSuccess(player));
		play.save(player);
		return anotherRound;
	}

	
	//Mètode que retorna el jugador amb l'id indicat per paràmetre.
	@Override
	public PlayerResponseDto findById(String id) {
		
		return play.findById(id).get();
	}
	
	
    //Mètode per actualitzar el nom el jugador tenin en compte que no sigui un nom repetit.
	@Override
	public void updateName(PlayerResponseDto player) {
		PlayerResponseDto player1 = findById(player.getId());
		boolean repeated = checkName(player.getName());
		if(repeated == false) {
			player1.setName(player.getName());
			play.save(player1);
		}
		
	}
	
	
	//Mètode per eliminar el jugador amb l'id indicat al paràmetre.
	@Override
	public void deletePlayer(String id) {
		play.deleteById(id);
		
	}

	//Mètode que elimina totes les partides del jugador amb id indicat per paràmetre.
	@Override
	public void deleteAllRoundsByPlayerId(String id) {
		PlayerResponseDto player = findById(id);
		player.setRounds(null);
	    //També s'ha d'actualitzar el percentatge d'èxit a 0:
		player.setSuccess(0);
		play.save(player);
		
		
	}
	
	
	
    //Mètode que retorna totes les partides del jugador amb l'id indicat per paràmetre.
	@Override
	public List<RoundResponseDto> getAllRoundsByIdPlayer(String id) {
		PlayerResponseDto player = findById(id);
		List<RoundResponseDto> rounds = player.getRounds();
		return rounds;
	}

	
	//Mètode que retorna un hashmap ordenat per les puntuacions dels jugadors.
	@Override
	public TreeMap<Double, String> rankingSuccess() {
		TreeMap <Double, String> ranking = new TreeMap<Double, String>();
		List<PlayerResponseDto> players = new ArrayList<>();
		play.findAll().forEach(player ->players.add(player));
		for (PlayerResponseDto p : players) {
			ranking.put(p.getSuccess(),p.getName());
		}
		return ranking;
	}
	
	//Mètode que retorna el jugador amb menys percentatge d'èxit.
	@Override
	public PlayerResponseDto findLoser() {
		double min = 100.0;
		PlayerResponseDto loser= new PlayerResponseDto();
		List<PlayerResponseDto> players = getAll();
		for(PlayerResponseDto player : players) {
			if (player.getSuccess()<min) {
				min = player.getSuccess();
				loser = player;
			}
		}
		return loser;
	}
	//Mètode que retorna el jugador amb mès percentage d'èxit
	@Override
	public PlayerResponseDto findWinner() {
		double max = 0.0;
		PlayerResponseDto winner= new PlayerResponseDto();
		List<PlayerResponseDto> players = getAll();
		for(PlayerResponseDto player : players) {
			if (player.getSuccess()>max) {
				max = player.getSuccess();
				winner = player;
			}
		}
		return winner;
	}
	
	//Mètode auxiliar per verificar que el nom no estigui repetit.
		public boolean checkName (String name) {
			boolean repeated = false;
			List<PlayerResponseDto> players = new ArrayList<>();
			players = getAll();
		    int count = 0;
			while(repeated == false && count<players.size()) {
				if (players.get(count).getName().equalsIgnoreCase(name)) {
					if (players.get(count).getName().equalsIgnoreCase("Anònim")) {
						repeated = false;
					} else {
						repeated = true;
					}
				}
				count ++;
			}
			return repeated;
		}
		
		
		
	
	//Mètode auxiliar per calcular el percentatge mig d'èxit del jugador.	
	public double calcSuccess(PlayerResponseDto player) {
		double wins=0;
    	double count=0;
    	double result;
    	List<RoundResponseDto> rounds = player.getRounds();
		for (RoundResponseDto x : rounds) {
    		if (x.isWins()== true) {
    			wins++;
    		}
    		count++;
    	}
    	if (count!=0 && wins!=0) {
    		result = (wins/count)*100;
    	} else {
    		result = 0;
    	}
    	System.out.println("wins="+wins+"count="+count+"result"+result);
    	return result;
    	
	}

	

	
}
