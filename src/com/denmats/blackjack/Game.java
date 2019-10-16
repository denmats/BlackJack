package com.denmats.blackjack;

import java.util.Scanner;

public class Game implements Gambler {
	private static Scanner sc = new Scanner(System.in);
	private boolean isNotGameOver = true;
	private boolean isOneMoreCard = true;

	public void gamble(Gamer player, Gamer dealer) {

		while (isNotGameOver) {
			// First deal, both player and dealer got two cards
			firstDeal(player);
			firstDeal(dealer);

			while (isOneMoreCard) {
				openCards(player, dealer);
				
				hitOrPass(player);
				checkBalance(player);
				
				//if the dealer's balance more than 16, the dealer won't take another card
				hitOrPass(dealer);
				checkBalance(dealer);
			}
			// Statistics
			displayStatisticsAtTheEndOfTheGame(player, dealer);
		}
	}

	public void openCards(Gamer player, Gamer dealer) {
		if (isNotGameOver == true || isOneMoreCard == true) {
			System.out.println("Do you want open cards? y-yes, n-no");
			if (sc.hasNextLine()) {
				if (sc.nextLine().equals("y")) {
					if (player.getBalance() > dealer.getBalance()) {
						System.out.println(("Player is a winner").toUpperCase());
						isNotGameOver = false;
						isOneMoreCard = false;
					}

					if (player.getBalance() <= dealer.getBalance()) {
						System.out.println(("Dealer is a winner").toUpperCase());
						isNotGameOver = false;
						isOneMoreCard = false;
					}
				}
			}
		}
	}
	
	//This method given permission to dealer manually to make a decision to hit another card or not.

	/*
	 * public void hitOrPass(Gamer gamer) { if (isNotGameOver) {
	 * System.out.println(gamer.getName() + ": Hit - h or Pass - p: "); if
	 * (sc.hasNextLine()) { if (sc.nextLine().equalsIgnoreCase("h")) { if
	 * (gamer.getName().equals("dealer") && (gamer.getBalance() < 16) ||
	 * gamer.getName().equals("player")) { gamer.setCardSet(getRandomCard());
	 * 
	 * displayGamerCards(gamer); } } } } }
	 */

	//This method forbid dealer from manually making a decision to hit another card
	public void hitOrPass(Gamer gamer) {
		if (isNotGameOver) {

			if (gamer.getName().equals("dealer") && (gamer.getBalance() < 16)) {
				gamer.setCardSet(getRandomCard());
				displayGamerCards(gamer);
			}
			else
			{
				displayGamerCards(gamer);
			}

			if (gamer.getName().equals("player")) {
				System.out.println(gamer.getName() + ": Hit - h or Pass - p: ");
				if (sc.hasNextLine()) {
					if (sc.nextLine().equalsIgnoreCase("h")) {
						gamer.setCardSet(getRandomCard());
						displayGamerCards(gamer);
					}
				}
			}
		}
	}
	
	

	public void checkBalance(Gamer gamer) {

		if (isNotGameOver) {
			gamer.setBalance(gamer.getCardSet().stream().reduce(0, (a, b) -> a + b));

			if (gamer.getBalance() > 21) {
				System.out.println((gamer.getName() + " lost the game").toUpperCase());
				isNotGameOver = false;
				isOneMoreCard = false;
			}

			if (gamer.getBalance() == 21) {
				System.out.println((gamer.getName() + " won the game").toUpperCase());
				isNotGameOver = false;
				isOneMoreCard = false;
			}
		}
	}

	public void firstDeal(Gamer gamer) {

		gamer.setCardSet(getRandomCard());
		gamer.setCardSet(getRandomCard());

		displayGamerCards(gamer);

		checkBalance(gamer);
	}

	private int getRandomCard() {
		return 2 + (int) (Math.random() * (11 - 2 + 1));
	}

	public void displayGamerCards(Gamer gamer) {
		System.out.print(gamer.getName() + "'s cards: ");
		for (int i = 0; i < gamer.getCardSet().size(); i++) {

			if (gamer.getName().equals("player")) {
				System.out.print(gamer.getCardSet().get(i) + " ");
			}

			if (gamer.getName().equals("dealer")) {
				if (i == 0) {
					System.out.print(gamer.getCardSet().get(i) + " ");
				} else {
					System.out.print("* ");
				}
			}
		}
		System.out.println();
	}

	public void displayStatisticsAtTheEndOfTheGame(Gamer player, Gamer dealer) {
		System.out.println("\n========== GAME OVER ==========");
		System.out.print("Player's balance: " + player.getBalance() + "; ");
		displayGamerCards(player);

		System.out.print("Dealer's balance: " + dealer.getBalance() + "; ");
		System.out.print("Dealer's cards: ");
		for (Integer cards : dealer.getCardSet()) {
			System.out.print(cards + " ");
		}
		System.out.println();
	}
}
