import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
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
public class BookDetail extends JFrame{
	JTable table;
	JPanel operationPanel,tablePanel;
	JTextField nametxt,categorytxt,descriptiontxt,url;
	String imagetxt="C:\\Users\\DELL\\Pictures\\Screenshots\\picture_2.png";
	JButton searchbtn,deletebtn,checkbtn,booklistbtn,userbooklistbtn;
	Integer id;
	ImageIcon icon;
	String book_name,email;
	String[] columnNames = {"id", "Book Name","Category Name","Description"};
	DefaultTableModel model = new DefaultTableModel(columnNames, 0);
	
	public BookDetail(int bookId,String image_url, String user_email) {
		setSize(500,460);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(3);
		setLayout(null);
		if(user_email == "nadia@gmail.com") {
			setTitle("Admin Panel");
		}else {
			setTitle("User Panel");
		}
		
		Font labelfont1 = new Font ("Arial", Font.BOLD,20);
	    
	    JLabel headerText = new JLabel("Book Detail");
		headerText.setBounds(175,0,460,30);
		headerText.setForeground(Color.BLACK);
		headerText.setFont(labelfont1);
    	add(headerText);
    	
    	JLabel namelabel = new JLabel("NAME:");
		namelabel.setBounds(90,50,300,30);
		namelabel.setForeground(Color.BLACK);
		
		add(namelabel);
		
		nametxt = new JTextField();
		nametxt.setBounds(150,50,300,30);
		add(nametxt);
	
		JLabel categorylabel = new JLabel("Category:");
		categorylabel.setBounds(90,90,100,30);
		categorylabel.setForeground(Color.BLACK);
		
		add(categorylabel);
		
		categorytxt = new JTextField();
		categorytxt.setBounds(150,90,300,30);
		add(categorytxt);
		
		JLabel descriptionlabel = new JLabel("Description:");
		descriptionlabel.setBounds(70,130,100,80);
		descriptionlabel.setForeground(Color.BLACK);
		
		add(descriptionlabel);
		
		descriptiontxt = new JTextField();
		descriptiontxt.setBounds(150,130,300,80);
		add(descriptiontxt);
		
		 icon = new ImageIcon(new ImageIcon("C:\\Users\\DELL\\Pictures\\library\\"+image_url).getImage().getScaledInstance(200, 150, Image.SCALE_DEFAULT));
		 JLabel jLabel = new JLabel();
		 jLabel.setBounds(150,220,250,150);
	     jLabel.setIcon(icon);
	     add(jLabel);
	     
	    booklistbtn = new JButton("Book List");
	    booklistbtn.setBounds(180,380,100,30);
	 	add(booklistbtn);
	    
//	tablePanel = new JPanel();
//	tablePanel.setBounds(0,30,490,290);
//	tablePanel.setBackground(Color.DARK_GRAY);
//	tablePanel.setLayout(new BorderLayout());
//	add(tablePanel);
	 
//	table = new JTable(model);
//	JScrollPane sc = new JScrollPane(table);
//	sc.setBounds(0,0,465,490);
//	tablePanel.add(sc);


	String retrieve = "SELECT `id`, `image`, `name`, `category_name`, `description` FROM `books` WHERE `id` = '"+bookId+"'";
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
        	nametxt.setText(rs.getString("name")) ;
        	categorytxt.setText(rs.getString("category_name")) ;
        	descriptiontxt.setText(rs.getString("description"));
        	imagetxt = rs.getString("image");
//        	System.out.println(imagetxt);
        }
           
        
	}  catch (SQLException e1) {
        e1.printStackTrace();
    }
	
	


	booklistbtn.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			dispose();
//			System.out.println(user_email);
			if(user_email == "nadia@gmail.com") {
				new Table();
			}else {
				new ManagementList(user_email);
			}
			
		}
		
	});
	


	
	setVisible(true);
}
}
