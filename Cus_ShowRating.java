
package teamJDBC;

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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Cus_ShowRating extends JFrame{
       
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	public static final String USER_UNIVERSITY ="university";
	public static final String USER_PASSWD ="comp322";
	Connection conn = null; // Connection object
	Statement stmt = null;   // Statement object
	String sql ="";
	ArrayList<String> my_Titleid = new ArrayList<>();
	ArrayList<Integer> my_Score = new ArrayList<>();
	ArrayList<String> my_TitleResult = new ArrayList<>();
	
	String name ="";          // '누구'님의 평가목록
	String rated_title = "";  // 평가한 영화의 제목
	int rated_score = 0;      // 이 영화를 얼만큼 봤는지
	
	public Cus_ShowRating(String id) 
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
		     }catch(SQLException ex) {
		        ex.printStackTrace();
		        System.err.println("Cannot get a connection: "+ex.getMessage());
		        System.exit(1);
		     }
		 
		// name 찾기
		try {
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			
			sql = "select name from account where account_id = '"+id+"'";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				name = rs.getString(1);
			}
			
		}catch(SQLException ex2)
		  {
			  System.err.println("sql0 error");
			  System.exit(1);
		  }
		
		// title_id랑 score 찾기
				try {
					conn.setAutoCommit(false);
					stmt = conn.createStatement();
					
					sql = "select Mt_id, score from rating where Rate_id = '"+id+"'";
					ResultSet rs = stmt.executeQuery(sql);
					
					while(rs.next())
					{
						my_Titleid.add(rs.getString(1));
						my_Score.add(rs.getInt(2));
					}
					
				}catch(SQLException ex2)
				  {
					  System.err.println("sql1 error");
					  System.exit(1);
				  }
				
				// title 이름 가져오기
				try {
					conn.setAutoCommit(false);
					stmt = conn.createStatement();
					
					for(int i = 0; i < my_Titleid.size(); i++)
					{
					    sql = "select title from movie where title_id = '"+my_Titleid.get(i)+"' ";
					    ResultSet rs = stmt.executeQuery(sql);
					
					   while(rs.next())
					   {
						   my_TitleResult.add(rs.getString(1));
					   }
					}
				}catch(SQLException ex2)
				  {
					  System.err.println("sql2 error");
					  System.exit(1);
				  }
				
		
		JPanel titlepanel = new JPanel(new BorderLayout());
		titlepanel.add(new JLabel("<< "+name+"의 평가내역 >>"));
		
		 JPanel showpanel = new JPanel();
		 showpanel.setLayout(new BoxLayout(showpanel, BoxLayout.Y_AXIS));
		 JScrollPane scroll = new JScrollPane(showpanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		for(int i = 0; i < my_Titleid.size(); i++)
		{
			showpanel.add(new JLabel("영화 제목 : "+ my_TitleResult.get(i)+", 평가 내역 : "+ my_Score.get(i)));
		}
		
		JButton closeBtn = new JButton("닫기");
		setLayout(new BorderLayout());
		add(titlepanel,BorderLayout.NORTH);
		add(scroll,BorderLayout.CENTER);
		add(closeBtn,BorderLayout.SOUTH);
		
		closeBtn.addActionListener(new ActionListener()
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





