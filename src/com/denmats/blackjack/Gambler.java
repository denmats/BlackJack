package com.denmats.blackjack;

public interface Gambler {
	void firstDeal(Gamer gamer);
	
	void displayGamerCards(Gamer gamer);

	void hitOrPass(Gamer gamer);

	void checkBalance(Gamer gamer);

	void openCards(Gamer gamer1, Gamer gamer2);
	
	void displayStatisticsAtTheEndOfTheGame(Gamer gamer1, Gamer gamer2);
}
