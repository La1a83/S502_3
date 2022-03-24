package com.demo.S502.dto;




public class RoundResponseDto extends ResponseDto {
	
	
	static final int max = 6;
    static final int min = 1;
	
	private int dice1;
	private int dice2;
	private int sum;
	private boolean wins;

  
    
	
	
	//Getters and Setters
	
	public int getDice1() {
		return dice1;
	}
	public void setDice1() {
		this.dice1 = (int)(Math.random()*(max-min)+min);
	}
	public int getDice2() {
		return dice2;
	}
	public void setDice2() {
		this.dice2 = (int)(Math.random()*(max-min)+min);
	}
	public int getSum() {
		return sum;
	}
	public void setSum() {
		this.sum = this.dice1+this.dice2;
	}
	public boolean isWins() {
		return wins;
	}
	public void setWinsa() {
		if (this.sum==7) {
			this.wins = true;
		} else {
			this.wins = false;
		}
	}
	
	
	
	
	@Override
	public String toString() {
		return "RoundResponseDto [dice1=" + dice1 + ", dice2=" + dice2 + ", sum=" + sum + ", wins="
				+ wins +  "]";
	}
	
	
	
	
	
	
	
	
	
	
	

}
