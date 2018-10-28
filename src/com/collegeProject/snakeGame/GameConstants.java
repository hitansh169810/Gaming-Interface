package com.collegeProject.snakeGame;

public interface GameConstants {
	int PIXELSIZE = 25 ;
	public static final int GWIDTH = 1200;
	int GHEIGHT = 650 ;
	int DELAY = 75;
	int TOTAL_PIXELS = (GHEIGHT*GWIDTH) / (PIXELSIZE*PIXELSIZE ); 

	String TITLE = " - - - - - - -   Snake Game   - - - - - - - ";
	String BACKGROUND_SOUND = "music.mp3";
	String POP_SOUND = "sound.mp3";
	String BACKGROUND_IMAGE = "bg.jpg";
	String GAME_OVER = "gameover.mp3";
	String HEAD = "head.png";
	String FOOD = "food.png";
	String WALL = "wall.png";
	String BODY = "body.png";
	
}