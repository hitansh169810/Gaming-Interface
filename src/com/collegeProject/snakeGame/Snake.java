package com.collegeProject.snakeGame;
public class Snake implements GameConstants{

	private final int[] x = new int[TOTAL_PIXELS] ;
	private final int[] y = new int[TOTAL_PIXELS] ;
	
	private boolean movingLeft = false ;
	private boolean movingRight = false ;
	private boolean movingUp = false ;
	private boolean movingDown = false ;	
	private int joints = 0 ;
	public int speed = 0 ;
	public int getSnakeX(int index) {
	    return x[index];
	}

	public int getSnakeY(int index) {
	    return y[index];
	}

	public void setSnakeX(int i) {
	    x[0] = i;
	}

	public void setSnakeY(int i) {
	    y[0] = i;
	}	
	
	public boolean isMovingLeft() {
		return movingLeft;
	}

	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}

	public boolean isMovingRight() {
		return movingRight;
	}

	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}

	public boolean isMovingUp() {
		return movingUp;
	}

	public void setMovingUp(boolean movingUp) {
		this.movingUp = movingUp;
	}

	public boolean isMovingDown() {
		return movingDown;
	}

	public void setMovingDown(boolean movingDown) {
		this.movingDown = movingDown;
	}

	public int getJoints() {
		return joints;
	}

	public void setJoints(int joints) {
		this.joints = joints;
	}
	
	public void move() {
		for(int i= joints ; i>0 ; i--) {
			x[i] = x[(i-1)] ;
			y[i] = y[(i-1)] ;
		}
		
		  if (movingLeft) {
		        x[0] -= PIXELSIZE;
		    }
		    // To the right
		    if (movingRight) {
		        x[0] += PIXELSIZE;
		    }
		    // Down
		    if (movingDown) {
		        y[0] += PIXELSIZE;
		    }
		    // And finally up
		    if (movingUp) {
		        y[0] -= PIXELSIZE;
		    }
	}
}
