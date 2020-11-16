package dbdbdib;
import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class ShowEp extends JFrame{
	
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	   public static final String USER_UNIVERSITY ="university";
	   public static final String USER_PASSWD ="comp322";
	   Connection conn = null; // Connection object
		Statement stmt = null;   // Statement object
		String sql = "";
		String title="";
		ArrayList<Integer> Season_number = new ArrayList<>();
		ArrayList<Integer> Episode_number = new ArrayList<>();
		ArrayList<String> Episode_title = new ArrayList<>();
	public ShowEp(int title_id) {
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
	  //title_id에 따른 title불러오기 
	  
	  JPanel intropanel = new JPanel();
	  JLabel introlabel = new JLabel("<< "+title+"'s Episodes >>");
	  intropanel.add(introlabel);
	  
	  
	  
	  try {
		  conn.setAutoCommit(false);
		  stmt = conn.createStatement();
		  sql = "SELECT Season_number, Episode_number, Episode_title FROM EPISODE WHERE Parent_id = "+title_id;
		  ResultSet rs = stmt.executeQuery(sql);
		  while(rs.next())
		  {
			 Season_number.add(rs.getInt(1));
			 Episode_number.add(rs.getInt(2));
			 Episode_title.add(rs.getString(3));
		  }
		  rs.close();
	  }catch(SQLException ex)
	  {
		  System.err.println("sql error = "+ex.getMessage());
		  System.exit(1);
	  }
	  //title_id 에 따른 episode 불러오기 
		
	  int cnt = Season_number.size();
	  
	  JPanel showpanel = new JPanel(new GridLayout(cnt,1));
	  for(int i=0;i<cnt;i++)
	  {
		  showpanel.add(new JLabel("Season"+Season_number.get(i)+"-"+Episode_number.get(i)+" : "+Episode_title.get(i)));
	  }
		
	  JButton closebtn = new JButton("닫기");
	  
	  closebtn.addActionListener(new ActionListener()
      {
      	public void actionPerformed(ActionEvent e)
      	{
      		dispose();
      	}
      });
	  
	  setLayout(new BorderLayout());
	  add(intropanel,BorderLayout.NORTH);
	  add(showpanel,BorderLayout.CENTER);
	  add(closebtn,BorderLayout.SOUTH);
	
		
		setVisible(true);
		setSize(1000,650);
		setLocationRelativeTo(null); 		//윈도우를 컴퓨터 중간에 띄우기
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
}
