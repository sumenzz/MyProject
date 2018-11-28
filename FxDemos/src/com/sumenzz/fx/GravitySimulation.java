package com.sumenzz.fx;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GravitySimulation extends Application {

	private Pane root = new Pane();
	private RectangleRb box1 = new RectangleRb(130, 8, 40, 40, Color.BLUE);
	private CircleRb ball = new CircleRb(100, 0, 40, Color.RED);
	private static double gravity = 1;
	private static double distance = 1;
	private static double friction = 5;
	private static double maxdistance = 300 ;
	
	
	   private Parent createContent() {
	    
		   root.setPrefSize(300, 300);
           root.getChildren().add(box1);

           AnimationTimer timer = new AnimationTimer() {
	            @Override
	            public void handle(long now) {
	                update();
	              
	                
	                
	            }
	        };

	        timer.start();
	        return root;
	    }
	
	 public static void main(String[] args) {
	        launch(args);
	    }


	@Override
	public void start(Stage stage) throws Exception {

		Scene scene = new Scene(createContent());
	        stage.setScene(scene);
	        stage.show();

		
	}
	
	 
	
	private void update () {
		move(box1);
	}
	
	private double newPosition(Rigidbody rb) {
		
		double newPosY = 0;
		
		distance = distance + gravity;
//		System.out.println(distance + "," + rb.getBoundary());

		if ( root.getHeight() - rb.getBoundary() < 0   ) {
			
	    		distance = distance - friction;
				distance = -distance;
				maxdistance = Math.abs(distance);
				
		//		System.out.println("hit :" + distance + "," + friction + " , "+rb.getBoundary());
		 
		} 
		
		
		
		if (maxdistance < friction || rb.getBoundary() > 300) {
			 	newPosY = root.getHeight() - rb.getDistanceFromCenter() ;
			} else	{
				newPosY = rb.getPosY() + distance ;
			}
		
		return newPosY;
		
	}
	
	
	void move(Rigidbody rb) {
		rb.setPosY(newPosition(rb));
	}
	
	
}
