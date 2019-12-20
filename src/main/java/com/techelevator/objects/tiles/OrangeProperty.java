package com.techelevator.objects.tiles;

public class OrangeProperty extends Property {
	private static int nth = 0;
	
	public OrangeProperty(int nthProperty, String name) {
		super(
			new int[] { 16, 18, 19 },
			nthProperty,
			3,
			name,
			new int[] { 180, 200 },
			new int[] { 14, 70, 200, 550, 750, 950 },
			new int[] { 16, 80, 220, 600, 800, 1000 }
		);
	}
	public OrangeProperty(String name) {
		this(nth++, name);
	}
	public OrangeProperty(int nthProperty) {
		this(nthProperty, getDefaultName(nthProperty));
	}
	public OrangeProperty() {
		this(nth++);
	}
	
	public static String getDefaultName(int nthProperty) {
		switch( nthProperty ) {
			case 0:
				return "St. Charles Place";
			case 1:
				return "Tennessee Avenue";
			case 2:
				return "New York Avenue";
		}
		return null;
	}
}
