package com.techelevator.objects.tiles;

public class GreenProperty extends Property {
	private static int nth = 0;
	
	public GreenProperty(int nthProperty, String name) {
		super(
			new int[] { 31, 32, 34 },
			nthProperty,
			3,
			name,
			new int[] { 300, 320 },
			new int[] { 26, 130, 390, 900, 1100, 1275 },
			new int[] { 28, 150, 450, 1000, 1200, 1400 }
		);
	}
	public GreenProperty(String name) {
		this(nth++, name);
	}
	public GreenProperty(int nthProperty) {
		this(nthProperty, getDefaultName(nthProperty));
	}
	public GreenProperty() {
		this(nth++);
	}
	
	public static String getDefaultName(int nthProperty) {
		switch( nthProperty ) {
			case 0:
				return "Pacific Avenue";
			case 1:
				return "North Carolina Avenue";
			case 2:
				return "Pennsylvania Avenue";
		}
		return null;
	}
}
