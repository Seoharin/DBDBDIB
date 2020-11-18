package dbdbdib;

import java.awt.BorderLayout;
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

public class AddEP extends JFrame{
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
		ArrayList<Integer> id = new ArrayList<>();
	public AddEP(int title_id, String Account_id) {
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
	  
	  JPanel center = new JPanel(new GridLayout(3,1));
	  JPanel showpanel = new JPanel(new GridLayout(cnt,1));
	  for(int i=0;i<cnt;i++)
	  {
		  showpanel.add(new JLabel("Season"+Season_number.get(i)+"-"+Episode_number.get(i)+" : "+Episode_title.get(i)));
	  }
	  
	  JPanel addpanel = new JPanel(new GridLayout(3,1));
	  JPanel snumpanel = new JPanel();
	  JLabel snumlabel = new JLabel("Season number : ");
	  JTextField snumfield = new JTextField(5);
	  snumpanel.add(snumlabel);
	  snumpanel.add(snumfield);
	  
	  JPanel enumpanel = new JPanel();
	  JLabel enumlabel = new JLabel("Episode number : ");
	  JTextField enumfield = new JTextField(5);
	  enumpanel.add(enumlabel);
	  enumpanel.add(enumfield);
	  
	  JPanel etpanel = new JPanel();
	  JLabel etlabel = new JLabel("Episode title : ");
	  JTextField etfield = new JTextField(30);
	  etpanel.add(etlabel);
	  etpanel.add(etfield);
	  
	  addpanel.add(snumpanel);
	  addpanel.add(enumpanel);
	  addpanel.add(etpanel);
	  
	  center.add(showpanel);
	  center.add(new JLabel("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"));
	  center.add(addpanel);
	  
	  JPanel btns = new JPanel();
	  JButton addbtn = new JButton("추가하기");
	  JButton closebtn = new JButton("닫기");
	  btns.add(addbtn);
	  btns.add(closebtn);
	  
	
	  
	  setLayout(new BorderLayout());
	  add(intropanel,BorderLayout.NORTH);
	  add(center,BorderLayout.CENTER);
	  add(btns,BorderLayout.SOUTH);
	
		
		setVisible(true);
		setSize(1000,650);
		setLocationRelativeTo(null); 		//윈도우를 컴퓨터 중간에 띄우기
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		 closebtn.addActionListener(new ActionListener()
	     {
	     	public void actionPerformed(ActionEvent e)
	     	{
	     		dispose();
	     		new Update_info(Account_id,title_id);
	     	}
	     });
		 
		 addbtn.addActionListener(new ActionListener()
	     {
	     	public void actionPerformed(ActionEvent e)
	     	{
	     		
	     		 try {
	     			  conn.setAutoCommit(false);
	     			  stmt = conn.createStatement();
	     			  sql = "SELECT Episode_id FROM EPISODE";
	     			  ResultSet rs = stmt.executeQuery(sql);
	     			  while(rs.next())
	     			  {
	     				 id.add(rs.getInt(1));
	     			  }
	     			  rs.close();
	     		  }catch(SQLException ex)
	     		  {
	     			  System.err.println("sql error = "+ex.getMessage());
	     			  System.exit(1);
	     		  }
	     		 
	     		 
	     		try {
	     			  String snum = snumfield.getText();
	     			  String epnum =enumfield.getText();
	     			  String eptitle = etfield.getText();
	     			  int epid = id.get(id.size()-1)+1;
	     			  conn.setAutoCommit(false);
	     			  stmt = conn.createStatement();
	     			  sql = "INSERT INTO EPISODE VALUES("+epid+","+snum+","
	     					  +epnum+",'"+eptitle+"',"+title_id+")";
	     			 int res = stmt.executeUpdate(sql);
	 					conn.commit();
	     		  }catch(SQLException ex)
	     		  {
	     			  System.err.println("sql error = "+ex.getMessage());
	     			  System.exit(1);
	     		  }
	     		  //title_id 에 따른 episode 불러오기 
	     		JOptionPane.showMessageDialog(null, "추가완료.");
	     		dispose();
	     		new Update_info(Account_id,title_id);
	     	}
	     });
		
	}
	
	
	
}
