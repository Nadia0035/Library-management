import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class Table2 extends JFrame {

	JTable table;
	JPanel operationPanel,tablePanel;
	JTextField nametxt,emailtxt;
	JFormattedTextField datetxt;
	JButton addbtn,deletebtn,stlistbtn,datepickbtn,expiredbtn;
	Integer id;
	String book_name,email,date;
	String[] columnNames = {"id", "Book Name","Email","Date"};
	DefaultTableModel model = new DefaultTableModel(columnNames, 0);
	
	public Table2() {
		setSize(500,460);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(3);
		setLayout(null);
		setTitle("Admin Panel");
		 Font labelfont1 = new Font ("Arial", Font.BOLD,20);
		    
		    JLabel headerText = new JLabel("Borrowed Books");
			headerText.setBounds(175,0,460,30);
			headerText.setForeground(Color.BLACK);
			headerText.setFont(labelfont1);
	    	add(headerText);
			
	    

	
		tablePanel = new JPanel();
		tablePanel.setBounds(0,30,490,230);
		tablePanel.setBackground(Color.DARK_GRAY);
		tablePanel.setLayout(new BorderLayout());
		add(tablePanel);
		
		
		operationPanel = new JPanel();
		operationPanel.setBounds(0,210,500,250);
        operationPanel.setBackground(Color.GRAY);
        operationPanel.setLayout(null);
		add(operationPanel);
		
		String retrieve = "SELECT `id`, `book_name`, `email`, `date` FROM `borrows` WHERE 1";
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management","root","" );
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(retrieve);
            while (rs.next()) {
            	int id = rs.getInt("id");
                String name = rs.getString("book_name");
                String category_name = rs.getString("email");
                String date = rs.getString("date");
                Object data[] = { id, name,category_name,date} ;

                model.addRow(data);
            }
	           
            
		}  catch (SQLException e1) {
            e1.printStackTrace();
        }
		  
       

		
		table = new JTable(model);
		JScrollPane sc = new JScrollPane(table);
		sc.setBounds(0,0,450,490);
		tablePanel.add(sc);
		
		JLabel namelabel = new JLabel("BOOK NAME:");
		namelabel.setBounds(60,60,100,30);
		namelabel.setForeground(Color.WHITE);
		
		operationPanel.add(namelabel);
		
		nametxt = new JTextField();
		nametxt.setBounds(150,60,200,30);
		operationPanel.add(nametxt);
		
		JLabel emaillabel = new JLabel("EMAIL :");
		emaillabel.setBounds(70,100,100,30);
		emaillabel.setForeground(Color.WHITE);
		
		operationPanel.add(emaillabel);
		
		emailtxt = new JTextField();
		emailtxt.setBounds(150,100,200,30);
		operationPanel.add(emailtxt);

		JLabel datelabel = new JLabel("Date :");
		datelabel.setBounds(70,140,100,30);
		datelabel.setForeground(Color.WHITE);
		operationPanel.add(datelabel);
		
		try {
			datetxt = new JFormattedTextField();
			datetxt.setValue(LocalDate.now()); //2021-07-23
			datetxt.setBounds(150,140,200,30);
			operationPanel.add(datetxt);
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,"Date is Invalid"); 
        	return;
		}
		
//		datepickbtn = new JButton("Pick Date");
//		datepickbtn.setBounds(360,140,90,30);
//		operationPanel.add(datepickbtn);
		expiredbtn = new JButton("Expired");
		expiredbtn.setBounds(20,180,100,30);
		operationPanel.add(expiredbtn);
		
		addbtn = new JButton("Add");
		addbtn.setBounds(130,180,70,30);
		operationPanel.add(addbtn);
		
		deletebtn = new JButton("Delete");
		deletebtn.setBounds(210,180,80,30);
		operationPanel.add(deletebtn);
		
		stlistbtn = new JButton("Student List");
		stlistbtn.setBounds(300,180,120,30);
		operationPanel.add(stlistbtn);
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int rowIndex = table.getSelectedRow();
				//id = (Integer) model.getValueAt(rowIndex, 0);
			    book_name = (String) model.getValueAt(rowIndex, 1);
			    email = (String) model.getValueAt(rowIndex, 2);
			    //description = (String) model.getValueAt(rowIndex, 3);
				nametxt.setText(book_name);
				emailtxt.setText(email);
			}

			});
		
		expiredbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ExpiredDate();
			}
		});
		addbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				id = idtxt.getText();
				book_name= nametxt.getText();
				email= emailtxt.getText();
				date= datetxt.getText();
				
				
				String nameRegex = "^[#\\+\\-\\_.0-9a-zA-Z\\s,-]+$";
				String emailRegex = "^[a-z0-9]+@[a-z]+.[a-z]+$";
				String dateRegex = "^^([0-9]{4})-([0-9]{2})-([0-9]{2})$";
				if(!Pattern.matches(nameRegex, book_name)) {
					JOptionPane.showMessageDialog(null,"Name = Only 2-30 char is allowed!");
					
				}else if(!Pattern.matches(emailRegex, email)) {
					JOptionPane.showMessageDialog(null,"Email is Invalid!");
				}else if(!Pattern.matches(dateRegex, date)) {
					JOptionPane.showMessageDialog(null,"Date format must be YYYY-MM-dd!");
				}
				else {
//					model = new DefaultTableModel(columnNames, 0);   
					String DataCheck = "SELECT `id`, `name`, `category_name`, `description` FROM `books` WHERE `name` = '"+book_name+"'";
					String UserCheck = "SELECT `id`, `name`, `email`, `password`, `mobile`, `institute` FROM `users` WHERE `email` = '"+email+"'";
					String Query = "INSERT INTO `borrows`(`book_name`, `email`,`date`) VALUES ('"+book_name+"','"+email+"', '"+date+"')";
					try {
						try {
							Class.forName("com.mysql.cj.jdbc.Driver");
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management","root","" );
			            Statement statement = connection.createStatement();
			            ResultSet rs = statement.executeQuery(DataCheck);
			            
			            if (rs.next() == false) { 
			            	JOptionPane.showMessageDialog(null,"Book is not Found"); 
			            	return;
			            }
			            ResultSet rs1 = statement.executeQuery(UserCheck);
			            if(rs1.next() == false) {
			            	JOptionPane.showMessageDialog(null,"User is not Found"); 
			            	return;
			            }
			            
			            	statement.executeUpdate(Query);
				            dispose();
				            new Table2();
			            
			            
			    		
				           
			            
					}  catch (SQLException e1) {
			            e1.printStackTrace();
			        }
				}
			}
		
			});
		deletebtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				book_name= nametxt.getText();
				email= emailtxt.getText();
				int row = table.getSelectedRow();
				if(row<0) {
					JOptionPane.showMessageDialog(null,"Select a Row first!");
					return;
				}
			     id = (Integer) model.getValueAt(row, 0);
			     String Query = "DELETE FROM `borrows` WHERE `id` = '"+id+"'";
				try {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management","root","" );
		            Statement statement = connection.createStatement();
		            statement.executeUpdate(Query);
		            dispose();
		            new Table2();
		    		
			           
		            
				}  catch (SQLException e1) {
		            e1.printStackTrace();
		        }
				//model.removeRow(table.getSelectedRow());
			}
			
		});
		stlistbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				dispose();
				new EnrolledStudent();
			}
			
			
			
			
		});
		setVisible(true);
		
	}
 
}
