package com.techelevator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.techelevator.objects.Board;
import com.techelevator.objects.CameraControl;
import com.techelevator.objects.Piece;
import com.techelevator.objects.tiles.*;

import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape3D;
import javafx.scene.transform.Rotate;

public class Setup {
	private Setup() {}
	
	public static void setupBoard(Image texture, Scene scene, Group gameGroup, Map<String,Object> objects) {
		Board board = new Board(
			Const.BOARD_WIDTH,
			Const.BOARD_HEIGHT,
			Const.BOARD_DEPTH,
			texture
		);
		board.setTranslateX(scene.getWidth()/2);
		board.setTranslateY(scene.getHeight()/2);
		board.setTranslateZ(Const.BOARD_WIDTH);
		gameGroup.getChildren().add(board);
		
		objects.put("board", board);
	}
	
	public static void setupLight(Group gameGroup) {
		 AmbientLight light = new AmbientLight();
		 light.setColor(Color.WHITE);
		 
		 gameGroup.getChildren().add(light);
	}
	
	@SuppressWarnings("unchecked")
	public static void setupCamera(CameraControl camera, Scene scene, Map<String,Object> objects) {
		camera.setPivot((Shape3D)objects.get("board"));
		camera.rotate(Const.CAMERA_START_ANGLE, Rotate.X_AXIS);
		camera.translate(-1 * scene.getWidth()/2, -1 * scene.getHeight()/2, -Const.BOARD_DEPTH);

	    scene.setCamera(camera);
		scene.setOnMouseClicked(camera);
		scene.setOnScroll(camera);
	}
	
	public static void setupPieces(Map<String,Object> objects) {
		List<Piece> pieces = new ArrayList<>();
		Color[] color = new Color[] { Color.BLACK, Color.WHITE, Color.ORANGERED, Color.DARKORANGE, Color.YELLOW, Color.GREEN, Color.CORNFLOWERBLUE, Color.INDIGO, Color.DARKVIOLET };
		
		for( Color c : color ) {
			Piece p = new Piece(c);
			pieces.add(p);
		}
		
		objects.put("allPieces", pieces.toArray(new Piece[pieces.size()]));
	}
	
	public static Tile[] setupTiles() {
		List<Tile> tiles = new ArrayList<>();
		
		tiles.add(new GoTile());
		tiles.add(new BrownProperty());
		tiles.add(new CommunityChestTile());
		tiles.add(new BrownProperty());
		tiles.add(new TaxTile());
		tiles.add(new RailroadProperty());
		tiles.add(new LightBlueProperty());
		tiles.add(new ChanceTile());
		tiles.add(new LightBlueProperty());
		tiles.add(new LightBlueProperty());
		tiles.add(new JailTile());
		tiles.add(new PurpleProperty());
		tiles.add(new UtilityProperty());
		tiles.add(new PurpleProperty());
		tiles.add(new PurpleProperty());
		tiles.add(new RailroadProperty());
		tiles.add(new OrangeProperty());
		tiles.add(new CommunityChestTile());
		tiles.add(new OrangeProperty());
		tiles.add(new OrangeProperty());
		tiles.add(new FreeParkingTile());
		tiles.add(new RedProperty());
		tiles.add(new ChanceTile());
		tiles.add(new RedProperty());
		tiles.add(new RedProperty());
		tiles.add(new RailroadProperty());
		tiles.add(new YellowProperty());
		tiles.add(new YellowProperty());
		tiles.add(new UtilityProperty());
		tiles.add(new YellowProperty());
		tiles.add(new GoToJailTile());
		tiles.add(new GreenProperty());
		tiles.add(new GreenProperty());
		tiles.add(new CommunityChestTile());
		tiles.add(new GreenProperty());
		tiles.add(new RailroadProperty());
		tiles.add(new ChanceTile());
		tiles.add(new BlueProperty());
		tiles.add(new TaxTile());
		tiles.add(new BlueProperty());
		
		return tiles.toArray(new Tile[tiles.size()]);
	}
}
