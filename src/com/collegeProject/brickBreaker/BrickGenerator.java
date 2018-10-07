package com.collegeProject.brickBreaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class BrickGenerator implements GameConstants {
int brick[][];
	//initializing values to brick
	public BrickGenerator(int row ,int col){
		brick = new int[row][col]; 	//new 2d array is created
		for(int i=0 ;i<row; i++) {
			System.out.println(brick.length);
			for(int j=0 ;j<col; j++) { 
				System.out.println(brick[0].length);//brick[0] is the 1st brick
				brick[i][j]=1; 	//storing 1 will ensure that ball is not intersecting with brick
								//storing 0 will ensure that ball is collided with brick & not shown on panel
				
			}
		}
	}
//	public void draw(Graphics g) {
//		int number = 5;
//	    int count = number - 1;
//	    for (int k = 1; k <= number; k++) {
//	        for (int i = 1; i <= count; i++)
//	            System.out.print(" ");
//	        count--;
//	        for (int i = 1; i <= 2 * k - 1; i++)
//	            System.out.print("*");
//	        System.out.println();
//	    }
//	    count = 1;
//	    for (int k = 1; k <= number - 1; k++) {
//	        for (int i = 1; i <= count; i++)
//	            System.out.print(" ");
//	        count++;
//	        for (int i = 1; i <= 2 * (number - k) - 1; i++)
//	            System.out.print("*");
//	        System.out.println();
//	    }
//	}
	
	
	//Drawing of Bricks
	public void draw(Graphics2D g) {
		for(int i=0 ;i<brick.length; i++) { //iterating rows
			for(int j=0 ;j<brick[0].length; j++) { //iterating col
				if(brick[i][j]>0) {
					g.setColor(Color.WHITE);
					g.fillRect(j*BRICK_WIDTH + 80, i*BRICK_HEIGHT +50, BRICK_WIDTH, BRICK_HEIGHT);
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.BLACK);
					g.drawRect(j*BRICK_WIDTH + 80, i*BRICK_HEIGHT +50, BRICK_WIDTH, BRICK_HEIGHT);
				}
			}
				
		}
	}
	
	public void setBrickValue(int value ,int row , int col) {
		brick[row][col] = value;
	}
}
