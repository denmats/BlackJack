package com.denmats.blackjack;

public class Main {

	public static void main(String[] args) {
		System.out.println("========== BLACKJACK ==========");
		Gamer player = new Gamer("player");
		Gamer dealer = new Gamer("dealer");
		Game game = new Game();
		game.gamble(player, dealer);
	}
}
