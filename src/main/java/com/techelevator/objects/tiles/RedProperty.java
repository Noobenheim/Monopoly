package com.techelevator.objects.tiles;

public class RedProperty extends Property {
	private static int nth = 0;
	
	public RedProperty(int nthProperty, String name) {
		super(
			new int[] { 21, 23, 24 },
			nthProperty,
			3,
			name,
			new int[] { 220, 240 },
			new int[] { 18, 90, 250, 700, 875, 1050 },
			new int[] { 20, 100, 300, 750, 925, 1100 }
		);
	}
	public RedProperty(String name) {
		this(nth++, name);
	}
	public RedProperty(int nthProperty) {
		this(nthProperty, getDefaultName(nthProperty));
	}
	public RedProperty() {
		this(nth++);
	}
	
	public static String getDefaultName(int nthProperty) {
		switch( nthProperty ) {
			case 0:
				return "Kentucky Avenue";
			case 1:
				return "Indiana Avenue";
			case 2:
				return "Illinois Avenue";
		}
		return null;
	}
}
