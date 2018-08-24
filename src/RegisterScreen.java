import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
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

public class RegisterScreen extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userNameField;
	private JTextField userIdField;
	private JPasswordField passwordField;
	private JPasswordField confirmPasswordField;
	
	Icon icon = new ImageIcon(SplashScreen.class.getResource("key.png"));

	public RegisterScreen() {
		setBackground(new Color(247,147,8));
		setResizable(false);
		setTitle("GAMING INTERFACE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 700);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(44,62,80));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUserName = new JLabel(" User Name  :           ");
		lblUserName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUserName.setForeground(Color.WHITE);
		lblUserName.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblUserName.setBounds(27, 63, 207, 30);
		contentPane.add(lblUserName);
		
		JLabel lblUserid = new JLabel(" Enter User ID  :       ");
		lblUserid.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUserid.setForeground(Color.WHITE);
		lblUserid.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblUserid.setBounds(27, 133, 194, 30);
		contentPane.add(lblUserid);
		
		
		JLabel lblPassword = new JLabel("Enter Password :       ");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblPassword.setBounds(27, 203, 194, 30);
		contentPane.add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel(" Confirm Password :       ");
		lblConfirmPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConfirmPassword.setForeground(Color.WHITE);
		lblConfirmPassword.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblConfirmPassword.setBounds(27, 273, 194, 30);
		contentPane.add(lblConfirmPassword);
		
		JLabel lblRegister = new JLabel("Register Here");
		lblRegister.setBackground(Color.WHITE);
		lblRegister.setForeground(Color.WHITE);
		lblRegister.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegister.setFont(new Font("Ebrima", Font.BOLD, 22));
		lblRegister.setBounds(10, 4, 564, 45);
		contentPane.add(lblRegister);
		
		userNameField = new JTextField();
		userNameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		userNameField.setForeground(Color.WHITE);
		userNameField.setBackground(new Color(108,122,137));
		userNameField.setBounds(244, 66, 304, 30);
		contentPane.add(userNameField);
		userNameField.setColumns(10);

		userIdField = new JTextField();
		userIdField.setForeground(Color.WHITE);
		userIdField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		userIdField.setBackground(new Color(108,122,137));
		userIdField.setBounds(244, 133, 304, 30);
		contentPane.add(userIdField);
		userIdField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setForeground(Color.WHITE);
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordField.setBackground(new Color(108,122,137));
		passwordField.setBounds(244, 200, 304, 30);
		contentPane.add(passwordField);
		
		confirmPasswordField = new JPasswordField();
		confirmPasswordField.setForeground(Color.WHITE);
		confirmPasswordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		confirmPasswordField.setBackground(new Color(108,122,137));
		confirmPasswordField.setBounds(244, 267, 304, 30);
		contentPane.add(confirmPasswordField);
		
		
		JButton btnRegister = new JButton("Register Here");
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnRegister.setBackground(new Color(54,185,255));
		btnRegister.setForeground(Color.WHITE);
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doRegister();
			}

		private void doRegister() {
						
						String userName = userNameField.getText();
						String userId = userIdField.getText();
						String password = passwordField.getText();
						String confirmPassword = confirmPasswordField.getText();
						
						Connection con = null ;
						int records = 0 ;
						PreparedStatement pstmt = null ;
						
						
						try {
						con = LoginScreen.getConnnection() ;
		
						// DB NAME - game , USER TABLE NAME - USER_MST
						
						if(con != null) {
						System.out.println("con is "+con);	
						pstmt = con.prepareStatement("INSERT INTO USER_MST(username,userid,userpassword) values(?,?,?)");
						pstmt.setString(1, userName);
						pstmt.setString(2,userId);
						pstmt.setString(3,password);
						System.out.println("pstmt is "+pstmt);
						
						if(password.equals(confirmPassword)) {
						records = pstmt.executeUpdate() ;
						System.out.println("password and confirm password are equal");
						}
							if(records > 0) {
//								while(rs.next()) {
		//									if() {
											System.out.println("Record inserted ");
											MainScreen mainScreen = new MainScreen("");
											mainScreen.setVisible(true);
		//									this.setVisible(false);
		//									this.dispose();
		//									}
//								}
							}
							else{
		//						JOptionPane.showInternalMessageDialog(this,"Invalid Userid or Password" , "GAME", JOptionPane.INFORMATION_MESSAGE,icon);
								userNameField.setText("");
								userIdField.setText("");
								passwordField.setText("");
								confirmPasswordField.setText("");
		//						JOptionPane.showConfirmDialog(parentComponent, message, title, optionType, messageType, icon) ;
							}
						}
						}
						catch (SQLException e) {
							System.out.println("exception e"+e);
						}
//						finally {
//							if(rs != null) {
//								try {
//									rs.close();
//								} catch (SQLException e) {
//									// TODO Auto-generated catch block
//									e.printStackTrace();
//								}
//								System.out.println("rs closed ");
//							}
//							if(pstmt != null) {
//								try {
//									pstmt.close();
//								} catch (SQLException e) {
//									// TODO Auto-generated catch block
//									e.printStackTrace();
//								}
//							}
//							if(con != null) {
//								try {
//									con.close();
//								} catch (SQLException e) {
//									// TODO Auto-generated catch block
//									e.printStackTrace();
//								}
//							}
//						}
		}
		});
		
		btnRegister.setBounds(10, 332, 263, 45);
		contentPane.add(btnRegister);
		
		
		
		JButton btnLogin = new JButton("Already Have An Account ? Sign In Here ");
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBackground(new Color(254,50,40));
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLogin.setBounds(283, 332, 291, 45);
		contentPane.add(btnLogin);
		
		Icon registerImage = new ImageIcon(SplashScreen.class.getResource("registerImage.png"));
		JLabel registerImagelbl = new JLabel("");
		registerImagelbl.setBounds(10, 406, 564, 233);
		registerImagelbl.setHorizontalAlignment(SwingConstants.CENTER);
		registerImagelbl.setIcon(registerImage);
		contentPane.add(registerImagelbl);
		
		
		
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				doLogin() ;
			}

			private void doLogin() {
				LoginScreen loginScreen = new LoginScreen();
				loginScreen.setVisible(true);	
				setVisible(false);
				dispose();
			}

		}) ;
	}
}
