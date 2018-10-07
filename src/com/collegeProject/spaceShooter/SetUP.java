package com.collegeProject.spaceShooter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class SetUP implements Runnable {
	private String title;
	private int height;
	private int width;
	private Thread thread;
	private boolean running;
	private BufferStrategy buffer;
	private Graphics g;  
	//	private int y;
	private gameManager manager;
	//	private int gameWidth;
	//	private int gameHeight;

	private Display display;
	public static final int gameWidth=1100;
	public static final int gameHeight=400; 
	public SetUP(String title,int height,int width) {
		this.title=title;
		this.height=height;
		this.width=width;
	}
	public void init() {
		display=new Display(title,width,height);
		LoadImages.init();
		manager=new gameManager(); 
		manager.init();
		//gameWidth=400;
		//gameHeight= 400;
	}
	//main-start()-thread start-run() -init()-Display initianise
	public synchronized void start() {
		if(running)
			return;
		running=true;
		if(thread==null) {
			thread=new Thread(this);
			thread.start();
		}
	}
	public synchronized void stop() {
		if(!(running))
			return;
		running=false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//stop the thread

	}
	public void tick() {
		manager.tick();

	}
	public void render() {
		buffer=display.getCanvas().getBufferStrategy();
		if(buffer==null) {
			display.getCanvas().createBufferStrategy(3);//create 3 screens 
			return;
		}
		g=buffer.getDrawGraphics();
		g.clearRect(0,0,width,height);//clear screen after thread changes
		//draw
		//	g.fillRect(33,y,44,44);

		//	g.setColor(Color.DARK_GRAY );
		g.drawImage(LoadImages.image, 50,50, gameWidth, gameHeight,null);
		manager.render(g);
		//end of draw
		buffer.show();
		g.dispose();
	}


	public void run() {
		init();
		int fps=50;//frames
		double timePerTick=1000000000/fps;
		double delta=0;
		long current=System.nanoTime();//system time fetch
		while(running) {
			delta=delta +(System.nanoTime()-current)/timePerTick;
			current=System.nanoTime();
			if(delta>=1) {
				tick();
				render();
				delta--;
			}
		}
	}
}
