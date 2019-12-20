package com.techelevator.objects.tiles;

public class YellowProperty extends Property {
	private static int nth = 0;
	
	public YellowProperty(int nthProperty, String name) {
		super(
			new int[] { 26, 27, 29 },
			nthProperty,
			3,
			name,
			new int[] { 260, 280 },
			new int[] { 22, 110, 330, 800, 975, 1150 },
			new int[] { 24, 120, 360, 850, 1025, 1200 }
		);
	}
	public YellowProperty(String name) {
		this(nth++, name);
	}
	public YellowProperty(int nthProperty) {
		this(nthProperty, getDefaultName(nthProperty));
	}
	public YellowProperty() {
		this(nth++);
	}
	
	public static String getDefaultName(int nthProperty) {
		switch( nthProperty ) {
			case 0:
				return "Atlantic Avenue";
			case 1:
				return "Ventnor Avenue";
			case 2:
				return "Marvin Gardens";
		}
		return null;
	}
}
