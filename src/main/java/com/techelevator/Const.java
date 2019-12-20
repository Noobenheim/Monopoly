package com.techelevator;

import javafx.scene.image.Image;

public class Const {
	// Application
	public static final int WINDOW_WIDTH = 1024;
	public static final int WINDOW_HEIGHT = 768;
	
	// Board
	public static final Image BOARD_TEXTURE = new Image(Const.class.getResource("resources/monopoly_board.jpg").toString(), true);
	public static final int BOARD_WIDTH = 1600;
	public static final int BOARD_HEIGHT = 10;
	public static final int BOARD_DEPTH = 1600;
	
	// Camera
	public static final int CAMERA_START_ANGLE = -35;
	
	// Pieces
	public static final int PIECES_COUNT = 40;
}
