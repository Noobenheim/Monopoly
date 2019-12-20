package com.techelevator.objects.tiles;

public class PurpleProperty extends Property {
	private static int nth = 0;
	
	public PurpleProperty(int nthProperty, String name) {
		super(
			new int[] { 11, 13, 14 },
			nthProperty,
			3,
			name,
			new int[] { 140, 160 },
			new int[] { 10, 50, 150, 450, 625, 750 },
			new int[] { 12, 60, 180, 500, 700, 900 }
		);
	}
	public PurpleProperty(String name) {
		this(nth++, name);
	}
	public PurpleProperty(int nthProperty) {
		this(nthProperty, getDefaultName(nthProperty));
	}
	public PurpleProperty() {
		this(nth++);
	}
	
	public static String getDefaultName(int nthProperty) {
		switch( nthProperty ) {
			case 0:
				return "St. Charles Place";
			case 1:
				return "States Avenue";
			case 2:
				return "Virginia Avenue";
		}
		return null;
	}
}
