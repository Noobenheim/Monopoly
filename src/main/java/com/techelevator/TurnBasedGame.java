package com.techelevator;

public abstract class TurnBasedGame implements Runnable {
	private int numberOfPlayers;
	protected final long startTime;
	
	public abstract void setup();
	public abstract void takeTurn(int playerNumber);
	public abstract void startGame();
	public abstract void finished();
	
	public TurnBasedGame() {
		startTime = System.nanoTime();
	}
	public void start() {
		this.run();
	}
	
	public void run() {
		setup();
		startGame();
	}
	
	public int getNumberOfPlayers() { return this.numberOfPlayers; }
}
