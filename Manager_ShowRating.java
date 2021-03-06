package dbdbdib;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Manager_ShowRating extends JFrame {
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	   public static final String USER_UNIVERSITY ="university";
	   public static final String USER_PASSWD ="comp322";
	   Connection conn = null; // Connection object
		Statement stmt = null;   // Statement object
		String sql = "";
		ArrayList<String> customer_id = new ArrayList<>();
		ArrayList<Integer> rate = new ArrayList<>();
		String title= "	";
		public Manager_ShowRating(int title_id)
	{
		try {
	        //Load a JDBC driver for Oracle DBMS
	        Class.forName("oracle.jdbc.driver.OracleDriver");
	        //Get a Connection object
	        System.out.println("Success!");
	     }catch(ClassNotFoundException ee) {
	        System.err.println("error = "+ee.getMessage());
	        System.exit(1);
	     }
	  try {
	       conn = DriverManager.getConnection(URL,USER_UNIVERSITY,USER_PASSWD);
	        System.out.println("디비연결성공");
	     }catch(SQLException ex) {
	        ex.printStackTrace();
	        System.err.println("Cannot get a connection: "+ex.getMessage());
	        System.exit(1);
	     }
	  //DB 연결
	  
	  //해당 movie를 평가한 고객의 id와 점수를 가져옴 
	  try {
		  conn.setAutoCommit(false);
		  stmt = conn.createStatement();
		  sql = "SELECT Rate_id, Score FROM Rating WHERE Mt_id = "+title_id;
		  ResultSet rs = stmt.executeQuery(sql);
		  while(rs.next())
		  {
			  customer_id.add(rs.getString(1));
			  rate.add(rs.getInt(2));
			  
		  }
		  rs.close();
	  }catch(SQLException ex)
	  {
		  System.err.println("sql error = "+ex.getMessage());
		  System.exit(1);
	  }
	  //해당 movie의 title 가져옴
	  try {
		  conn.setAutoCommit(false);
		  stmt = conn.createStatement();
		  sql = "SELECT title FROM MOVIE WHERE title_id = "+title_id;
		  ResultSet rs = stmt.executeQuery(sql);
		  while(rs.next())
		  {
			  title = rs.getString(1);
			  
		  }
		  rs.close();
	  }catch(SQLException ex)
	  {
		  System.err.println("sql error = "+ex.getMessage());
		  System.exit(1);
	  }
		
	  JPanel namepanel = new JPanel();
	  namepanel.add(new JLabel("<< "+title+" 의 평가내역 >>"));
	  
	  JPanel showpanel = new JPanel();
	  showpanel.setLayout(new BoxLayout(showpanel, BoxLayout.Y_AXIS));
	  JScrollPane scroll = new JScrollPane(showpanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
	  for(int i=0;i<customer_id.size();i++)
	  {
		  showpanel.add(new JLabel("ID : "+customer_id.get(i)+", Score : "+rate.get(i)));
	  }
 
	  JButton closebtn = new JButton("닫기");
	  setLayout(new BorderLayout());
	  add(namepanel,BorderLayout.NORTH);
	  add(scroll,BorderLayout.CENTER);
	  add(closebtn,BorderLayout.SOUTH);
	  
	  closebtn.addActionListener(new ActionListener()
      {
      	public void actionPerformed(ActionEvent e)
      	{
      		dispose();
      		
      	}
      });
		
		setVisible(true);
	     setSize(1000,650);
	     setLocationRelativeTo(null);    
	     setResizable(false);
	     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
