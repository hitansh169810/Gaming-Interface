package com.collegeProject.snakeGame;

public interface GameConstants {
	int PIXELSIZE = 25 ;
	public static final int GWIDTH = 1200;
	int GHEIGHT = 650 ;
	String TITLE = " - - - - - - -   Snake Game   - - - - - - - ";
//	int DELAY = 70;
//	int DELAY = 80;
	
	int TOTAL_PIXELS = (GHEIGHT*GWIDTH) / (PIXELSIZE*PIXELSIZE ); 
	String BACKGROUND_SOUND = "music.mp3";
	String POP_SOUND = "sound.mp3";
	String BACKGROUND_IMAGE = "bg.gif";
	String GAME_OVER = "gameover.mp3";
	String HEAD = "head.png";
	String FOOD = "food.gif";
	String WALL = "wall.png";
	String BODY = "body.png";
	
}