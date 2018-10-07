package com.collegeProject.spaceShooter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player implements KeyListener {
	private int x;
	private int y;
	private boolean left;
	private boolean right;
	private boolean fire;
	private long current;
	private long delay;
	private int health;
	
public Player(int x,int y) {
	this.x=x;
	this.y=y;
}
public void init() {
	Display.frame.addKeyListener(this);
current=System.nanoTime();
delay=100;
health=3;
}

public void tick() {
	if(!(health<=0 )) {
		if(left) {
		if(x>=50) {
x-=4;
}}
	if(right) {
		if(x<=1150-60)
	x+=4;}
	if(fire) {
		long breaks= (System.nanoTime()-current)/1000000 ; 
		if(breaks>delay) {
		gameManager.bullet.add(new Bullet(x+30,y));//+15 so that bullet will get fired from centre of player 
	}
		current=System.nanoTime() ; 
	}}}
public void render(Graphics g) {
	if(!(health<=0)) {
	g.setColor(Color.RED);
	g.drawImage(LoadImages.player,x,y,60,60,null );
	}
}
public void keyPressed(KeyEvent e) {
	int source=e.getKeyCode();
	if(source==KeyEvent.VK_LEFT ) {
		left=true; 
	}
	if(source==KeyEvent.VK_RIGHT ) {
		right=true; 
	}
	if(source==KeyEvent.VK_B) {
		fire=true; 
	}
}
public void keyReleased(KeyEvent e) {
	int source=e.getKeyCode();
	if(source==KeyEvent.VK_LEFT ) {
		left=false; 
	}
	if(source==KeyEvent.VK_RIGHT ) {
		right=false; 
	}
	if(source==KeyEvent.VK_B) {
		fire=false; 
	}
}
public void keyTyped(KeyEvent e) {
	
}
public int getX() {
	return x;
}
public int getY() {
	return y;
}
public int getHealth() {
	return health;
	
}
public void setHealth(int health) {
	this.health=health;	
}
}
