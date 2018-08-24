import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class LoginScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	Icon icon = new ImageIcon(SplashScreen.class.getResource("key.png"));
	Icon tickImage = new ImageIcon(SplashScreen.class.getResource("tick.png"));
	
//	MP3Player bgSound = new MP3Player(SplashScreen.class.getResource("music.mp3"));
	boolean isplaying ;
	
	
	public static Connection getConnnection() throws SQLException {
		Connection connection = null;
//		String driverName = "org.postgresql.Driver" ;
		String dburl = "jdbc:postgresql://localhost:5432/game" ;
		String dbid = "postgres" ;
		String pass = "1234" ;
		connection = DriverManager.getConnection(dburl,dbid,pass);
		System.out.println("Connection Created ");
		 return connection ;
	}


	private void doRegister() {
		RegisterScreen registerScreen = new RegisterScreen();
		registerScreen.setVisible(true);
		this.setVisible(false);
		this.dispose();
	}
	
	private void checkLogin() {
		String userid = textField.getText();
		String pwd = passwordField.getText();
		Connection con = null ;
		ResultSet rs = null ;
		PreparedStatement pstmt = null ;
		
		
		try {
		con = getConnnection() ;

		// DB NAME - game , USER TABLE NAME - USER_MST
		
		if(con != null) {
		System.out.println("con is "+con);	
		pstmt = con.prepareStatement("SELECT USERNAME,USERID,USERPASSWORD FROM USER_MST WHERE USER_MST.USERID=? AND USER_MST.USERPASSWORD=?");
		pstmt.setString(1, userid);
		pstmt.setString(2,pwd);
		rs = pstmt.executeQuery() ;
//		System.out.println("rs.next is "+rs.next());
//		boolean a ;
			if(rs.next()) {
				while(rs.next()) {
							if(rs.getString("userid").equals(userid) && rs.getString("userpassword").equals(pwd)) {
							System.out.println("User Aunthenticated ");
							MainScreen mainScreen = new MainScreen(rs.getString("username"));
							mainScreen.setVisible(true);
							this.setVisible(false);
							this.dispose();
//							bgSound.stop();
							}
				}
			}
			else{
				JOptionPane.showMessageDialog(this,"Invalid Userid or Password" , "GAME", JOptionPane.INFORMATION_MESSAGE,icon);
				textField.setText("");
				passwordField.setText("");
//				JOptionPane.showConfirmDialog(parentComponent, message, title, optionType, messageType, icon) ;
			}
		}
		}
		catch (SQLException e) {
			System.out.println("exception e"+e);
		}
		finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				System.out.println("rs closed ");
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginScreen frame = new LoginScreen();
					frame.setVisible(true);
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginScreen() {
//		bgSound.play();
		setBackground(Color.GRAY);
		setResizable(false);
		setTitle("GAMING INTERFACE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(44,62,80));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUserid = new JLabel(" Enter User ID  :     ");
		lblUserid.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUserid.setForeground(Color.WHITE);
		lblUserid.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblUserid.setBounds(27, 63, 181, 30);
		contentPane.add(lblUserid);
		
		JLabel lblPassword = new JLabel("Enter Password  :     ");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblPassword.setBounds(27, 133, 181, 30);
		contentPane.add(lblPassword);
		
		JLabel lblLogin = new JLabel("Login Here");
		lblLogin.setBackground(Color.DARK_GRAY);
		lblLogin.setForeground(Color.WHITE);
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Ebrima", Font.BOLD, 22));
		lblLogin.setBounds(10, 4, 564, 45);
		contentPane.add(lblLogin);
		
		textField = new JTextField();
		textField.setForeground(Color.WHITE);
		textField.setBounds(244, 66, 304, 30);
		textField.setBackground(new Color(108,122,137));
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setForeground(Color.WHITE);
		passwordField.setBounds(244, 133, 304, 30);
		passwordField.setBackground(new Color(108,122,137));
		contentPane.add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLogin.setBackground(new Color(54,185,255));
		btnLogin.setForeground(Color.WHITE);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkLogin();
			}
		});
		
		btnLogin.setBounds(10, 210, 250, 45);
		contentPane.add(btnLogin);
		
		JButton btnRegister = new JButton("Don't Have An Account ? Register Here ...");
		btnRegister.setForeground(Color.WHITE);
		btnRegister.setBackground(new Color(254,50,40));
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRegister.setBounds(270, 210, 304, 45);
		contentPane.add(btnRegister);
		
		
		Icon icon = new ImageIcon(SplashScreen.class.getResource("game.png"));
		JLabel gameimage = new JLabel("");
		gameimage.setHorizontalAlignment(SwingConstants.CENTER);
		gameimage.setIcon(icon);
		gameimage.setBounds(10, 266, 564, 388);
		contentPane.add(gameimage);
		setLocationRelativeTo(null);
		
		btnRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				doRegister() ;
//				bgSound.stop();
			}
		}) ;
	}
}
