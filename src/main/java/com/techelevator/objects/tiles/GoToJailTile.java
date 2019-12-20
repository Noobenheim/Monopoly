package com.techelevator.objects.tiles;

import com.techelevator.objects.Piece;

public class GoToJailTile extends Tile {
	@Override
	public void onLandOn(Piece p, Tile[] properties) {
		System.out.println("fakking go to jail");
		p.goToProperty(10);
	}
	@Override
	public void onPass(Piece p) {
	}
}
