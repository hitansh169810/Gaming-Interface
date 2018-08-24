package com.collegeProject.snakeGame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
//import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import jaco.mp3.player.MP3Player;

public class Board extends JPanel implements GameConstants ,ActionListener {

	private static final long serialVersionUID = 1L;
	private Boolean inGame  = true ;
	private Food food = new Food() ;
	private Snake snake = new Snake() ;
	private int w = GWIDTH-50-PIXELSIZE ;
	private int h = GHEIGHT-50-PIXELSIZE ;
	private int p = 50+PIXELSIZE ;
	private static int score = 0 ;
	int DELAY = 80 ;
	
	Font  scoreFont  = new Font(Font.SERIF, Font.PLAIN,  30);
	String showScore = null ;

	public Board() {

	setPreferredSize(new Dimension(GWIDTH, GHEIGHT));
	setSize(GWIDTH,GHEIGHT);
//	setBackground(Color.BLACK);	
	
	gameLoop();
	setFocusable(true);
	bindEvents() ;
	playBackGround(true);
	}
	
	MP3Player music = new MP3Player() ;
	MP3Player sound = new MP3Player() ;
	MP3Player gameover  = new MP3Player() ;
	
	private void playSound() {
		sound = new MP3Player(Board.class.getResource(POP_SOUND)) ;
		sound.play();
	}
	
	private void playBackGround(Boolean isPlaying) {
		if(isPlaying) {
		music = new MP3Player(Board.class.getResource(BACKGROUND_SOUND));
		music.play();
		}
		else {
			music.stop();
		}
	}

	
	private void drawBackground(Graphics g) {

		Image bg = new ImageIcon(Board.class.getResource(BACKGROUND_IMAGE)).getImage();
		g.drawImage(bg, 0, 0, GWIDTH, GHEIGHT, null);
		
		showScore = "Score = "+score ;
		g.setFont(scoreFont);
		g.setColor(Color.white);
		g.drawString(showScore,GWIDTH/2-100 ,50);

	}
	
	
	
