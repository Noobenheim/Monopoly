package com.techelevator.objects.tiles;

public class RailroadProperty extends Property {
	private static int nth = 0;
	
	public RailroadProperty(int nthProperty, String name) {
		super(
			new int[] { 5, 15, 25, 35 },
			nthProperty,
			4,
			name,
			new int[] { 200 },
			null,
			null
		);
	}
	public RailroadProperty(String name) {
		this(nth++, name);
	}
	public RailroadProperty(int nthProperty) {
		this(nthProperty, getDefaultName(nthProperty));
	}
	public RailroadProperty() {
		this(nth++);
	}
	
	public static String getDefaultName(int nthProperty) {
		switch( nthProperty ) {
			case 0:
				return "Reading Railroad";
			case 1:
				return "Pennsylvania Railroad";
			case 2:
				return "B. and O. Railroad";
			case 3:
				return "Short Line";
		}
		return null;
	}
	
	@Override
	public int getHouseCost() {
		return 0;
	}
	
	@Override
	public int getRent() {
		return 0;
	}
}
