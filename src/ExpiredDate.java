
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
public class ExpiredDate extends JFrame {

	JTable table;
	JPanel operationPanel,tablePanel;
	JTextField nametxt,emailtxt;
	JFormattedTextField datetxt;
	JButton addbtn,deletebtn,stlistbtn,datepickbtn;
	Integer id;
	String book_name,email,date;
	String[] columnNames = {"id", "Book Name","Email","Date"};
	DefaultTableModel model = new DefaultTableModel(columnNames, 0);
	
	public ExpiredDate() {
		setSize(500,460);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(3);
		setLayout(null);
		setTitle("Admin Panel");
		 Font labelfont1 = new Font ("Arial", Font.BOLD,20);
		    
		    JLabel headerText = new JLabel("Expire Date");
			headerText.setBounds(175,0,460,30);
			headerText.setForeground(Color.BLACK);
			headerText.setFont(labelfont1);
	    	add(headerText);
			
	    

	
		tablePanel = new JPanel();
		tablePanel.setBounds(0,30,490,340);
		tablePanel.setBackground(Color.DARK_GRAY);
		tablePanel.setLayout(new BorderLayout());
		add(tablePanel);
		
		
		operationPanel = new JPanel();
		operationPanel.setBounds(0,320,500,250);
        operationPanel.setBackground(Color.GRAY);
        operationPanel.setLayout(null);
		add(operationPanel);
		LocalDate lt = LocalDate.now(); // 2024-01-18
		String retrieve = "SELECT `id`, `book_name`, `email`, `date` FROM `borrows` WHERE `date` < '"+lt+"'";
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
		
		deletebtn = new JButton("Delete");
		deletebtn.setBounds(130,60,80,30);
		operationPanel.add(deletebtn);
		
		stlistbtn = new JButton("Student List");
		stlistbtn.setBounds(230,60,140,30);
		operationPanel.add(stlistbtn);
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int rowIndex = table.getSelectedRow();
				//id = (Integer) model.getValueAt(rowIndex, 0);
			    book_name = (String) model.getValueAt(rowIndex, 1);
			    email = (String) model.getValueAt(rowIndex, 2);
			    //description = (String) model.getValueAt(rowIndex, 3);
				
			}

			});
		
		deletebtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
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
