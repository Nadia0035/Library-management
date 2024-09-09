import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.*;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class Table extends JFrame{

	JTable table;
	JPanel operationPanel,tablePanel;
	JTextField idtxt,nametxt,categoryNametxt,descriptiontxt,imagetxt;
	String imagepath ="";
	File imageFile;
	JButton addbtn,updatebtn,deletebtn,stlistbtn,addImagebtn,bookdetailbtn;
	Integer id;
	String name,categoryName,description;

	String[] columnNames = {"id", "Name","Category Name","Description"};
	DefaultTableModel model = new DefaultTableModel(columnNames, 0);
	
	
	public Table() {
		setSize(500,460);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(3);
		setLayout(null);
		setTitle("Admin Panel");
		
		 Font labelfont1 = new Font ("Arial", Font.BOLD,20);
		    
		    JLabel headerText = new JLabel("Book List");
			headerText.setBounds(175,0,460,30);
			headerText.setForeground(Color.BLACK);
			headerText.setFont(labelfont1);
	    	add(headerText);
			
	    
		tablePanel = new JPanel();
		tablePanel.setBounds(0,30,490,210);
		tablePanel.setBackground(Color.DARK_GRAY);
		tablePanel.setLayout(new BorderLayout());
		add(tablePanel);
		

		operationPanel = new JPanel();
		operationPanel.setBounds(0,200,500,250);
        operationPanel.setBackground(Color.GRAY);
        operationPanel.setLayout(null);
		add(operationPanel);
		
		String retrieve = "SELECT `id`,`image`, `name`, `category_name`, `description` FROM `books` WHERE 1";
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
                String name = rs.getString("name");
                String category_name = rs.getString("category_name");
                String description = rs.getString("description");
                Object data[] = { id, name,category_name,description} ;

                model.addRow(data);
            }
	           
            
		}  catch (SQLException e1) {
            e1.printStackTrace();
        }
		  
       
		
		table = new JTable(model);
		JScrollPane sc = new JScrollPane(table);
		sc.setBounds(0,0,465,290);
		tablePanel.add(sc);
		
		JLabel namelabel = new JLabel("NAME:");
		namelabel.setBounds(90,50,100,30);
		namelabel.setForeground(Color.WHITE);
		
		operationPanel.add(namelabel);
		
		nametxt = new JTextField();
		nametxt.setBounds(150,50,200,30);
		operationPanel.add(nametxt);
		

		addImagebtn = new JButton("Image");
		addImagebtn.setBounds(360,50,70,30);
		operationPanel.add(addImagebtn);

		JLabel categorylabel = new JLabel("CATEGORY:");
		categorylabel.setBounds(70,90,100,30);
		categorylabel.setForeground(Color.WHITE);
		
		operationPanel.add(categorylabel);
		
		categoryNametxt = new JTextField();
		categoryNametxt.setBounds(150,90,200,30);
		operationPanel.add(categoryNametxt);
		
		JLabel descriptionlabel = new JLabel("DESCRIPTION:");
		descriptionlabel .setBounds(60,130,100,30);
		descriptionlabel .setForeground(Color.WHITE);
		
		operationPanel.add(descriptionlabel );
		
		descriptiontxt = new JTextField();
		descriptiontxt.setBounds(150,130,200,30);
		operationPanel.add(descriptiontxt);
		
		bookdetailbtn = new JButton("Detail");
		bookdetailbtn.setBounds(15,180,70,30);
		operationPanel.add(bookdetailbtn);
		
		addbtn = new JButton("Add");
		addbtn.setBounds(95,180,70,30);
		operationPanel.add(addbtn);
		
		updatebtn = new JButton("Update");
		updatebtn.setBounds(180,180,80,30);
		operationPanel.add(updatebtn);
		
		deletebtn = new JButton("Delete");
		deletebtn.setBounds(275,180,80,30);
		operationPanel.add(deletebtn);
		
		stlistbtn = new JButton("StudentList");
		stlistbtn.setBounds(365,180,100,30);
		operationPanel.add(stlistbtn);
		
		
		bookdetailbtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				name= nametxt.getText();
				categoryName= categoryNametxt.getText();
				int row = table.getSelectedRow();
				if(row<0) {
					JOptionPane.showMessageDialog(null,"Select a Row first!");
					return;
				}
				int selectedId = (Integer) model.getValueAt(row, 0);
				String Query = "SELECT `id`, `image`, `name`, `category_name`, `description` FROM `books` WHERE `id`= '"+selectedId+"' ";
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
		            String image_url = "";
		            while (rs.next()) {
		            	image_url = rs.getString("image");
		            }
