package dbdbdib;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
public class MovieList extends JFrame{
	
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	   public static final String USER_UNIVERSITY ="university";
	   public static final String USER_PASSWD ="comp322";
	   Connection conn = null; // Connection object
		Statement stmt = null;   // Statement object
		ArrayList non_rate_movie = new ArrayList<Integer>();
		ArrayList btnlist = new ArrayList<JRadioButton>();
		String username="";
	public MovieList(String id) {
		// TODO Auto-generated method stub

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
		     } //DB랑 연결
		  
		  
		//DB에서 MOVIE리스트 가져오기
		  
		try {
			 conn.setAutoCommit(false);
			 stmt = conn.createStatement();
			
			
			 String sql = "SELECT title_id FROM MOVIE"
					+ " minus"
					+ " SELECT title_id FROM MOVIE,RATING WHERE title_id ="
					+ " MT_id AND Rate_id = '"+id+"'";
			 ResultSet rs = stmt.executeQuery(sql);
			 int title_id;
			 while(rs.next()) {
				 title_id =rs.getInt(1);
				 non_rate_movie.add(title_id);
				 //평가하지 않은 movie들의 title_id를 arraylist non_rate_movie에 저장
			 }
			 
			 rs.close();
		}catch(SQLException ex2)
		{
			System.err.println("sql error = "+ex2.getMessage());
			System.exit(1);
		}
		 //사용자 이름 받아오기
		try {
			 conn.setAutoCommit(false);
			 stmt = conn.createStatement();
			
			
			 String sql = "SELECT name FROM ACCOUNT WHERE Account_id = '"+id+"'";
			 ResultSet rs = stmt.executeQuery(sql);
			 
			 while(rs.next()) {
				 username = rs.getString(1);
			 }
			 
			 rs.close();
		}catch(SQLException ex2)
		{
			System.err.println("sql error = "+ex2.getMessage());
			System.exit(1);
		}
		
		JMenuBar mb = new JMenuBar();
		JMenu menu = new JMenu("MENU");
		
		JMenuItem search_name = new JMenuItem("제목으로 검색");
		JMenuItem search_condition = new JMenuItem("조건으로 검색");
		JMenuItem go_mypage = new JMenuItem("My page");
		JMenuItem logout = new JMenuItem("Logout");
		
		menu.add(search_name);
		menu.add(search_condition);
		menu.add(go_mypage);
		menu.add(logout);
		
		mb.add(menu);
		
		JPanel welcomepanel = new JPanel(new GridLayout(2,1));
		JLabel name = new JLabel(username+"님 환영합니다!");
		JLabel show = new JLabel(":::MOVIE 목록:::");
		welcomepanel.add(name);
		welcomepanel.add(show);
		JPanel movielistpanel = new JPanel();
		movielistpanel.setLayout(new BoxLayout(movielistpanel, BoxLayout.Y_AXIS));
		ButtonGroup movielist = new ButtonGroup();
		JScrollPane scroll = new JScrollPane(movielistpanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		for(int i=0;i<non_rate_movie.size();i++)
		{
			//평가하지 않은 movie들을 radiobutton으로 추가
			try {
				 conn.setAutoCommit(false);
				 stmt = conn.createStatement();
				
				
				 String sql = "SELECT title FROM MOVIE WHERE title_id = "
						+ non_rate_movie.get(i);
				 //title_id 에 맞는 title들 가져오기
				 ResultSet rs = stmt.executeQuery(sql);
				 while(rs.next()) {
					 String title = rs.getString(1);
					 btnlist.add(new JRadioButton(non_rate_movie.get(i)+"-"+title));
					 movielist.add((AbstractButton) btnlist.get(i));
					 movielistpanel.add((AbstractButton) btnlist.get(i));
					 //평가하지 않은 movie들의 title_id를 arraylist non_rate_movie에 저장
				 }
				 
				 rs.close();
			}catch(SQLException ex2)
			{
				System.err.println("sql error = "+ex2.getMessage());
				System.exit(1);
			}
			
		}
		JButton showdetailbtn = new JButton("상세보기");
		
		//JTextArea showmovie = new JTextArea();
		//JScrollPane sp = new JScrollPane(showmovie);
		
		
		setJMenuBar(mb);
		setLayout(new BorderLayout());
		add(welcomepanel,BorderLayout.NORTH);
		add(scroll,BorderLayout.CENTER);
		add(showdetailbtn,BorderLayout.SOUTH);
		
		setVisible(true);
		setSize(1000,650);
		setLocationRelativeTo(null); 		//윈도우를 컴퓨터 중간에 띄우기
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		showdetailbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				//선택한 영화의 title_id 를 인자로
				//상세페이지 호출
				String str = "";
				for(int i=0;i<btnlist.size();i++)
				{	//버튼들 중 체크된 버튼의 문자열 가져오기
					if(((AbstractButton) btnlist.get(i)).isSelected())
					{
						str = ((AbstractButton) btnlist.get(i)).getText();
					}
				}
				//가져온 문자열에서 title_id를 추출함
				StringTokenizer token = new StringTokenizer(str,"-");
				String data = token.nextToken();
				
				new Moredeep(Integer.parseInt(data));
				
			}
		});
		
		
		
		search_name.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{	//제목으로 찾기
				//new SubjectSearch();
			}
		});
		
		search_condition.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{	//조건으로 찾기
				//new OptionSearch();
			}
		});
		
		go_mypage.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{	//마이페이지
				
			}
		});
		
		logout.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{	//로그아웃
				dispose();		
				new Login(); //다시 로그인창으로 돌아감
			}
		});
		
		
	} 

}
