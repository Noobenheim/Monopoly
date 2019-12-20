package com.techelevator.objects;

import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.DrawMode;

// GO corner 800,1312 to 1102,1010
// tile width: 131

public class Board extends Box {
	public static final int BOARD_CORNER_X = 1312;
	public static final int BOARD_CORNER_Z = 800;
	public static final int CORNER_TILE_WIDTH = 210;
	public static final int TILE_WIDTH = 131;
	public static final int TILE_HEIGHT = CORNER_TILE_WIDTH;
	public static final int PROPERTY_TITLE_WIDTH = 28;
	
	public Board(int width, int height, int depth, Image texture) {
		super(width, height, depth);

		setDrawMode(DrawMode.FILL);
		PhongMaterial material = new PhongMaterial();
		material.setDiffuseMap(texture);
		setMaterial(material);
	}
}
