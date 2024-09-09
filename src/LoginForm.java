import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

import javax.swing.*;
public class LoginForm extends JFrame {
	
	public LoginForm() {
		setSize(390,300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(3);
		setLayout(null);
		
		
		Font labelfont = new Font ("Arial", Font.BOLD,16);
		Font labelfont1 = new Font ("Arial", Font.BOLD,20);
		
//		JPanel headerPanel = new JPanel();
		JLabel headerPanel = new JLabel(new ImageIcon("C:\\Users\\Default\\Downloads\\library_image_2.jpg"));
		headerPanel.setBackground(new Color(255,0,0));
		headerPanel.setBounds(0,5,390,60);
		add(headerPanel);
		
		JLabel headerText = new JLabel("Login Form");
		headerText.setForeground(new Color(255,255,255));
		headerText.setBounds(150,5,390,60);
		headerText.setFont(labelfont1);
		headerPanel.add(headerText);
		
//		JPanel inputPanel = new JPanel();
		JLabel inputPanel = new JLabel(new ImageIcon("C:\\Users\\Default\\Downloads\\library_image_2.jpg"));
//		inputPanel.setBackground(new Color(218,112,214));
		inputPanel.setBounds(0,60,490,425);
		inputPanel.setLayout(null);
		add(inputPanel);
		

		JLabel emailLabel = new JLabel("Email:");
		emailLabel.setBounds(70,40,100,25);
		emailLabel.setForeground(Color.WHITE);
		emailLabel.setFont(labelfont);
		inputPanel.add(emailLabel);
		
		JTextField emailtxt = new JTextField();
		emailtxt.setBounds(130,40,230,25);
		emailtxt.setFont(labelfont);
		inputPanel.add(emailtxt);
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(40,80,100,25);
		passwordLabel.setForeground(Color.WHITE);
		passwordLabel.setFont(labelfont);
		inputPanel.add(passwordLabel);
		
		JTextField passwordtxt = new JTextField();
		passwordtxt.setBounds(130,80,230,25);
		passwordtxt.setFont(labelfont);
		inputPanel.add(passwordtxt);
		
		JButton  loginBtn = new JButton("Login");
		loginBtn.setBounds(90,150,100,30);
		loginBtn.setBackground(Color.white);
		loginBtn.setFont(labelfont);
		loginBtn.setFocusable(true);
		loginBtn.setForeground(Color.BLACK);
		inputPanel.add(loginBtn);
		
		JButton registerBtn = new JButton("Register");
		 registerBtn .setBounds(200,150,100,30);
		 registerBtn .setBackground(Color.white);
		 registerBtn .setFont(labelfont);
		 registerBtn .setFocusable(true);
		 registerBtn .setForeground(Color.BLACK);
		inputPanel.add(registerBtn);
		
		
		loginBtn.addActionListener (new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String email = emailtxt.getText();
				String password = passwordtxt.getText();
				
				String emailRegex = "^[a-z0-9]+@[a-z]+.[a-z]+$";
				String passwordRegex =  "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
				
				
				if(!Pattern.matches(emailRegex, email)) {
					JOptionPane.showMessageDialog(null,"invalid");
					
				}
				else if(!Pattern.matches(passwordRegex, password)) {
					JOptionPane.showMessageDialog(null,"1 digit, 1 uppercase, 1 lowercase, 1 special char and length 6-20");
					
				}
				else {
//					System.out.println(email);
//					System.out.println(password);
					
					
					String Query = "SELECT `id`, `name`, `email`, `password`, `mobile`, `institute` FROM `users` WHERE `email` = '"+email+"' and `password` = '"+password+"'";
					
					try {
						try {
							Class.forName("com.mysql.cj.jdbc.Driver");
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management","root","" );
			            Statement statement = connection.createStatement();
			            ResultSet rs = statement.executeQuery(Query);
//			            if (rs.next() != false) { 
				            while (rs.next()) {
				            	String dbValue = rs.getString("email");
				            	if(dbValue.equals("nadia@gmail.com")) {
				            		dispose();
				            		new Table();
				            		return;
				            	}else {
				            		dispose();
				            		new ManagementList(email);
				            		return;
				            	}	
				            }
				            JOptionPane.showMessageDialog(null,"User is not Found"); 
			            
					}  catch (SQLException e1) {
			            e1.printStackTrace();
			        }
//					System.out.println(Query);
				}
				
				
				
				
				
			}

			
		});
		registerBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				new RegistrationForm();
				
				
			}
			
		});
		
		
		setVisible(true);
		

}



	

	

}
