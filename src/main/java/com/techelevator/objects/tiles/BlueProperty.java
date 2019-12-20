package com.techelevator.objects.tiles;

public class BlueProperty extends Property {
	private static int nth = 0;
	
	public BlueProperty(int nthProperty, String name) {
		super(
			new int[] { 37, 39 },
			nthProperty,
			2,
			name,
			new int[] { 350, 400 },
			new int[] { 35, 175, 500, 1100, 1300, 1500 },
			new int[] { 50, 200, 600, 1400, 1700, 2000 }
		);
	}
	public BlueProperty(String name) {
		this(nth++, name);
	}
	public BlueProperty(int nthProperty) {
		this(nthProperty, getDefaultName(nthProperty));
	}
	public BlueProperty() {
		this(nth++);
	}
	
	public static String getDefaultName(int nthProperty) {
		switch( nthProperty ) {
			case 0:
				return "Park Place";
			case 1:
				return "Boardwalk";
		}
		return null;
	}
}
