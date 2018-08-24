import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class MainScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private String userid;
	boolean isVisible = false ;
	int counter = 0 ;
	Timer timer ;
	
	public MainScreen(String userid) {
		getContentPane().setBackground(new Color(44,62,80));
		Icon icon1 =new ImageIcon(SplashScreen.class.getResource("game1.png"));
		Icon icon2 =new ImageIcon(SplashScreen.class.getResource("game2.jpeg"));
		Icon icon3 =new ImageIcon(SplashScreen.class.getResource("game3.jpeg"));
		Icon icon4 =new ImageIcon(SplashScreen.class.getResource("game4.jpg"));
		getContentPane().setLayout(null);

		JButton snakebtn = new JButton("");
		snakebtn.setBounds(50, 72, 225, 225);
		snakebtn.setIcon(icon1);
		snakebtn.setBackground(Color.WHITE);
		getContentPane().add(snakebtn);
		
		snakebtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				com.collegeProject.snakeGame.GameFrame snake = new com.collegeProject.snakeGame.GameFrame();	
				
			}
		});
		
		
		JButton hangManbtn = new JButton("Game3");
		hangManbtn.setBounds(50, 349, 225, 225);
		hangManbtn.setIcon(icon2);
		getContentPane().add(hangManbtn);

		
		hangManbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				com.collegeProject.snakeGame.GameFrame gameFrame = new com.collegeProject.snakeGame.GameFrame();	
			}
		});
		
		
		JButton brickBreakerbtn = new JButton("Game4");
		brickBreakerbtn.setBounds(321, 349, 225, 225);
		brickBreakerbtn.setIcon(icon3);
		brickBreakerbtn.setBackground(Color.WHITE);
		getContentPane().add(brickBreakerbtn);

		
		brickBreakerbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				com.collegeProject.brickBreaker.GameFrame brickBreaker = new com.collegeProject.brickBreaker.GameFrame() ;
				System.out.println("brickBreaker clicked "+brickBreaker.toString());
			}
		});
		
		JButton spaceInvaderbtn = new JButton("");
		spaceInvaderbtn.setBounds(321, 72, 225, 225);
		spaceInvaderbtn.setIcon(icon4);
		getContentPane().add(spaceInvaderbtn);
		
		
		spaceInvaderbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
//				com.collegeProject.snakeGame.GameFrame gameFrame = new com.collegeProject.snakeGame.GameFrame();	
//				spaceInvader.com.joshuacrotts.genericspaceshooter.main.GenericSpaceShooterHandler spaceInvader = new spaceInvader.com.joshuacrotts.genericspaceshooter.main.GenericSpaceShooterHandler() ;
				System.out.println("Space Invaders clicked ");
			}
		});
		

		this.userid = userid;
		JLabel welcomelbl = new JLabel("Welcome ,"+(this.userid).toUpperCase());
		welcomelbl.setForeground(Color.WHITE);
		welcomelbl.setFont(new Font("Century Gothic", Font.BOLD, 14));
		welcomelbl.setBounds(29, 11, 344, 34);
		getContentPane().add(welcomelbl);
		
		JLabel headinglbl = new JLabel("Click The Image To Play The Game ");
		headinglbl.setForeground(Color.WHITE);
		headinglbl.setHorizontalAlignment(SwingConstants.CENTER);
		headinglbl.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 16));
		headinglbl.setBounds(10, 620, 564, 34);
		getContentPane().add(headinglbl);
		
		
		JLabel snakelbl = new JLabel("Snake Game");
		snakelbl.setVerticalAlignment(SwingConstants.BOTTOM);
		snakelbl.setForeground(Color.WHITE);
		snakelbl.setHorizontalAlignment(SwingConstants.CENTER);
		snakelbl.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 16));
		snakelbl.setBounds(71, 308, 170, 24);
		getContentPane().add(snakelbl);
		
		JLabel spaceInvaderlbl = new JLabel("Space Inveder Game");
		spaceInvaderlbl.setForeground(Color.WHITE);
		spaceInvaderlbl.setBounds(347, 308, 170, 24);
		spaceInvaderlbl.setHorizontalAlignment(SwingConstants.CENTER);
		spaceInvaderlbl.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 16));
		getContentPane().add(spaceInvaderlbl);
		
		JLabel hangManlbl = new JLabel("HangMan Game");
		hangManlbl.setForeground(Color.WHITE);
		hangManlbl.setBounds(87, 585, 170, 24);
		hangManlbl.setHorizontalAlignment(SwingConstants.CENTER);
		hangManlbl.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 16));
		getContentPane().add(hangManlbl);
		
		JLabel brickBreakerlbl = new JLabel("Brick Breaker Game");
		brickBreakerlbl.setForeground(Color.WHITE);
		brickBreakerlbl.setBounds(347, 585, 170, 24);
		brickBreakerlbl.setHorizontalAlignment(SwingConstants.CENTER);
		brickBreakerlbl.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 16));
		getContentPane().add(brickBreakerlbl);
		

		timer = new Timer(750,e->{
			if(counter<=250){
				counter++ ;
				isVisible = !isVisible;
			}
			
			snakebtn.setVisible(!isVisible);
			hangManbtn.setVisible(isVisible);
			spaceInvaderbtn.setVisible(isVisible);
			brickBreakerbtn.setVisible(!isVisible);
			
			snakelbl.setVisible(!isVisible);
			hangManlbl.setVisible(isVisible);
			spaceInvaderlbl.setVisible(isVisible);
			brickBreakerlbl.setVisible(!isVisible);
			
				if(counter>=250){
					counter = 0 ;
				}
			});
			timer.start();

		
		setTitle("GAMING INTERFACE DASHBOARD - WELCOME LOKESH KUMAR");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setLocation(200, 20);
		setResizable(false);
		setBounds(100, 100, 600, 700);
		setLocationRelativeTo(null);
	}
}
