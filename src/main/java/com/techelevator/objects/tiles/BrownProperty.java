package com.techelevator.objects.tiles;

public class BrownProperty extends Property {
	private static int nth = 0;
	
	public BrownProperty(int nthProperty, String name) {
		super(
			new int[] { 1, 3 },
			nthProperty,
			2,
			name,
			new int[] { 60 },
			new int[] { 2, 10, 30, 90, 160, 250 },
			new int[] { 4, 20, 60, 180, 320, 450 }
		);
	}
	public BrownProperty(String name) {
		this(nth++, name);
	}
	public BrownProperty(int nthProperty) {
		this(nthProperty, getDefaultName(nthProperty));
	}
	public BrownProperty() {
		this(nth++);
	}
	
	public static String getDefaultName(int nthProperty) {
		switch( nthProperty ) {
			case 0:
				return "Mediterranean Avenue";
			case 1:
				return "Baltic Avenue";
		}
		return null;
	}
}
