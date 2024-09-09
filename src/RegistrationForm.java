import java.awt.Color;
import java.sql.SQLException;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Pattern;


import javax.swing.*;
public class RegistrationForm extends JFrame {
	public RegistrationForm() {
		setSize(500,460);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(3);
		setLayout(null);
		
//        JLabel background=new JLabel(new ImageIcon("C:\\Users\\Default\\Downloads\\library_image.jpg"));
//        add(background);
		Font labelfont = new Font ("Arial", Font.BOLD,16);
		Font labelfont1 = new Font ("Arial", Font.BOLD,20);
		
		JLabel headerPanel = new JLabel(new ImageIcon("C:\\Users\\Default\\Downloads\\library_image_2.jpg"));
//		JPanel headerPanel = new JPanel();
//		headerPanel.setBackground(Color.BLACK);
		headerPanel.setBounds(0,5,490,60);
		add(headerPanel);
		
		JLabel headerText = new JLabel("Registration Form");
		headerText.setBounds(175,5,490,60);
		headerText.setForeground(new Color(255,255,255));
		headerText.setFont(labelfont1);
    	headerPanel.add(headerText);
		
		JLabel inputPanel = new JLabel(new ImageIcon("C:\\Users\\Default\\Downloads\\library_image_2.jpg"));
//		inputPanel.setBackground(new Color(218,112,214));
		inputPanel.setBounds(0,18,490,425);
		inputPanel.setLayout(null);
		add(inputPanel);
	
		
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setBounds(120,50,100,25);
		nameLabel.setForeground(new Color(255,255,255));
		nameLabel.setFont(labelfont);
		inputPanel.add(nameLabel);
		
		JTextField nametxt = new JTextField();
		nametxt.setBounds(200,50,230,25);
		nametxt.setFont(labelfont);
		inputPanel.add(nametxt);
		
		JLabel emailLabel = new JLabel("Email:");
		emailLabel.setBounds(120,85,100,25);
		emailLabel.setForeground(new Color(255,255,255));
		emailLabel.setFont(labelfont);
		inputPanel.add(emailLabel);
		
		JTextField emailtxt = new JTextField();
		emailtxt.setBounds(200,85,230,25);
		emailtxt.setFont(labelfont);
		inputPanel.add(emailtxt);
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(85,120,100,25);
		passwordLabel.setForeground(new Color(255,255,255));
		passwordLabel.setFont(labelfont);
		inputPanel.add(passwordLabel);
		
		JTextField passwordtxt = new JTextField();
		passwordtxt.setBounds(200,120,230,25);
		passwordtxt.setFont(labelfont);
		inputPanel.add(passwordtxt);
		
		JLabel confirmpasswordLabel = new JLabel("Confirm Password:");
		confirmpasswordLabel.setBounds(20,160,150,25);
		confirmpasswordLabel.setForeground(new Color(255,255,255));
		confirmpasswordLabel.setFont(labelfont);
		inputPanel.add(confirmpasswordLabel);
		
		JTextField confirmpasswordtxt = new JTextField();
		confirmpasswordtxt.setBounds(200,160,230,25);
		confirmpasswordtxt.setFont(labelfont);
		inputPanel.add(confirmpasswordtxt);
		
		JLabel mobileLabel = new JLabel("Mobile:");
		 mobileLabel.setBounds(110,200,100,25);
		 mobileLabel.setForeground(new Color(255,255,255));
		 mobileLabel.setFont(labelfont);
		inputPanel.add(mobileLabel);
		
		JTextField  mobiletxt = new JTextField();
		 mobiletxt.setBounds(200,200,230,25);
		 mobiletxt.setFont(labelfont);
		inputPanel.add( mobiletxt);
		
		JLabel instituteLabel = new JLabel("Institute:");
		instituteLabel.setBounds(100,240,100,25);
		instituteLabel.setForeground(new Color(255,255,255));
		instituteLabel.setFont(labelfont);
		inputPanel.add(instituteLabel);
		
		JTextField institutetxt = new JTextField();
		institutetxt.setBounds(200,240,230,25);
		institutetxt.setFont(labelfont);
		inputPanel.add(institutetxt);
		
		JButton registerBtn = new JButton("Register");
		registerBtn.setBounds(110,280,100,30);
		registerBtn.setBackground(Color.white);
		registerBtn.setFont(labelfont);
		registerBtn.setFocusable(true);
		registerBtn.setForeground(Color.BLACK);
		inputPanel.add(registerBtn);
		
		JButton loginBtn = new JButton("Login");
		loginBtn .setBounds(280,280,100,30);
		loginBtn .setBackground(Color.white);
		loginBtn .setFont(labelfont);
		loginBtn .setFocusable(true);
		loginBtn .setForeground(Color.BLACK);
		inputPanel.add(loginBtn);
		
		
		
		loginBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LoginForm();
				
			}
			
		});
		
		
		registerBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nametxt.getText();
				String email = emailtxt.getText();
				String password = passwordtxt.getText();
				String confirmpassword = confirmpasswordtxt.getText();
				String mobile = mobiletxt.getText();
				String institute = institutetxt.getText();
				
				String nameRegex = "^[#.0-9a-zA-Z\\s,-]+$";
				String emailRegex = "^[a-z0-9]+@[a-z]+.[a-z]+$";
				String passwordRegex =  "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}";
				String mobileRegex = "^(\\+88)?01[2-9]\\d{8}$";
				String instituteRegex = "^[#.0-9a-zA-Z\s,-]+$";
				
						
				if(!Pattern.matches(nameRegex, name)) {
					JOptionPane.showMessageDialog(null,"Only 3-10 char is allowed");
					
				}
				else if(!Pattern.matches(emailRegex, email)) {
					JOptionPane.showMessageDialog(null,"invalid");
					
				}
				else if(!Pattern.matches(passwordRegex, password)) {
					JOptionPane.showMessageDialog(null,"1 digit, 1 uppercase, 1 lowercase, 1 special char and length 6-20");
					
				}
				
				else if(!confirmpassword.equals(password)) {
					JOptionPane.showMessageDialog(null,"password an confirm password is not matching!!");
					
				}
				
				
				else if(!Pattern.matches(mobileRegex, mobile)) {
					JOptionPane.showMessageDialog(null," invalid mobile number!!");
					
				}

				else if(!Pattern.matches(instituteRegex, institute)) {
					JOptionPane.showMessageDialog(null," incorrect institute!!");
					
				}
				else {
					String Retrive = "SELECT `id`, `name`, `email`, `password`, `mobile`, `institute` FROM `users` WHERE `email` = '"+email+"'";
					try {
						try {
							Class.forName("com.mysql.cj.jdbc.Driver");
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management","root","" );
			            Statement statement = connection.createStatement();
			            ResultSet rs = statement.executeQuery(Retrive);
			            if (rs.next() != false) { 
			            	JOptionPane.showMessageDialog(null,"Duplicate Email not Allowed!"); 
			            }else {
			            	String Query = "INSERT INTO `users`(`name`, `email`, `password`, `mobile`, `institute`) VALUES ('"+name+"','"+email+"','"+password+"','"+mobile+"','"+institute+"')";
							
//							
							
								try {
									try {
										Class.forName("com.mysql.cj.jdbc.Driver");
									} catch (ClassNotFoundException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
						            statement.executeUpdate(Query);
						            dispose();
						            new LoginForm();
								}  catch (SQLException e1) {
						            e1.printStackTrace();
						        }
			            }
					}  catch (SQLException e1) {
			            e1.printStackTrace();
			        }
					
			            
			            
			        
		            
					
				}
				
				
				
			}
			
		});		
		
		
		setVisible(true);
		
		
	}

	
	}




