package com.collegeProject.snakeGame;
import javax.swing.JFrame;

public class GameFrame extends JFrame implements GameConstants{
	
	private static final long serialVersionUID = 1L;
//
//	public static void main(String[] args) {		
//		GameFrame frame = new GameFrame();
//	}

	public GameFrame() {
		Board board = new Board();
		add(board);
		pack();
		setResizable(false);
		setTitle(TITLE);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
