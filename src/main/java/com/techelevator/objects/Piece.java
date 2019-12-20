package com.techelevator.objects;

import java.util.ArrayList;
import java.util.List;

import com.techelevator.Const;
import com.techelevator.objects.tiles.Property;
import com.techelevator.objects.tiles.Tile;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.BoundingBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

public class Piece extends Cylinder {
	private Translate position = new Translate();
	private int currentPosition = 0;
	private static double MOVE_HEIGHT = 100;
	private static double MOVE_DURATION_MILLIS = 400;
	
	public Piece(Color color) {
		super(30, 20);
		PhongMaterial material = new PhongMaterial();
		material.setDiffuseColor(color);
		this.setMaterial(material);
		this.getTransforms().addAll(position);
		
		setTranslateY(379 - this.getHeight()/2);
		
		goToProperty(0);
	}
	
	public void goToProperty( int index ) {
		double x, z;
		
		this.currentPosition = index%Const.PIECES_COUNT;
		
		BoundingBox box = Property.getBoundingBoxForProperty( index, true );
		
		x = box.getWidth()/2 + box.getMinX();
		z = box.getHeight()/2 + box.getMinY();
		
		position.setX(x);
		position.setZ(z);
		
		this.currentPosition = index%Const.PIECES_COUNT;
	}
	
	private boolean isMoving = false;
	private int additional = 0;
	public void moveToProperty( int index, CameraControl follow, Tile[] tiles ) {
		if( index < currentPosition ) {
			index += Const.PIECES_COUNT;
		}
		if( isMoving ) {
			if( index > additional ) {
				additional = index;
			}
			return;
		}
		isMoving = true;
		int iterations = index - currentPosition;
		List<KeyFrame> frames = new ArrayList<>();

		final double originalCameraX = follow.getX();
		final double originalCameraY = follow.getY();
		final double originalCameraZ = follow.getZ();
		final int originalCameraRotations;
		
		int testX = -Const.WINDOW_WIDTH/2;
		int testZ = Const.WINDOW_HEIGHT/2;
		
		System.out.println(follow.getYRotation());
		
		double angle = follow.getYRotation()%360;
		
		if( angle >= 360 ) {
			originalCameraRotations = 0;
		} else if( angle >= 270 ) {
			follow.rotate90(1);
			originalCameraRotations = -1;
		} else if( angle >= 180 ) {
			follow.rotate90(2);
			originalCameraRotations = 2;
		} else if( angle >= 90 ){
			follow.rotate90(1, false, true);
			originalCameraRotations = 1;
		} else {
			originalCameraRotations = 0;
		}
		
		frames.add(new KeyFrame(
			Duration.seconds(0),
			new KeyValue(position.xProperty(), position.getX()),
			new KeyValue(position.yProperty(), position.getY()),
			new KeyValue(position.zProperty(), position.getZ()),
			new KeyValue(follow.getXProperty(), follow.getX()),
			new KeyValue(follow.getYProperty(), follow.getY()),
			new KeyValue(follow.getZProperty(), follow.getZ())
		));
		
		// zoom camera in before moving
		BoundingBox currentBox = Property.getBoundingBoxForProperty(currentPosition, true);
		double x = currentBox.getMinX() + currentBox.getWidth()/2 + testX;
		double z = currentBox.getMinY() + currentBox.getHeight()/2 + testZ;
		frames.add(new KeyFrame(
			Duration.millis(MOVE_DURATION_MILLIS),
			new KeyValue(position.xProperty(), position.getX()),
			new KeyValue(position.yProperty(), position.getY()),
			new KeyValue(position.zProperty(), position.getZ()),
			new KeyValue(follow.getXProperty(), x),
			new KeyValue(follow.getYProperty(), CameraControl.MAX_ZOOM),
			new KeyValue(follow.getZProperty(), z)
		));
		
		int i;
		for( i=1; i<=iterations; i++ ) {
			BoundingBox propBox = Property.getBoundingBoxForProperty(currentPosition+i, true);
			x = propBox.getMinX() + propBox.getWidth()/2;
			z = propBox.getMinY() + propBox.getHeight()/2;
			
			final int passedIndex = currentPosition+i;
			
			EventHandler<ActionEvent> passEvent = e->{
				tiles[passedIndex%Const.PIECES_COUNT].onPass(this);
			};
			
			frames.add(new KeyFrame(
				Duration.millis(MOVE_DURATION_MILLIS*(i) + MOVE_DURATION_MILLIS/2),
				new KeyValue(position.yProperty(), position.getY()-MOVE_HEIGHT)
			));
			frames.add(new KeyFrame(
				Duration.millis(MOVE_DURATION_MILLIS*(i+1)),
				passEvent,
				new KeyValue(position.xProperty(), x),
				new KeyValue(position.yProperty(), position.getY()),
				new KeyValue(position.zProperty(), z),
				new KeyValue(follow.getXProperty(), x + testX),
				new KeyValue(follow.getYProperty(), CameraControl.MAX_ZOOM),
				new KeyValue(follow.getZProperty(), z + testZ)
			));
		}
		
		frames.add(new KeyFrame(
				Duration.millis(MOVE_DURATION_MILLIS*(i+1)),
				e->{
					if( originalCameraRotations != 0 )
						follow.rotate90(originalCameraRotations<0?originalCameraRotations*-1:originalCameraRotations, false, originalCameraRotations<0);
				},
				new KeyValue(follow.getXProperty(), originalCameraX),
				new KeyValue(follow.getYProperty(), originalCameraY),
				new KeyValue(follow.getZProperty(), originalCameraZ)
		));
		
		Timeline tl = new Timeline(frames.toArray(new KeyFrame[frames.size()]));
		final int landedIndex = index%Const.PIECES_COUNT;
		tl.setOnFinished(e->{
			isMoving = false;
			if( additional > 0 ) {
				moveToProperty(additional, follow, tiles);
				additional = 0;
			} else {
				tiles[landedIndex].onLandOn(this, tiles);
			}
		});
		tl.play();
		
		currentPosition = index%Const.PIECES_COUNT;
	}
	
	public void moveBy(int number, CameraControl follow, Tile[] tiles) {
		moveToProperty(this.currentPosition+number, follow, tiles);
	}
	
	public int getCurrentPosition() {
		return this.currentPosition;
	}
}
