import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class BorrowList extends JFrame{
	JTable table;
	JPanel operationPanel,tablePanel;
	JTextField nametxt;
	JButton searchbtn,listbtn;
	String id,name,email;
	String[] columnNames = {"id", "Book Name","Email"};
	DefaultTableModel model = new DefaultTableModel(columnNames, 0);
	
	public BorrowList(String user_email) {
		setSize(500,460);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(3);
		setLayout(null);
	    setTitle("Borrowlist");
	    Font labelfont1 = new Font ("Arial", Font.BOLD,20);
	    
	    JLabel headerText = new JLabel("Borrow List");
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
		
		listbtn = new JButton("BookList");
		listbtn.setBounds(370,30,100,30);
		operationPanel.add(listbtn);
	
	
		tablePanel = new JPanel();
		tablePanel.setBounds(0,100,490,300);
		tablePanel.setBackground(Color.DARK_GRAY);
		tablePanel.setLayout(new BorderLayout());
		add(tablePanel);
		 
		table = new JTable(model);
		JScrollPane sc = new JScrollPane(table);
		sc.setBounds(0,0,465,490);
		//tablePanel.add(sc);
		tablePanel.add(sc);
		
		
		String retrieve = "SELECT `id`, `book_name`, `email` FROM `borrows` WHERE `email` = '"+user_email+"'";
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
                String email = rs.getString("email");
                Object data[] = { id, name,email} ;

                model.addRow(data);
            }
	           
            
		}  catch (SQLException e1) {
            e1.printStackTrace();
        }
		
		searchbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				name= nametxt.getText();
				String[] columnNames = {"id", "Name","Email"};
				DefaultTableModel model = new DefaultTableModel(columnNames, 0);
				table = new JTable(model);
				JScrollPane sc = new JScrollPane(table);
				sc.setBounds(0,0,500,490);
				tablePanel.add(sc);
			     String Query = "SELECT `id`, `book_name`, `email` FROM `borrows` WHERE `book_name` LIKE '%"+name+"%'";
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
		                String name = rs.getString("book_name");
		                String email = rs.getString("email");	
		                Object data[] = { id, name,email} ;

		                model.addRow(data);
		            }
		            table = new JTable(model);
//		            JScrollPane sc1 = new JScrollPane(table);
//		    		sc.setBounds(0,0,465,490);
		    		//tablePanel.add(sc);
//		    		tablePanel.add(sc1);
//		            dispose();
//		            new ManagementList();
		    		
			           
		            
				}  catch (SQLException e1) {
		            e1.printStackTrace();
		        }
				//model.removeRow(table.getSelectedRow());
			}
			
		});
		listbtn.addActionListener(new ActionListener() {
			

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				new ManagementList(user_email);
				
			}
			
		});
		setVisible(true);
		
		
	}

}
