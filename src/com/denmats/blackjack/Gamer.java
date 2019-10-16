package com.denmats.blackjack;

import java.util.ArrayList;
import java.util.List;

public class Gamer {
	private String name;
	private int balance = 0;
	private List<Integer> cards;

	public Gamer(String name) {
		super();
		this.name = name;
		cards = new ArrayList<>();
	}

	public List<Integer> getCardSet() {
		return cards;
	}

	public void setCardSet(int number) {
		this.cards.add(number);
	}

	public String getName() {
		return name;
	}

	public int getBalance() {
		return balance;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
}