//		            System.out.println(image_url);
		            imagepath = "";
		            dispose();
		            new BookDetail(selectedId,image_url,"nadia@gmail.com");
		    		
			           
		            
				}  catch (SQLException e1) {
		            e1.printStackTrace();
		        }
			}
		});
		addImagebtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JFileChooser j = new JFileChooser("C:\\Users\\DELL\\Pictures\\library");
				int r = j.showSaveDialog(null);
				 if (r == JFileChooser.APPROVE_OPTION)
					 
		            {
					 	imageFile = j.getSelectedFile();
		              
		                imagepath = imageFile.getAbsolutePath();
//		                System.out.println(imagepath);
		            }
		           
		            else {
		            	JOptionPane.showMessageDialog(null,"Image is Required");
		            }
		            	
			}
		});
		addbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				id = idtxt.getText();
				
				name= nametxt.getText();
				categoryName= categoryNametxt.getText();
				description	= descriptiontxt.getText();
				String nameRegex = "^[#\\+\\-\\_.0-9a-zA-Z\\s,-]+$";
				String bookRegex = "/^[A-Za-z][0-9]\\s\\+_,\\.;:()]+$/";
				String desRegex = "^[#\\+\\-\\_.a-zA-Z\\s]{5,300}$";
				if(imagepath == "") {
					JOptionPane.showMessageDialog(null,"Image is Required");
					return;
				}
				if(!Pattern.matches(nameRegex, name)) {
					JOptionPane.showMessageDialog(null,"Name = Only 2-30 char is allowed");
					
				}else if(!Pattern.matches(nameRegex, categoryName)) {
					JOptionPane.showMessageDialog(null,"Category Name = Only 2-30 char is allowed");
				}else if(!Pattern.matches(desRegex, description)) {
					JOptionPane.showMessageDialog(null,"Description = Only 5-300 char is allowed");
				}
				else {   
					String newStr = imagepath.replace("C:\\Users\\DELL\\Pictures\\library","");
					//System.out.println(newStr);
					String Query = "INSERT INTO `books`(`image`,`name`, `category_name`, `description`) VALUES ('"+newStr+"','"+name+"','"+categoryName+"','"+description+"')";
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
			            imagepath = "";
			            dispose();
			            new Table();
			    		
				           
			            
					}  catch (SQLException e1) {
			            e1.printStackTrace();
			        }
				}
		
				
			}
			
		});

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int rowIndex = table.getSelectedRow();
				id = (Integer) model.getValueAt(rowIndex, 0);
			    name = (String) model.getValueAt(rowIndex, 1);
			    categoryName = (String) model.getValueAt(rowIndex, 2);
			    description = (String) model.getValueAt(rowIndex, 3);
				nametxt.setText(name);
				categoryNametxt.setText(categoryName);
				descriptiontxt.setText(description);
			}

			});
		
		updatebtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				name= nametxt.getText();
				categoryName= categoryNametxt.getText();
				description = descriptiontxt.getText();
				String nameRegex = "^[#\\+\\-\\_.0-9a-zA-Z\\s,-]+$";
				String bookRegex = "/^[a-zA-Z0-9.'\\-_\\s]$/";
				String desRegex = "^[#\\+\\-\\_.a-zA-Z\\s]{5,300}$";
				if(!Pattern.matches(nameRegex, name)) {
					JOptionPane.showMessageDialog(null,"Name = Only 2-30 char is allowed");
					
				}else if(!Pattern.matches(nameRegex, categoryName)) {
					JOptionPane.showMessageDialog(null,"Category Name = Only 2-30 char is allowed");
				}else if(!Pattern.matches(desRegex, description)) {
					JOptionPane.showMessageDialog(null,"Description = Only 5-60 char is allowed");
				}else {
					 int row = table.getSelectedRow();
				     id = (Integer) model.getValueAt(row, 0);
				     String Query ="";
					if(imagepath == "") {
						Query = "UPDATE `books` SET `id`='"+id+"',`name`='"+name+"',`category_name`='"+categoryName+"',`description`='"+description+"' WHERE `id` = '"+id+"'";
					}else {
						String newStr = imagepath.replace("C:\\Users\\DELL\\Pictures\\library","");
//						System.out.println(newStr);
						Query = "UPDATE `books` SET `id`='"+id+"',`image`='"+newStr+"' ,`name`='"+name+"',`category_name`='"+categoryName+"',`description`='"+description+"' WHERE `id` = '"+id+"'";
					}
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
			            new Table();
			    		
				           
			            
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
				name= nametxt.getText();
				categoryName= categoryNametxt.getText();
				int row = table.getSelectedRow();
				if(row<0) {
					JOptionPane.showMessageDialog(null,"Select a Row first!");
					return;
				}
			     id = (Integer) model.getValueAt(row, 0);
			     String Query = "DELETE FROM `books` WHERE `id` = '"+id+"'";
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
		            new Table();
		    		
			           
		            
				}  catch (SQLException e1) {
		            e1.printStackTrace();
		        }
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
