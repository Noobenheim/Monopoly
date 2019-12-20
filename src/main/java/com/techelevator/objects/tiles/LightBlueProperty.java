package com.techelevator.objects.tiles;

public class LightBlueProperty extends Property {
	private static int nth = 0;
	
	public LightBlueProperty(int nthProperty, String name) {
		super(
			new int[] { 6, 8, 9 },
			nthProperty,
			3,
			name,
			new int[] { 100, 120 },
			new int[] { 6, 30, 90, 270, 400, 550 },
			new int[] { 8, 40, 100, 300, 450, 600 }
		);
	}
	public LightBlueProperty(String name) {
		this(nth++, name);
	}
	public LightBlueProperty(int nthProperty) {
		this(nthProperty, getDefaultName(nthProperty));
	}
	public LightBlueProperty() {
		this(nth++);
	}

	public static String getDefaultName(int nthProperty) {
		switch( nthProperty ) {
			case 0:
				return "Oriental Avenue";
			case 1:
				return "Vermont Avenue";
			case 2:
				return "Connecticut Avenue";
		}
		return null;
	}
}
