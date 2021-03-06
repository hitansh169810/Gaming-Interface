package com.collegeProject.brickBreaker;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import jaco.mp3.player.MP3Player;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


//panel
public class GamePanel extends JPanel implements KeyListener , ActionListener, GameConstants {
	
	
	private boolean play = false;
	private int score = 0;
	private int bricksRemain=TOTAL_BRICKS;
	private boolean GameWin=false;
	private boolean left = true;
	private boolean right = true;
	private boolean firsttime=true;
	
	private Timer timer;
	
	private int paddleX = 340;
	private int paddleY = 530;
	
	MP3Player break_sound ;
	MP3Player game_over;
	MP3Player bgmusic ;
	
	 Image bgImage;
	 BufferedImage spritesheet;
	BufferedImage paddleimg ;
	
	
	//starting pos
	int ballpos_X = 120;
	 int ballpos_Y = 350;
	 //speed
	int balldir_X = -1;
	 int balldir_Y = -3;
	 
	 private BrickGenerator bricks;
	
	public GamePanel(){
		bricks=new BrickGenerator(ROWS , COLUMNS);
		break_sound = new MP3Player(GamePanel.class.getResource(BREAK_SOUND)) ;
		bgmusic = new MP3Player(GamePanel.class.getResource(BGMUSIC)) ;
		game_over = new MP3Player(GamePanel.class.getResource(GAMEOVER)) ;
		bgImage = new ImageIcon(GamePanel.class.getResource(BACKGROUND)).getImage();
		bgmusic.play();

		try{
			spritesheet = ImageIO.read(GamePanel.class.getResource(SPRITESHEET));
			}
			catch(Exception e){
			System.out.println("Problem in Player Sprite Sheet Loading"+e);
			}
		
		paddleimg = spritesheet.getSubimage(15, 302, 140-15, 336-302);
		setFocusable(true);
		addKeyListener(this);
		setFocusTraversalKeysEnabled(false);
		
		timer = new Timer(DELAY , this);
		timer.start();
	}
	
	
	public void gameOverSound() {
		game_over.play();
	}
	
