package com.techelevator.objects.tiles;

import com.techelevator.Const;
import com.techelevator.objects.Board;
import com.techelevator.objects.Piece;

import javafx.geometry.BoundingBox;
import javafx.scene.paint.Color;

public abstract class Property extends Tile {
	public enum PropertyColor {
		BROWN(Color.SADDLEBROWN),
		LIGHT_BLUE(Color.ALICEBLUE),
		PURPLE(Color.PURPLE),
		ORANGE(Color.DARKORANGE),
		RED(Color.RED),
		YELLOW(Color.YELLOW),
		GREEN(Color.GREEN),
		BLUE(Color.BLUE),
		UTILITY(Color.WHITE),
		RAILROAD(Color.BLACK),
		CORNER(Color.SLATEGRAY),
		COMMUNITY_CHEST(Color.DEEPSKYBLUE),
		CHANCE(Color.MEDIUMPURPLE),
		TAX(Color.GOLD),
		
		UNKNOWN(Color.BLANCHEDALMOND);
		
		private Color color;
		private PropertyColor(Color c) {
			this.color = c;
		}
		public Color getColor() {
			return this.color;
		}
	}
	
	private int index;
	private int houses = 0;
	private int nthProperty;
	private int totalProperties;
	private String name;
	private int[] costs;
	private int[] minRents;
	private int[] maxRents;
	
	public Property(int[] indexes, int nthProperty, int totalProperties, String name, int[] costs, int[] minRents, int[] maxRents) {
		super();
		
		nthProperty %= totalProperties;
		
		this.index = nthPropertyToIndex(nthProperty, indexes);
		this.nthProperty = nthProperty;
		this.totalProperties = totalProperties;
		this.name = name;
		this.costs = costs;
		this.minRents = minRents;
		this.maxRents = maxRents;
	}
	
	public Color getColor() {
		return getColorByIndex(index);
	}
	public int getHouseCost() {
		return getHouseCostByIndex(index);
	}
	public int getRent() {
		if( nthProperty == totalProperties-1 ) {
			return maxRents[houses];
		} else {
			return minRents[houses];
		}
	}
	public int getCost() {
		if( costs.length == 1 ) {
			return costs[0];
		}
		if( nthProperty == totalProperties-1 ) {
			return costs[costs.length-1];
		}
		return costs[0];
	}
	public String getName() {
		return this.name;
	}
	
	@Override
	public void onLandOn(Piece p, Tile[] properties) {
		
	}
	
	private static int nthPropertyToIndex(int n, int...indexes) {
		if( n < indexes.length ) {
			return indexes[n];
		}
		return -1;
	}
	private static int getHouseCostByIndex(int index) {
		int side = (index%40)/10;
		
		switch( side ) {
			case 0:
				return 50;
			case 1:
				return 100;
			case 2:
				return 150;
			case 3:
				return 200;
		}
		
		return 0;
	}
	public static Color getColorByIndex(int index) {
		index %= 40;
		// filter out non-properties
		if( index%10 == 0 ) { // corner
			return PropertyColor.CORNER.getColor();
		}
		if( index%5 == 0 ) { // railroad
			return PropertyColor.RAILROAD.getColor();
		}
		switch( index ) {
			case 2:
			case 17:
			case 33:
				return PropertyColor.COMMUNITY_CHEST.getColor();
			case 4:
			case 38:
				return PropertyColor.TAX.getColor();
			case 7:
			case 22:
				return PropertyColor.CHANCE.getColor();
			case 12:
			case 28:
				return PropertyColor.UTILITY.getColor();
		}
		
		int side = index/5;
		
		switch( side ) {
			case 0:
				return PropertyColor.BROWN.getColor();
			case 1:
				return PropertyColor.LIGHT_BLUE.getColor();
			case 2:
				return PropertyColor.PURPLE.getColor();
			case 3:
				return PropertyColor.ORANGE.getColor();
			case 4:
				return PropertyColor.RED.getColor();
			case 5:
				return PropertyColor.YELLOW.getColor();
			case 6:
				return PropertyColor.GREEN.getColor();
			case 7:
				return PropertyColor.BLUE.getColor();
		}
		
		return PropertyColor.UNKNOWN.getColor();
	}

	public static boolean isCorner(int index) {
		return index%10 == 0;
	}
	public static BoundingBox getBoundingBoxForProperty( int index, boolean subtractTitle ) {
		index %= 40;
		int spaces = index%10;
		
		double x, y, width, height;
		
		if( index < 10 ) { // along bottom
			int diff = 0;
			if( !isCorner(index) ) {
				diff = Board.TILE_WIDTH*spaces;
				
				width = Board.TILE_WIDTH;
				height = Board.TILE_HEIGHT - (subtractTitle?Board.PROPERTY_TITLE_WIDTH:0);
			} else {
				width = height = Board.CORNER_TILE_WIDTH;
			}
			x = Board.BOARD_CORNER_X - Board.CORNER_TILE_WIDTH - diff;
			
			y = Board.BOARD_CORNER_Z;
		} else if( index < 20 ) { // along left
			int diff = 0;
			if( !isCorner(index) ) {
				diff = Board.TILE_WIDTH*(spaces-1) + Board.CORNER_TILE_WIDTH;
				
				width = Board.TILE_HEIGHT - (subtractTitle?Board.PROPERTY_TITLE_WIDTH:0);
				height = Board.TILE_WIDTH;
			} else {
				width = height = Board.CORNER_TILE_WIDTH;
			}
			x = Board.BOARD_CORNER_X - Const.BOARD_WIDTH;
			
			y = Board.BOARD_CORNER_Z + diff;
		} else if( index < 30 ) { // along top
			int diff = 0;
			if( !isCorner(index) ) {
				diff = Board.TILE_WIDTH*(spaces-1) + Board.CORNER_TILE_WIDTH;
				
				width = Board.TILE_WIDTH;
				height = Board.TILE_HEIGHT - (subtractTitle?Board.PROPERTY_TITLE_WIDTH:0);
			} else {
				width = height = Board.CORNER_TILE_WIDTH;
			}
			x = Board.BOARD_CORNER_X - Const.BOARD_WIDTH + diff;
			
			y = Board.BOARD_CORNER_Z + Const.BOARD_DEPTH - Board.CORNER_TILE_WIDTH;
		} else { // along left
			int diff = 0;
			if( !isCorner(index) ) {
				diff = Board.TILE_WIDTH*spaces;
				
				width = Board.TILE_HEIGHT - (subtractTitle?Board.PROPERTY_TITLE_WIDTH:0);
				height = Board.TILE_WIDTH;
			} else {
				width = height = Board.CORNER_TILE_WIDTH;
			}
			x = Board.BOARD_CORNER_X - Board.CORNER_TILE_WIDTH;
			
			y = Board.BOARD_CORNER_Z + Const.BOARD_DEPTH - Board.CORNER_TILE_WIDTH - diff;
		}
		
		return new BoundingBox(x, y, width, height);
	}
}