	private void bindEvents() {
			this.addKeyListener(new KeyAdapter() {
			
				public void keyPressed(KeyEvent e) {
				int key =  e.getKeyCode();
				if ((key == KeyEvent.VK_LEFT) && (!snake.isMovingRight())) {
		            snake.setMovingLeft(true);
		            snake.setMovingUp(false);
		            snake.setMovingDown(false);
		        }

				else if ((key == KeyEvent.VK_RIGHT) && (!snake.isMovingLeft())) {
		            snake.setMovingRight(true);
		            snake.setMovingUp(false);
		            snake.setMovingDown(false);
		        }

				else if ((key == KeyEvent.VK_UP) && (!snake.isMovingDown())) {
		            snake.setMovingUp(true);
		            snake.setMovingRight(false);
		            snake.setMovingLeft(false);
		        }

				else if ((key == KeyEvent.VK_DOWN) && (!snake.isMovingUp())) {
		            snake.setMovingDown(true);
		            snake.setMovingRight(false);
		            snake.setMovingLeft(false);
		        }

				else if ((key == KeyEvent.VK_ENTER) && (inGame == false)) {

//					System.out.println("ingame = false && enter pressed ");
		            inGame = true;
		            snake.setMovingDown(false);
		            snake.setMovingRight(false);
		            snake.setMovingLeft(false);
		            snake.setMovingUp(false);
		            initialiseGame();
		            gameLoop();
		            playBackGround(inGame);
		            score=0;
		        }
			}
		});
	}
		
private Timer timer ;

@Override
public void actionPerformed(ActionEvent e) {
    if (inGame == true) {
    	
    	checkFoodCollisions();
        checkCollisions();
        snake.move();
//        System.out.println(snake.getSnakeX(0) + " " + snake.getSnakeY(0) + " " + food.getFoodX() + ", " + food.getFoodY());
    }
    repaint();
}

public void gameLoop() {
	 initialiseGame() ;
	 snake.move(); 
	 timer = new Timer(DELAY,this);
	 timer.start();
}

@Override
public void paintComponent(Graphics g) {
	super.paintComponent(g);	
	draw(g) ;
}

void initialiseGame() {
	snake.setJoints(3);
	for(int i = 0 ;i <snake.getJoints() ; i++) {
		snake.setSnakeX(200);
		snake.setSnakeY(200);
		}
 	 snake.setMovingRight(true);
	 food.createFood();
}

void checkFoodCollisions() {

	   if ((proximity(snake.getSnakeX(0), food.getFoodX(), 20)) && (proximity(snake.getSnakeY(0), food.getFoodY(), 20))) {
		   score++;
		   playSound();
		   System.out.println("collided with food ");
		   snake.setJoints(snake.getJoints() + 1);
           food.createFood();
    	   if(score >= 3 ) {
    		   DELAY-- ;
        	   System.out.println("Inside checkFoodCollison DELAY is : "+DELAY);
    	   }
    	   
    } 
}

void checkCollisions() {

    for (int i = snake.getJoints() ; i > 0; i--) {

    	if ((i > 5) && (snake.getSnakeX(0) == snake.getSnakeX(i) && (snake.getSnakeY(0) == snake.getSnakeY(i)))) {
            inGame = false;
        }
    	
    }
    
    if (snake.getSnakeY(0) >= GHEIGHT) {
    	inGame = true ;
    	snake.setSnakeY(0);
    }

    else if (snake.getSnakeY(0) < 0) {
    	  inGame = true;
      	snake.setSnakeY(GHEIGHT);
    }

    else if (snake.getSnakeX(0) >= GWIDTH) {
    	inGame = true ;
    	snake.setSnakeX(0);
    }

    else if (snake.getSnakeX(0) < 0) {
    	inGame = true ;
    	snake.setSnakeX(GWIDTH);
    }
    
    
    //for bricks 
    //top top left
    if(snake.getSnakeX(0)<p && snake.getSnakeY(0)<2*p) {
		inGame = false;
    }
    //top left
    if(snake.getSnakeX(0)<2*p && snake.getSnakeY(0)<p) {
		inGame = false;
    }

    //top top right 
    if(snake.getSnakeX(0) > GWIDTH-(2*p)-25 && snake.getSnakeY(0) < p) {
		inGame = false;
    }
    //top right
    if(snake.getSnakeX(0)>(GWIDTH-p)-25 && snake.getSnakeY(0)< (2*p)) {
		inGame = false;
    }
    
    //bottom bottom left 
    if(snake.getSnakeX(0)<p && snake.getSnakeY(0)>GHEIGHT-2*p-25) {
		inGame = false;
    }
    
    //bottom left 
    if(snake.getSnakeX(0)<2*p && snake.getSnakeY(0)>GHEIGHT-p-25) {
		inGame = false;
    }

    //bottom bottom right
    if(snake.getSnakeX(0)>GWIDTH-2*p && snake.getSnakeY(0)>GHEIGHT-p) {
		inGame = false;
    }
    
    //bottom right
    if(snake.getSnakeX(0)>GWIDTH-p && snake.getSnakeY(0)>GHEIGHT-2*p) {
		inGame = false;
    }

    
    //for center wall 
    if(snake.getSnakeX(0)>GWIDTH/2-125 && snake.getSnakeX(0)<GWIDTH/2 + 50 ) {
	    if(snake.getSnakeY(0)>GHEIGHT/2-125 && snake.getSnakeY(0)<GHEIGHT/2+50) {
	    	System.out.println("collided with center wall ");
	    	inGame = false;
	    }
    }

    if (!inGame) {
        timer.stop();
    }

}


void draw(Graphics g) {
	
	drawBackground(g);

	if(inGame == true) {

//		g.setColor(Color.GREEN);
//		g.fillRect(food.getFoodX(), food.getFoodY(), PIXELSIZE,PIXELSIZE);
//		g.fillOval(food.getFoodX(), food.getFoodY(), PIXELSIZE,PIXELSIZE);
		
		Image foodImg = new ImageIcon(Board.class.getResource(FOOD)).getImage();
		g.drawImage(foodImg,food.getFoodX(), food.getFoodY(), PIXELSIZE,PIXELSIZE, null);
		
		
//		g.setColor(Color.white);

		/*
		g.fill3DRect(0,0,25,200,true); 		//top left
		g.fill3DRect(0,0,200,25,true); 		//top left
		
		g.fill3DRect(GWIDTH-25,0,25,200,true);	//top right 
		g.fill3DRect(GWIDTH-200,0,200,25,true);	//top right

		g.fill3DRect(0,GHEIGHT-20,200,25,true);		//bottom left
		g.fill3DRect(0,GHEIGHT-200,25,200,true);	//bottom left
		
		
		g.fill3DRect(GWIDTH-25,GHEIGHT-200,200,200,true); 			//bottom right
		g.fill3DRect(GWIDTH-200,GHEIGHT-25,GWIDTH,GHEIGHT,true);	//bottom right

		
		// for center box .
		g.fillRect((GWIDTH/2-150),(GHEIGHT/2-150),(GWIDTH/2-100),(GHEIGHT/2-50));
		
		*/
		
		Image wall = new ImageIcon(Board.class.getResource(WALL)).getImage();
		
		//top left
		g.drawImage(wall,0,0,50+PIXELSIZE,50+PIXELSIZE, null);
		g.drawImage(wall,50+PIXELSIZE,0,50+PIXELSIZE,50+PIXELSIZE, null);
		g.drawImage(wall,0,50+PIXELSIZE,50+PIXELSIZE,50+PIXELSIZE, null);
		
		
		//top right
		g.drawImage(wall,w,0,p,p, null);
		g.drawImage(wall,w-75,0,p,p, null);
		g.drawImage(wall,w,p,p,p, null);
		
		//bottom left
		g.drawImage(wall,0,h,p,p, null);
		g.drawImage(wall,0,h-75,p,p, null);
		g.drawImage(wall,w-75,h,p,p, null);
		
		//bottom right
		g.drawImage(wall,w,h,p,p, null);
		g.drawImage(wall,w,h-75,p,p, null);
		g.drawImage(wall,p,h,p,p, null);
				
		
		//center
		g.drawImage(wall,GWIDTH/2-75-PIXELSIZE,GHEIGHT/2-75-PIXELSIZE,p,p, null);
		g.drawImage(wall,GWIDTH/2-25,GHEIGHT/2-75-PIXELSIZE,p,p, null);
		g.drawImage(wall,GWIDTH/2-75-PIXELSIZE,GHEIGHT/2-25,p,p, null);
		g.drawImage(wall,GWIDTH/2-25,GHEIGHT/2-25,p,p, null);

		
		
		for(int i= 0 ; i<snake.getJoints() ;i++ ) {
			if(i==0) {
				
//				g.setColor(Color.red) ;
//				g.fillOval(snake.getSnakeX(i), snake.getSnakeY(i),PIXELSIZE, PIXELSIZE);
			
				Image head = new ImageIcon(Board.class.getResource(HEAD)).getImage();
				g.drawImage(head, snake.getSnakeX(i), snake.getSnakeY(i),PIXELSIZE, PIXELSIZE, null);
				
//				g.setColor(Color.BLACK);
//				g.fillOval(snake.getSnakeX(i)+10, snake.getSnakeY(i)+10,PIXELSIZE/3, PIXELSIZE/3);
			}			
			else {
//				g.setColor(Color.YELLOW);
//				g.fillOval(snake.getSnakeX(i), snake.getSnakeY(i),PIXELSIZE, PIXELSIZE);
			
				Image body = new ImageIcon(Board.class.getResource(BODY)).getImage();
				g.drawImage(body,snake.getSnakeX(i), snake.getSnakeY(i),PIXELSIZE, PIXELSIZE, null);
				
			
			}
		}
		Toolkit.getDefaultToolkit().sync();		
	}
	else {
	endGame(g) ;	
	}
}

void endGame(Graphics g) {
	
	Font  f1  = new Font(Font.SERIF, Font.PLAIN,  38);
	inGame= false ;
    String message1 = "Game over "+" "+"Score is "+score;
	String message2 = " Press Enter to Play Again ..." ;
	g.setFont(f1);
    g.setColor(Color.red);
    g.drawString(message1,GWIDTH/2-300 ,GHEIGHT / 2);
    
    g.setFont(new Font(Font.MONOSPACED,Font.ROMAN_BASELINE,48));
    g.setColor(Color.green);
    g.drawString(message2,150 ,GHEIGHT / 2+50);
    
    System.out.println("Game Ended");
    playBackGround(false);
    gameover = new MP3Player(Board.class.getResource(GAME_OVER));
	gameover.play();
	
}

private boolean proximity(int a, int b, int closeness) {
    return Math.abs( a - b ) < closeness ;
    }
}

