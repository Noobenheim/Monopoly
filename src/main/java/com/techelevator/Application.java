package com.techelevator;

import java.util.HashMap;
import java.util.Map;

import com.techelevator.objects.CameraControl;
import com.techelevator.objects.Piece;
import com.techelevator.objects.tiles.ChanceTile;
import com.techelevator.objects.tiles.CommunityChestTile;
import com.techelevator.objects.tiles.FreeParkingTile;
import com.techelevator.objects.tiles.GoToJailTile;
import com.techelevator.objects.tiles.GoTile;
import com.techelevator.objects.tiles.JailTile;
import com.techelevator.objects.tiles.Property;
import com.techelevator.objects.tiles.TaxTile;
import com.techelevator.objects.tiles.Tile;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {
	private Scene scene;
	private Group gameGroup;
	private final CameraControl camera = new CameraControl();
	
	Map<String,Object> objects = new HashMap<>();

	public static void main(String[] args) {
		launch();
	}
	
	@Override
	public void start(Stage primaryStage) {
		scene = primaryStage.getScene();
		
		TurnBasedGame game = new TurnBasedGame() {
			@Override
			public void setup() {
				gameGroup = new Group();
				scene = new Scene(gameGroup, Const.WINDOW_WIDTH, Const.WINDOW_HEIGHT, true, SceneAntialiasing.BALANCED);
				primaryStage.setScene(scene);
				
				Setup.setupPieces(objects);
				
				objects.put("tiles", Setup.setupTiles());
				
				Piece[] pieces = (Piece[])objects.get("allPieces");
				Piece tester = pieces[6];
				gameGroup.getChildren().add(tester);

				Setup.setupBoard(Const.BOARD_TEXTURE, scene, gameGroup, objects);
				Setup.setupLight(gameGroup);
				Setup.setupCamera(camera, scene, objects);

				Tile[] tiles = ((Tile[])objects.get("tiles"));
				
				scene.setOnKeyPressed(e->{
					KeyCode code = e.getCode();
					
					double moveAmount = 50;
					
					int orig = tester.getCurrentPosition();
					
					switch( code ) {
						case W:
							camera.moveForward(moveAmount);
							break;
						case A:
							camera.moveLeft(moveAmount);
							break;
						case S:
							camera.moveBackward(moveAmount);
							break;
						case D:
							camera.moveRight(moveAmount);
							break;
						case Q:
							camera.moveDown(moveAmount);
							break;
						case E:
							camera.moveUp(moveAmount);
							break;
						case ESCAPE:
							System.exit(0);
						case DIGIT1:
							tester.moveBy(1, camera, tiles);
							break;
						case DIGIT2:
							tester.moveBy(2, camera, tiles);
							break;
						case DIGIT3:
							tester.moveBy(3, camera, tiles);
							break;
						case DIGIT4:
							tester.moveBy(4, camera, tiles);
							break;
						case DIGIT5:
							tester.moveBy(5, camera, tiles);
							break;
						default:
							break;
					}
					
					if( orig != tester.getCurrentPosition() ) {
						Tile current = tiles[tester.getCurrentPosition()];
						String name;
						int price;
						int rent;
						if( current instanceof Property ) {
							name = ((Property)current).getName();
							price = ((Property)current).getCost();
							rent = ((Property)current).getRent();
						} else if( current instanceof CommunityChestTile ) {
							name = "Community Chest";
							price = rent = -1;
						} else if( current instanceof ChanceTile ) {
							name = "Chance";
							price = rent = -1;
						} else if( current instanceof GoTile ) {
							name = "GO";
							price = rent = -1;
						} else if( current instanceof JailTile ) {
							name = "Jail";
							price = rent = -1;
						} else if( current instanceof FreeParkingTile ) {
							name = "Free Parking";
							price = rent = -1;
						} else if( current instanceof GoToJailTile ) {
							name = "Go To JAIL!";
							price = rent = -1;
						} else if( current instanceof TaxTile ) {
							name = "Tax";
							price = rent = -1;
						} else {
							name = "UNKNOWN: "+current.getClass().toString();
							price = rent = -1;
						}
						
						System.out.format("Landed on %s (%d price, %d rent)\n", name, price, rent);
					}
				});
				
				/*
				final Robot robot;
				try {
					robot = new Robot();
					
					double screenCenterX = Const.WINDOW_WIDTH/2;
					double screenCenterY = Const.WINDOW_HEIGHT/2;
					scene.setOnMouseMoved(e->{
						double x = e.getScreenX();
						double y = e.getScreenY();
						double dX = x-screenCenterX;
						double dY = y-screenCenterY;
						
						double sensitivity = 10;
						
						if( x != screenCenterX ) {
							camera.setYRotate(camera.getYRotation()+(dX/sensitivity));
							robot.mouseMove((int)screenCenterX, (int)screenCenterY);
						}
						if( y != screenCenterY ) {
							camera.setXRotate(camera.getXRotation()+(dY/sensitivity));
							robot.mouseMove((int)screenCenterX, (int)screenCenterY);
						}
					});
					
					robot.mouseMove((int)screenCenterX, (int)screenCenterY);
				} catch (AWTException e1) {
					e1.printStackTrace();
				}
				*/
			}

			@Override
			public void takeTurn(int playerNumber) {
				
			}

			@Override
			public void finished() {
				
			}

			@Override
			public void startGame() {
				
			}
		};
		primaryStage.show();
		game.start();
	}
}
