package com.collegeProject.snakeGame;

public class Food extends Snake implements GameConstants{
	
	private Snake snake = new Snake() ;
	private int foodX  ;
	private int foodY  ;
	private int randomPosition = 25  ;

	public int getFoodX() {
		return foodX;
	}
	
	public int getFoodY() {
		return foodY;
	}

//	private int w = GWIDTH-50-PIXELSIZE ;
//	private int h = GHEIGHT-50-PIXELSIZE ;
	private int p = 50+PIXELSIZE ;

	
	public void createFood() {
		
//	    //for bricks 
//	    //top top left
//	    if(snake.getSnakeX(0)<p && snake.getSnakeY(0)<2*p) {
//	    return ;
//	    }
//
//	    //top left
//	    else if(snake.getSnakeX(0)<2*p && snake.getSnakeY(0)<p) {
//		    return ;
//	    }
//
//	    //top top right 
//	    else if(snake.getSnakeX(0) > GWIDTH-(2*p)-25 && snake.getSnakeY(0) < p) {
//		    return ;
//	    }
//	    //top right
//	    else if(snake.getSnakeX(0)>(GWIDTH-p)-25 && snake.getSnakeY(0)< (2*p)) {
//		    return ;
//	    }
//	    
//	    //bottom bottom left 
//	    else if(snake.getSnakeX(0)<p && snake.getSnakeY(0)>GHEIGHT-2*p-25) {
//		    return ;
//	    }
//	    
//	    //bottom left 
//	    else if(snake.getSnakeX(0)<2*p && snake.getSnakeY(0)>GHEIGHT-p-25) {
//		    return ;
//	    }
//
//	    //bottom bottom right
//	    else if(snake.getSnakeX(0)>GWIDTH-2*p && snake.getSnakeY(0)>GHEIGHT-p) {
//		    return ;
//	    }
//	    
//	    //bottom right
//	    else if(snake.getSnakeX(0)>GWIDTH-p && snake.getSnakeY(0)>GHEIGHT-2*p) {
//		    return ;
//	    }
//	    
//	    //for center wall 
//	    else if(snake.getSnakeX(0)>GWIDTH/2-125 && snake.getSnakeX(0)<GWIDTH/2 + 50 ) {
//		    if(snake.getSnakeY(0)>GHEIGHT/2-125 && snake.getSnakeY(0)<GHEIGHT/2+50) {
//		    	System.out.println("food can not be created here ");
//			    return ;
//		    }
//	    }
		
	    	int location = (int)(Math.random() * randomPosition) ;
			foodX = location*PIXELSIZE ;
			
			location = (int)(Math.random() * randomPosition) ;
			foodY = location*PIXELSIZE ;
		
			
		    //for bricks 
		    //top top left
		    if(foodX<p && foodY<2*p) {
	    	System.out.println("food can not be created here ");
	    	createFood();
		    }
	
		    //top left
		    else if(foodX<2*p && foodY<p) {
		    	System.out.println("food can not be created here ");
		    	createFood();
			}
	
		    //top top right 
		    else if( foodX > GWIDTH-(2*p)-25 && foodY < p) {
		    	createFood();
		    	System.out.println("food can not be created here ");
			}
		    //top right
		    else if(foodX >(GWIDTH-p)-25 && foodY < (2*p)) {
		    	System.out.println("food can not be created here ");
		    	createFood();
			}
		    
		    //bottom bottom left 
		    else if(foodX <p && foodY >GHEIGHT-2*p-25) {
		    	System.out.println("food can not be created here ");
		    	createFood();
			}
		    
		    //bottom left 
		    else if(foodX <2*p && foodY >GHEIGHT-p-25) {
		    	System.out.println("food can not be created here ");
		    	createFood();
		    }
	
		    //bottom bottom right
		    else if(foodX >GWIDTH-2*p && foodY >GHEIGHT-p) {
		    	System.out.println("food can not be created here ");
		    	createFood();
		    }
		    
		    //bottom right
		    else if(foodX >GWIDTH-p && foodY >GHEIGHT-2*p) {
		    	System.out.println("food can not be created here ");
		    	createFood();
		    }
		    
		    //for center wall 
		    else if(foodX >GWIDTH/2-125 && foodX <GWIDTH/2 + 50 ) {
			    if(foodY >GHEIGHT/2-125 && foodY <GHEIGHT/2+50) {
			    	System.out.println("food can not be created here ");
			    	createFood();
			    }
		    }
			
		    else if((foodX==snake.getSnakeX(0)) && (foodX==snake.getSnakeY(0))) {
				createFood();
		    }
	}
}
