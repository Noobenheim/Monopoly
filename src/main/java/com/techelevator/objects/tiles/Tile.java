package com.techelevator.objects.tiles;

import com.techelevator.objects.Piece;

public abstract class Tile {
	public Tile() {
	}
	
	public abstract void onLandOn(Piece p, Tile[] properties);
	public void onPass(Piece p) {
		// generally do nothing
	}
}
