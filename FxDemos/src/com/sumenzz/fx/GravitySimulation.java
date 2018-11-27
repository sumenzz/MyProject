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
	private Box box1 = new Box(300, 0, 40, 40, Color.BLUE);
	private static double gravity = .2;
	private static double distance = 1;
	
	   private Parent createContent() {
	        root.setPrefSize(500, 500);

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
		distance =+ distance + gravity;
		
		if (rb.getPosY() > 430) {			
		 distance = -distance;
		}
		newPosY = rb.getPosY() + distance ;
		
		return newPosY;
		
	}
	
	
	void move(Rigidbody rb) {
		rb.setPosY(newPosition(rb));
		
		
	}
	
	
	private static class Box extends Rectangle implements  Rigidbody {
        
        
		private int mass;
		
		Box(int x, int y, int w, int h, Color color) {
            super(w, h, color);
            setTranslateX(x);
            setTranslateY(y);
        }

		@Override
		public void setMass(int mass) {
			this.mass = mass ;
			
		}

		@Override
		public int getMass() {
			// 
			return this.mass;
		}

		@Override
		public void setPosY(double i) {
			this.setTranslateY(i);
			
		}

		@Override
		public double getPosY() {
			return this.getTranslateY();
		}

		@Override
		public double getPosX() {
			return this.getTranslateX();
		}

		@Override
		public void setPosX(double i) {
			this.setTranslateX(i);
			
		}

		
       
    }
	
	
	public interface Rigidbody {
	
		void setMass(int mass);
		public int getMass();
		double getPosY();
		void setPosY(double i);
		double getPosX();
		void setPosX(double i);
		
	}
}
