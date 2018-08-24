package com.collegeProject.brickBreaker;


import javax.swing.JFrame;

public class GameFrame implements GameConstants{

	public GameFrame() {
		// TODO Auto-generated method stub
		JFrame jframe = new JFrame();
		GamePanel gamepanel = new GamePanel();
		jframe.setLocation(300,50);
		jframe.setSize(GAME_WIDTH,GAME_HEIGHT);
		jframe.setTitle("Breakout Ball");
		jframe.setResizable(false);
		jframe.setVisible(true);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.add(gamepanel);
	}

}
