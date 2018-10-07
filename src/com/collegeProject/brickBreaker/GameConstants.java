package com.collegeProject.brickBreaker;


public interface GameConstants {
	int GAME_WIDTH= 700;
	int GAME_HEIGHT= 600;
	int BALL_WIDTH=20;
	int BALL_HEIGHT=20;
	int DELAY = 8;
	int ROWS=3;
	int COLUMNS=7;
	int TOTAL_BRICKS = ROWS * COLUMNS;
	int BRICK_WIDTH = 540/COLUMNS;
	int BRICK_HEIGHT=150/ROWS;
	String BREAK_SOUND = "break1.mp3" ;
	String GAMEOVER = "break1.mp3";
	String BGMUSIC = "bgmusic.mp3";
	String BACKGROUND = "background.png";
	String SPRITESHEET = "paddle-large.png";
	
}
