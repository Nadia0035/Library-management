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
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class EnrolledStudent extends JFrame {

	JTable table;
	JPanel operationPanel,tablePanel;
	JTextField nametxt;
	JButton searchbtn,deletebtn,checkbtn,booklistbtn;
	Integer id;
	String book_name,email;
	String[] columnNames = {"id", "Student Name","Email"};
	DefaultTableModel model = new DefaultTableModel(columnNames, 0);
	
	public EnrolledStudent() {
		setSize(500,460);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(3);
		setLayout(null);
		setTitle("Admin Panel");
		Font labelfont1 = new Font ("Arial", Font.BOLD,20);
	    
	    JLabel headerText = new JLabel("Student List");
		headerText.setBounds(175,0,460,30);
		headerText.setForeground(Color.BLACK);
		headerText.setFont(labelfont1);
    	add(headerText);
	
	operationPanel = new JPanel();
	operationPanel.setBounds(0,30,500,40);
    operationPanel.setBackground(Color.GRAY);
    operationPanel.setLayout(null);
	add(operationPanel);
	
	nametxt = new JTextField();
	nametxt.setBounds(10,5,350,30);
	operationPanel.add(nametxt);
	
	searchbtn = new JButton("Search");
	searchbtn.setBounds(370,5,100,30);
	operationPanel.add(searchbtn);
	
	deletebtn = new JButton("Delete");
	deletebtn.setBounds(80,380,80,30);
	add(deletebtn);
	
	checkbtn = new JButton("Cheakout");
	checkbtn.setBounds(180,380,100,30);
	add(checkbtn);
	booklistbtn = new JButton("Book List");
	booklistbtn.setBounds(295,380,100,30);
	add(booklistbtn);
	


	tablePanel = new JPanel();
	tablePanel.setBounds(0,70,490,290);
	tablePanel.setBackground(Color.DARK_GRAY);
	tablePanel.setLayout(new BorderLayout());
	add(tablePanel);
	 
	table = new JTable(model);
	JScrollPane sc = new JScrollPane(table);
	sc.setBounds(0,0,465,490);
	tablePanel.add(sc);


	String retrieve = "SELECT `id`, `name`, `email`, `password` FROM `users` WHERE `id` != 1";
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
            String email = rs.getString("email");
            Object data[] = { id, name,email} ;

            model.addRow(data);
        }
           
        
	}  catch (SQLException e1) {
        e1.printStackTrace();
    }
	

	table.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			int rowIndex = table.getSelectedRow();
			id = (Integer) model.getValueAt(rowIndex, 0);
		    
			
		}

	});
	
	searchbtn.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		String	name= nametxt.getText();
			String[] columnNames = {"id", "Name","Email"};
			DefaultTableModel model = new DefaultTableModel(columnNames, 0);
			table = new JTable(model);
			JScrollPane sc = new JScrollPane(table);
			sc.setBounds(0,0,500,490);
			tablePanel.add(sc);
			table = new JTable(model);
		    String Query = "SELECT `id`, `name`, `email` FROM `users` WHERE `id` != 1  AND	`name` like '%"+name+"%' ";
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
	                String name1 = rs.getString("name");
	                String email = rs.getString("email");
	                Object data1[] = { id, name1,email} ;

	                model.addRow(data1);
	            }
	            table = new JTable(model);
	            
			}  catch (SQLException e1) {
	            e1.printStackTrace();
	        }
			
		}
		
	
		
		
	});
	

	checkbtn.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			dispose();
			new Table2();
		}
		
	});
	booklistbtn.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			dispose();
			new Table();
		}
		
	});
	
	deletebtn.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			int row = table.getSelectedRow();
			if(row<0) {
				JOptionPane.showMessageDialog(null,"Select a Row first!");
				return;
			}
		     id = (Integer) model.getValueAt(row, 0);
		     String Query = "DELETE FROM `users` WHERE `id` = '"+id+"'";
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
	            new EnrolledStudent();
	            
	    		
		           
	            
			}  catch (SQLException e1) {
	            e1.printStackTrace();
	        }
			
		}
		
	});

	
	setVisible(true);
	
}

}
			