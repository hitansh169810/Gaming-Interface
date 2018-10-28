package com.collegeProject.spaceShooter;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display {
	private int width;
	private int height;
	private String title;
	public static JFrame frame;
	private Canvas canvas;
//	constructor
public Display(String title,int width,int height){
	this.title=title;
	this.width=width;
	this.height=height;
	createDisplay();
}

public void createDisplay() {
	
	frame=new JFrame(title);
	frame.setSize(width,height);
	frame.setVisible(true);//by default frame is false
	frame.setLocationRelativeTo(null);//so that it will not open at leftmostSide of screen
	frame.setResizable(false);//so our passed values do no get false;
	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//otherwise it will minimize on click 
	canvas=new Canvas();
	canvas.setPreferredSize(new Dimension(width,height));
	canvas.setBackground(Color.black);
	canvas.setFocusable(false);
	frame.add(canvas);
	frame.pack();
}
public Canvas getCanvas() {
	return canvas;
}
public void setCanvas(Canvas canvas) {
	this.canvas = canvas;
} 
}
