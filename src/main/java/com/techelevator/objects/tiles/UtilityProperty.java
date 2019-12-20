package com.techelevator.objects.tiles;

public class UtilityProperty extends Property {
	private static int nth = 0;
	
	public UtilityProperty(int nthProperty, String name) {
		super(
			new int[] { 12, 28 },
			nthProperty,
			2,
			name,
			new int[] { 150 },
			null,
			null
		);
	}
	public UtilityProperty(String name) {
		this(nth++, name);
	}
	public UtilityProperty(int nthProperty) {
		this(nthProperty, getDefaultName(nthProperty));
	}
	public UtilityProperty() {
		this(nth++);
	}
	
	public static String getDefaultName(int nthProperty) {
		switch( nthProperty ) {
			case 0:
				return "Electric Company";
			case 1:
				return "Water Works";
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
