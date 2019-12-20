package com.techelevator.objects;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.PerspectiveCamera;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.shape.Shape3D;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

@SuppressWarnings("rawtypes")
public class CameraControl extends PerspectiveCamera implements EventHandler {
	private Translate pivot = new Translate();
	private Rotate yRotate = new Rotate(0, Rotate.Y_AXIS);
	private Rotate xRotate = new Rotate(0, Rotate.X_AXIS);
	private Translate zoom = new Translate();
	private boolean isMoving = false;
	
	public final static int MAX_ZOOM = 1250;
	public final static int MIN_ZOOM = 80;
	
	public final static int MIN_PAN = -600;
	public final static int MAX_PAN = 600;
	
	public CameraControl() {
		super(false);
		this.getTransforms().addAll(pivot, yRotate, xRotate, zoom);
	}
	
	public void translate(double x, double y, double z) {
		transform(new Translate(x, y, z));
	}
	public void rotate(double angle, Point3D axis) {
		transform(new Rotate(angle, axis));
	}
	public double getXRotation() {
		return xRotate.getAngle();
	}
	public double getYRotation() {
		return yRotate.getAngle();
	}
	public void transform(Transform transform) {
		this.getTransforms().add(transform);
	}
	public void setPivot(double x, double y, double z) {
		pivot.setX(x);
		pivot.setY(y);
		pivot.setZ(z);
	}
	public void setPivot(Shape3D shape) {
		pivot.setX(shape.getTranslateX());
		pivot.setY(shape.getTranslateY());
		pivot.setZ(shape.getTranslateZ());
	}
	public void setYRotate(double angle) {
		yRotate.setAngle(angle);
	}
	public void setXRotate(double angle) {
		xRotate.setAngle(angle);
	}
	public void moveForward(double amount) {
		zoomAnimation(0, 0, amount);
	}
	public void moveBackward(double amount) {
		zoomAnimation(0, 0, -amount);		
	}
	public void moveUp(double amount) {
		zoomAnimation(0, -amount, 0);
	}
	public void moveDown(double amount) {
		zoomAnimation(0, amount, 0);
	}
	public void moveLeft(double amount) {
		zoomAnimation(-amount, 0, 0);
	}
	public void moveRight(double amount) {
		zoomAnimation(amount, 0, 0);
	}
	private void zoomAnimation(double xDiff, double yDiff, double zDiff) {
		Timeline timeline = new Timeline(
			new KeyFrame(
				Duration.seconds(0),
				new KeyValue(zoom.xProperty(), zoom.getX()),
				new KeyValue(zoom.yProperty(), zoom.getY()),
				new KeyValue(zoom.zProperty(), zoom.getZ())
			),
			new KeyFrame(
				Duration.millis(100),
				new KeyValue(zoom.xProperty(), zoom.getX()+xDiff),
				new KeyValue(zoom.yProperty(), zoom.getY()+yDiff),
				new KeyValue(zoom.zProperty(), zoom.getZ()+zDiff)
			)
		);
		timeline.play();
	}

	private void handleClick(MouseEvent event) {
//		if( event.getClickCount() >= 2 )
//			rotate90(true);
//		else
			rotate90(false);
	}
	
	public void rotate90(int count) {
		rotate90(count, false, false);
	}
	public void rotate90(boolean turnCorner) {
		rotate90(1, turnCorner, false);
	}
	public void rotate90(int count, boolean turnCorner, boolean reverse) {
		if( !isMoving ) {
			double panStart = zoom.getX();
			double panEnd = zoom.getX();
			
			if( turnCorner ) {
				panEnd = MAX_PAN;
			}
			isMoving = true;
			List<KeyFrame> frames = new ArrayList<>();
			frames.add(new KeyFrame(
				Duration.seconds(0),
				new KeyValue(yRotate.angleProperty(), yRotate.getAngle()),
				new KeyValue(zoom.xProperty(), panStart)
			));
			int modifier = reverse? -1 : 1;
			int i=0;
			for( i=0; i<count-1; i++ ) {
				frames.add(new KeyFrame(
					Duration.millis(250*(i+1)),
					new KeyValue(yRotate.angleProperty(), yRotate.getAngle()+ 90*(i+1) * modifier)
				));
			}
			frames.add(new KeyFrame(
				Duration.millis(250*(i+1)),
				new KeyValue(yRotate.angleProperty(), yRotate.getAngle()+ 90*(i+1) * modifier),
				new KeyValue(zoom.xProperty(), panEnd)
			));
			Timeline timeline = new Timeline(
				frames.toArray(new KeyFrame[frames.size()])
			);
			timeline.setOnFinished((ActionEvent e)->{
				isMoving = false;
			});
			timeline.play();
		}	
	}
	
	private void handleScroll(ScrollEvent event) {
		if( event.getDeltaY() != 0 ) { // zoom out
			double zoomZ = Math.min(zoom.getZ()+event.getDeltaY(), MAX_ZOOM);
			double zoomY = Math.min(zoom.getY()+event.getDeltaY(), MAX_ZOOM);
			
			zoomZ = Math.max(zoomZ, MIN_ZOOM);
			zoomY = Math.max(zoomY, MIN_ZOOM);
			
			zoom.setZ(zoomZ);
			zoom.setY(zoomY);
		}
		if( event.getDeltaX() != 0 ) {
			double zoomX = Math.min(zoom.getX() + event.getDeltaX(), MAX_PAN);
			zoomX = Math.max(zoomX, MIN_PAN);
			
			zoom.setX(zoomX);
		}
	}
	
	public DoubleProperty getXProperty() {
		return zoom.xProperty();
	}
	public DoubleProperty getYProperty() {
		return zoom.yProperty();
	}
	public DoubleProperty getZProperty() {
		return zoom.zProperty();
	}
	public double getX() {
		return zoom.getX();
	}
	public double getY() {
		return zoom.getY();
	}
	public double getZ() {
		return zoom.getZ();
	}
	public void setX(double x) {
		zoom.setX(x);
	}
	public void setY(double y) {
		zoom.setY(y);
	}
	public void setZ(double z) {
		zoom.setZ(z);
	}

	@Override
	public void handle(Event event) {
		if( event instanceof MouseEvent ) {
			handleClick((MouseEvent)event);
		} else if( event instanceof ScrollEvent ) {
			handleScroll((ScrollEvent)event);
		}
	}
}
