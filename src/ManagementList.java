
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

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class ManagementList extends JFrame {
	JTable table;
	JPanel operationPanel,tablePanel,tablePanel1;
	JTextField nametxt;
	JButton searchbtn,borrowbtn;
	String id,name;
	String[] columnNames = {"id", "Name","Category Name","Description"};
	DefaultTableModel model = new DefaultTableModel(columnNames, 0);

	public ManagementList(String email) {
		setSize(500,460);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(3);
		setLayout(null);
		setTitle("User Panel");
		
		 Font labelfont1 = new Font ("Arial", Font.BOLD,20);
		    
		    JLabel headerText = new JLabel("Book List");
			headerText.setBounds(175,0,460,30);
			headerText.setForeground(Color.BLACK);
			headerText.setFont(labelfont1);
	    	add(headerText);
		
		operationPanel = new JPanel();
		operationPanel.setBounds(0,30,500,60);
        operationPanel.setBackground(Color.GRAY);
        operationPanel.setLayout(null);
		add(operationPanel);
		
		nametxt = new JTextField();
		nametxt.setBounds(10,5,350,30);
		operationPanel.add(nametxt);
		
		searchbtn = new JButton("Search");
		searchbtn.setBounds(370,0,100,30);
		operationPanel.add(searchbtn);
		
		borrowbtn = new JButton("Borrowed");
		borrowbtn.setBounds(370,30,100,30);
		operationPanel.add(borrowbtn);
	
	
		tablePanel = new JPanel();
		tablePanel.setBounds(0,90,490,320);
		tablePanel.setBackground(Color.DARK_GRAY);
		tablePanel.setLayout(new BorderLayout());
		add(tablePanel);
		 
		table = new JTable(model);
		JScrollPane sc = new JScrollPane(table);
		sc.setBounds(0,0,465,490);
		//tablePanel.add(sc);
		tablePanel.add(sc);

		
//		Object data [][]= {{"1","Abir","abir@gamil.com"},{"2","wasim","wasim@gmail.com"},{"3","sumon","sumon@gmail.com"},{"4","Abid","abid@gamil.com"}};
//		String columnNames[] = {"Id", "Name" , "Email"};
		String retrieve = "SELECT `id`, `name`, `category_name`, `description` FROM `books` WHERE 1";
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
		
	
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
				int rowIndex = table.getSelectedRow();
				System.out.println(rowIndex);
//				id = model.getValueAt(rowIndex, 0);
				if(rowIndex == -1) {
					rowIndex=0;
				}
				int selectId = (Integer) model.getValueAt(rowIndex, 0);
				String Query = "SELECT `id`, `image`, `name`, `category_name`, `description` FROM `books` WHERE `id`= '"+selectId+"' ";
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
		            dispose();
		            new BookDetail(selectId,image_url,email);
		    		
			           
		            
				}  catch (SQLException e1) {
		            e1.printStackTrace();
		        }
				
			}

		});
		searchbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				name= nametxt.getText();
				if(name=="") {
					dispose();
					new ManagementList(email);
					return;
				}
				model.setRowCount(0);
				table = new JTable(model);
				
			    String Query = "SELECT `id`, `name`, `category_name`, `description` FROM `books` WHERE `name` LIKE '%"+name+"%'";
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
		            while (rs.next()) {
		            	int id = rs.getInt("id");
		                String name = rs.getString("name");
		                String category_name = rs.getString("category_name");
		                String description = rs.getString("description");
		                Object data1[] = { id, name,category_name,description} ;

		                model.addRow(data1);
		            }
		            table = new JTable(model);
	           
		    		
			           
		            
				}  catch (SQLException e1) {
		            e1.printStackTrace();
		        }
				//model.removeRow(table.getSelectedRow());
			}
			
		});
		
		borrowbtn.addActionListener(new ActionListener() {
			

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				new BorrowList(email);
				
			}
			
		});
		setVisible(true);
		
	}

}
