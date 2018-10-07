package com.collegeProject.snakeGame;
import javax.swing.JFrame;

public class GameFrame extends JFrame implements GameConstants{
	
	private static final long serialVersionUID = 1L;
//
//	public static void main(String[] args) {		
//		GameFrame frame = new GameFrame();
//	}

	public GameFrame(String userName) {
		Board board = new Board(userName);
		add(board);
		pack();
		setResizable(false);
		setTitle(TITLE);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}
