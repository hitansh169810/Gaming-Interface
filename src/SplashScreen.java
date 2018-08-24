import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class SplashScreen extends JWindow {
	
	private static final long serialVersionUID = 1L;
	private int counter;
	private Timer timer;
	private boolean isVisible ;  //false
	
	private void plusProgress(){
		timer = new Timer(40,e->{
		if(counter>=100){
			timer.stop();
			LoginScreen loginScreen = new LoginScreen();
			loginScreen.setVisible(true);
			SplashScreen.this.setVisible(false);
			SplashScreen.this.dispose();
		}
		titlelbl.setVisible(isVisible);
		isVisible = !isVisible;
			if(counter<=100){
		counter++ ;
		progressBar.setValue(counter);
		}
		});
		timer.start();
	}
	JProgressBar progressBar = new JProgressBar();
	private JPanel contentPane;
	JLabel titlelbl = new JLabel(" GAMING INTERFACE");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SplashScreen frame = new SplashScreen();
					frame.setVisible(true);
					frame.plusProgress();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SplashScreen() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100,500, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(44,62,80));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		titlelbl.setForeground(Color.YELLOW);
		titlelbl.setHorizontalAlignment(SwingConstants.CENTER);
		titlelbl.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 18));
		titlelbl.setBounds(83, 6, 297, 45);
		contentPane.add(titlelbl);
		
		JLabel imglbl = new JLabel("");
		Icon icon = new ImageIcon(SplashScreen.class.getResource("splash.png"));
		imglbl.setIcon(icon);
		imglbl.setBounds(85, 45, 320, 310);
		contentPane.add(imglbl);
		progressBar.setForeground(Color.BLACK);
		progressBar.setFont(new Font("Arial", Font.BOLD, 12));
		progressBar.setBackground(Color.CYAN);
		
		progressBar.setStringPainted(true);
		progressBar.setBounds(10, 364, 480, 25);
		contentPane.add(progressBar);
		setLocationRelativeTo(null);
	}
}