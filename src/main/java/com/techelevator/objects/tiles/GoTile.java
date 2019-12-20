package com.techelevator.objects.tiles;

import com.techelevator.objects.Piece;

public class GoTile extends Tile {
	@Override
	public void onLandOn(Piece p, Tile[] properties) {
		
	}
	@Override
	public void onPass(Piece p) {
		System.out.println("HERE'! $200!");
	}
}
