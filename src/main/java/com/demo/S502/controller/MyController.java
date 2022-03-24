package com.demo.S502.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.demo.S502.Service.PlayServiceImpl;
import com.demo.S502.dto.PlayerResponseDto;
import com.demo.S502.dto.RoundResponseDto;




@RestController
@RequestMapping("/players")
public class MyController {
	
	
	private final PlayServiceImpl playerService;
    
    

    @Autowired
    public MyController(PlayServiceImpl playerService){
        this.playerService = playerService;
    }
    
    
    //Petició Get per testejar que funciona la conexió amb el servidor.
    @GetMapping(path="/test")
    public String greetings() {
        return "Hello World!";
    }
    
    //Petició Get que retorna llistat de tots els jugadors.
    @GetMapping
    public List<PlayerResponseDto> getAllPlayers() {
		List<PlayerResponseDto> players = new ArrayList<>();
		players = playerService.getAll();
		return players;
	}
    
    //Petició Get que retorna el llistat de partides d'un jugador amb l'id indicat al path
    @GetMapping("/{id}/games/")
    public List<RoundResponseDto> getAllRoundsByPlayer(@PathVariable String id) {
    	List<RoundResponseDto> rounds = playerService.getAllRoundsByIdPlayer(id);
    	return rounds;
    }
    
    //Petició get que retorna un ranking ordenat de forma ascendent de les puntuacions.
    @GetMapping("/ranking/")
    public TreeMap<Double, String>  getRanking(){
    	TreeMap<Double, String>  ranking = playerService.rankingSuccess();
    	return ranking;
    }
    
    
    //Petició Get que retorna el jugador amb menys percentatge mig d'èxit.
    @GetMapping("/ranking/loser")
    public PlayerResponseDto getLoser() {
    	return playerService.findLoser();
    }
    
    
    //Petició Get que retorna el jugador amb mès percentatge mig d'èxit.
    @GetMapping("/ranking/winner")
    public PlayerResponseDto getWinner() {
    	return playerService.findWinner();
    }
   
    
    //Petició Post per afegir un jugador (nomès cal indicar el nom al Json):
    @PostMapping
    public PlayerResponseDto add(@RequestBody PlayerResponseDto player) {
    	playerService.addPlayer(player);
    	return player;
    	
    }
    
    //Petició Put per actyualitzar el nom del jugador (Json amb id i nom actual)
    @PutMapping
    public PlayerResponseDto updateName(@RequestBody PlayerResponseDto player) {
    	playerService.updateName(player);
    	return player;
    }
    
    //Petició Post que executa una nova partida de daus al jugador amb id indicat al path.(no cal json)
    @PostMapping(path="/{id}/games/")
    public RoundResponseDto newRoundByPlayer(@PathVariable String id) {
         RoundResponseDto newRound =playerService.newRound(id);
    	 return newRound;
    }
    
    //Petició Delete que elimina el jugador amb l'id indicat al path del navegador.
    @DeleteMapping("/{id}")
    public void deletePlayerById(@PathVariable String id) {
    	playerService.deletePlayer(id);
    }
    
    //Petició Delete que elimina totes les partides del jugador amb l'id indicat al path.
    @DeleteMapping("/{id}/games/")
    public void deleteAllRounds(@PathVariable String id) {
    	playerService.deleteAllRoundsByPlayerId(id);
    }
    
    /*Petició Delete per eliminar una partida en concret, d'ús intern nomès
    @DeleteMapping("/{id}/rounds/")
    public void deleteRoundById(@PathVariable String id) {
    	playerService.deleteRoundsById(Integer.parseInt(id));
    }*/
	

}
