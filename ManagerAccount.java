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

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

public class ManagerAccount extends JFrame {

	public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	   public static final String USER_UNIVERSITY ="university";
	   public static final String USER_PASSWD ="comp322";
	   Connection conn = null; // Connection object
		Statement stmt = null;   // Statement object
		String sql = "";
		String name = ""; //관리자의 이름
		ArrayList<Integer> movie_list = new ArrayList<>(); //내가올린 영상물의 title_id
		ArrayList<String> movies_name = new ArrayList<>(); //내가올린 영상물의 title
		ArrayList<String> movies_rate = new ArrayList<>(); //내가 올린 영상물의 평점
		ArrayList<JRadioButton> btnlist = new ArrayList<>(); // 버튼들의 list
	public ManagerAccount(String id)
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
	//관리자의 이름 읽어오기
	  try {
		  conn.setAutoCommit(false);
		  stmt = conn.createStatement();
		  sql = "SELECT name FROM ACCOUNT WHERE Account_id = '"+id+"'";
		  ResultSet rs = stmt.executeQuery(sql);
		  while(rs.next())
		  {
			  name = rs.getString(1);
		  }
		  rs.close();
	  }catch(SQLException ex)
	  {
		  System.err.println("sql error = "+ex.getMessage());
		  System.exit(1);
	  }
	  
	  
		
		
	 JPanel titlepanel = new JPanel();
     JLabel titlelabel = new JLabel("<<관리자 : "+name+" >>");
     titlepanel.add(titlelabel);
     
    
    
     //내가 올린 영상물의 title_id받아오기
     try {
		  conn.setAutoCommit(false);
		  stmt = conn.createStatement();
		  sql = "SELECT title_id FROM MOVIE WHERE admin_id = '"+id+"'";
		  
		  ResultSet rs = stmt.executeQuery(sql);
		  while(rs.next())
		  {
			  int title_id = rs.getInt(1);
			  //평점 가져오기
			  movie_list.add(title_id);
			  try {
				  conn.setAutoCommit(false);
				  stmt = conn.createStatement();
				  
				  sql = "SELECT Mt_id, AVG(Score) FROM RATING"
						  +" WHERE Mt_id = "+title_id+" GROUP BY Mt_id";
				  
				  ResultSet rs2 = stmt.executeQuery(sql);
				  if(rs2.next())
				  {
					  String score = rs2.getString(2);
					  if(score.length()>4)
					  {
						  movies_rate.add(score.substring(0, 3));
					  }
					  else
					  {
						  movies_rate.add(score);
					  }
				  }
				  else {
					  movies_rate.add("0");
				  }
				  rs2.close();
			  }catch(SQLException ex)
			  {
				  System.err.println("sql error = "+ex.getMessage());
				  System.exit(1);
			  }
			  //title가져오기
			  try {
				  conn.setAutoCommit(false);
				  stmt = conn.createStatement();
				  
				  sql = "SELECT title FROM MOVIE"
						  +" WHERE title_id = "+title_id;
				  ResultSet rs2 = stmt.executeQuery(sql);
				  while(rs2.next())
				  {
					  movies_name.add(rs2.getString(1));
					  
				  }
				  rs2.close();
			  }catch(SQLException ex)
			  {
				  System.err.println("sql error = "+ex.getMessage());
				  System.exit(1);
			  }
			 
		  }
		  rs.close();
	  }catch(SQLException ex)
	  {
		  System.err.println("sql error = "+ex.getMessage());
		  System.exit(1);
	  }
     
   
     JPanel center = new JPanel(new BorderLayout());
     JPanel showpanel = new JPanel();
     showpanel.setLayout(new BoxLayout(showpanel, BoxLayout.Y_AXIS));
     ButtonGroup movielist = new ButtonGroup();
     JScrollPane scroll = new JScrollPane(showpanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
     
     JPanel btn = new JPanel();
     JButton updatebtn = new JButton("영상 정보 수정");
     JButton ratinginfobtn = new JButton("평가 내역 보기");
     btn.add(updatebtn);
     btn.add(ratinginfobtn);
     
     for(int i=0;i<movie_list.size();i++)
		{
			//평가하지 않은 movie들을 radiobutton으로 추가
    	    btnlist.add(new JRadioButton(movie_list.get(i)+"-"+movies_name.get(i)+", 평점: "+movies_rate.get(i)));
			movielist.add(btnlist.get(i));
			showpanel.add(btnlist.get(i));
		}
     
     center.add(new JLabel("내가올린 영상물"),BorderLayout.NORTH);
     center.add(scroll,BorderLayout.CENTER);
     center.add(btn,BorderLayout.SOUTH);
     
     JPanel btns = new JPanel();
     JButton addbtn = new JButton("새로운 영상물 등록");
     JButton logoutbtn = new JButton("로그 아웃");
     btns.add(addbtn);
     btns.add(logoutbtn);
     
     setLayout(new BorderLayout());
     add(titlepanel,BorderLayout.NORTH);
     add(center,BorderLayout.CENTER);
     add(btns,BorderLayout.SOUTH);
     
     
     setVisible(true);
     setSize(1000,650);
     setLocationRelativeTo(null);    
     setResizable(false);
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     
     
     updatebtn.addActionListener(new ActionListener()
     { //해당 영상물 정보 수정
     	public void actionPerformed(ActionEvent e)
     	{
     		for(int i=0;i<movie_list.size();i++)
     		{
     			if(btnlist.get(i).isSelected())
     			{
     				dispose();
     				new Update_info(id,movie_list.get(i));
     			}
     		}
     	}
     });
     
     ratinginfobtn.addActionListener(new ActionListener()
     { //해당 영상물의 평가내역을 보여주는 새로운 창 생성
     	public void actionPerformed(ActionEvent e)
     	{
     		for(int i=0;i<movie_list.size();i++)
     		{
     			if(btnlist.get(i).isSelected())
     			{
     				new Show_Rating(movie_list.get(i));
     			}
     		}
     	}
     });
     
     addbtn.addActionListener(new ActionListener()
     { //새로운 영상물을 등록하는 class 호출
     	public void actionPerformed(ActionEvent e)
     	{
     		dispose();
     		new AddNewMovie(id);
     	}
     });
	
     
     logoutbtn.addActionListener(new ActionListener()
     {
     	public void actionPerformed(ActionEvent e)
     	{
     		dispose();
     		new Login();
     	}
     });
	}
}