	public void playSound() {
		
		break_sound.play();
	}
//	public void backgroundMusic() {
//		bgmusic.play();
//	}
	public void drawPaddle(Graphics g) {
		g.drawImage(paddleimg, paddleX, paddleY, 140-15, 336-302,null);
	}
	public void drawBackground(Graphics g) {
		g.drawImage(bgImage,0,0,GAME_WIDTH,GAME_HEIGHT,null);
		
	}
	
	 
	public void paint(Graphics g) {
		super.paintComponent(g);
		//BACKGROUND
		drawBackground(g);
		//g.setColor(Color.black);
		//g.fillRect(1, 1, GAME_WIDTH-8, GAME_HEIGHT-8);
		
		//Drawing Bricks
		bricks.draw((Graphics2D)g);
		
		//Starting of game
		if(firsttime) {
			g.setColor(Color.GREEN);
			g.setFont(new Font("serif", Font.BOLD,30));
			g.drawString("Press Left Or Right Key To Play", 130, 300);
		}
		
		//border
		//g.setColor(Color.yellow);
		//g.fillRect(0, 0, 3, 592);
		//g.fillRect(0, 0, GAME_WIDTH-8, 3);
		//g.fillRect(GAME_WIDTH-18, 0, 3, GAME_HEIGHT-8);
		
		//score show
		g.setColor(Color.WHITE);
		g.setFont(new Font("serif", Font.BOLD,25));
		g.drawString(""+score, 590, 30);
		
		//PADDLE--> width=100 , height=8
		drawPaddle(g);
		//g.setColor(Color.GREEN);
		//g.fillRect(paddleX, 550, 100, 8);
		
		//ball
		g.setColor(Color.YELLOW);
		g.fillOval(ballpos_X, ballpos_Y, BALL_WIDTH, BALL_HEIGHT);
		
		//Game Win
		if(GameWin) {
			firsttime =false;
			left=false;
			right=false;
			play=false;
			balldir_X=0;
			balldir_Y=0;
			bricksRemain=TOTAL_BRICKS;
			g.setColor(Color.RED);
			g.setFont(new Font("serif", Font.BOLD,30));
			g.drawString("You Won , Score:"+score, 200, 280);
			
			g.setFont(new Font("serif", Font.BOLD,20));
			g.drawString("Press Enter To Restart", 220, 310);
			
		}
		
		//Game Over
		if(ballpos_Y>570) {
			if(play) {
				game_over.play();
			}
			firsttime = false;
			left=false;
			right=false;
			play=false;
			balldir_X=0;
			balldir_Y=0;
			g.setColor(Color.RED);
			g.setFont(new Font("serif", Font.BOLD,30));
			g.drawString("Game Over , Score:"+score, 190, 280);
			
			g.setFont(new Font("serif", Font.BOLD,20));
			g.drawString("Press Enter To Restart", 210, 310);
			bgmusic.stop();

		}
		//g.dispose();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		
		if(play) {
			//detecting intersection of paddle and ball
			if(new Rectangle(ballpos_X , ballpos_Y , BALL_WIDTH,BALL_HEIGHT).intersects(new Rectangle(paddleX,550,140-15,336-302))) { 	
				balldir_Y = -balldir_Y;
			}
			
			A:for(int i=0 ;i<bricks.brick.length ; i++) {
				for(int j=0 ;j<bricks.brick[0].length ; j++) {
					//if brick of particular pos i j is available(ie 1) then detect the intersection
					if(bricks.brick[i][j]>0) {
						//Detecting pos of brick for intersection
						int brickX = j*BRICK_WIDTH + 80;
						int brickY = i*BRICK_HEIGHT + 50;
						int brickWidth = BRICK_WIDTH;
						int brickHeight = BRICK_HEIGHT;
						
						//creating rectangle around a brick
						Rectangle rect = new Rectangle(brickX, brickY, brickWidth,brickHeight );
						Rectangle brickRect = rect; 	//passing rect as reference
						
						//creating rectangle around ball
						Rectangle ballRect = new Rectangle(ballpos_X, ballpos_Y, BALL_WIDTH,BALL_HEIGHT );
						
						//if intersects or not
						if(ballRect.intersects(brickRect)) {
							playSound();
							bricks.setBrickValue(0, i, j);
							bricksRemain--;
							
							if(bricksRemain<=15) {
								balldir_X=-2;
								balldir_Y=-3;
								
							}
							System.out.println(bricksRemain);
							score+= 5;
							if(bricksRemain==0) {
								GameWin=true;
							}
							
							//left & right intersection of ball with brick
							if(ballpos_X+19 <= brickRect.x || ballpos_Y+1 >= brickRect.x + brickRect.width) {
								//move the ball in opp dir
								balldir_X = -balldir_X;
							}else {
								balldir_Y = -balldir_Y;
							}
							break A; 	//this is break label to come out of loop
						}
					}
				}
			}
			
			ballpos_X+=balldir_X;
			ballpos_Y+=balldir_Y;
			if(ballpos_X <=0) { 	//For left Border
				balldir_X = -balldir_X;
			}
			if(ballpos_Y <=0) { 	//For top Border
				balldir_Y = -balldir_Y;
			}
			if(ballpos_X > 660) { 	//For right Border
				balldir_X = -balldir_X;
			}
		}
		else {
		}
		repaint();	//after incrementing paddle value ,we need to show the new pos of paddle
		
	}
	
	public void moveRight() {
		play=true;
		paddleX+=20;
	}
	public void moveLeft() {
		play=true;
		paddleX-=20;
	}
	

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_LEFT && left) {
			firsttime=false;
			if(paddleX<=10) {
				paddleX=10;
			}
			else {
				moveLeft();
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT && right) {
			firsttime=false;
			if(paddleX>=560) {
				paddleX=560;
			}
			else {
				moveRight();
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			if(!play) {
				
				firsttime=false;
				GameWin=false;
				play=true;
				left=true;
				right=true;
				ballpos_X=120;
				ballpos_Y=350;
				balldir_X=-1;
				balldir_Y=-3;
				paddleX=310;
				score=0;
				bricksRemain=TOTAL_BRICKS;
				bricks = new BrickGenerator(ROWS, COLUMNS);
				repaint();
				bgmusic.play();
				
			}
			else {
				moveRight();
			}
		}
		
	}
	

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

}

