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

public class Moredeep extends JFrame{
	//상세정보 보여주는 class 
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	   public static final String USER_UNIVERSITY ="university";
	   public static final String USER_PASSWD ="comp322";
	   Connection conn = null; // Connection object
		Statement stmt = null;   // Statement object
		String sql ="";
		String title= "";
		String is_adult="";
		String runtime_minute = "";
		String ost ="";
		String audience = "";
		String hasclip ="";
		String director ="";
		String writer = "";
		String startyear ="";
		String endyear = "";
		String year = "";
		String type = "";
		String score = "";
		ArrayList<Integer> version = new ArrayList<Integer>(); 
		ArrayList<Integer> genre = new ArrayList<Integer>(); 
	public Moredeep(int title_id, String Account_id)
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
	  
	  //title_id 에 맞는 movie의 정보 읽어오기 
	  try {
		  conn.setAutoCommit(false);
		  stmt = conn.createStatement();
		  sql = "SELECT title, is_adult, runtime_minute, ost, audience, hasclip, director, writer, startyear, endyear, type "
				  +"FROM MOVIE WHERE title_id = "+title_id;
		  
		  ResultSet rs = stmt.executeQuery(sql);
		  while(rs.next())
		  {
			  title = rs.getString(1);
			  is_adult = rs.getString(2);
			  runtime_minute = rs.getString(3);
			  ost = rs.getString(4);
			  audience = rs.getString(5);
			  hasclip = rs.getString(6);
			  director = rs.getString(7);
			  writer= rs.getString(8);
			  startyear = rs.getString(9);
			  endyear = rs.getString(10);
			  type = rs.getString(11);
			  
			  //System.out.println(title);
		  }
		  rs.close();
	  }catch(SQLException ex)
	  {
		  System.err.println("sql error = "+ex.getMessage());
		  System.exit(1);
	  }
	  //movie 의 평점 읽어오기 
	  try {
		  conn.setAutoCommit(false);
		  stmt = conn.createStatement();
		  sql = "SELECT Mt_id, AVG(Score) FROM RATING"
				  +" WHERE Mt_id = "+title_id
				  +" GROUP BY Mt_id";
		  
		  ResultSet rs = stmt.executeQuery(sql);
		  while(rs.next())
		  {
			  score = rs.getString(2);
			  
			  //System.out.println(title);
		  }
		  rs.close();
	  }catch(SQLException ex)
	  {
		  System.err.println("sql error = "+ex.getMessage());
		  System.exit(1);
	  }
	  
	  //movie의 장르 읽어오기 
	 try {
		  conn.setAutoCommit(false);
		  stmt = conn.createStatement();
		  sql = "SELECT Gnum FROM HAS_GENRE WHERE M_id = "+title_id;
		  
		  ResultSet rs = stmt.executeQuery(sql);
		  while(rs.next())
		  {
			  genre.add(rs.getInt(1));
		  }
		  rs.close();
	  }catch(SQLException ex)
	  {
		  System.err.println("sql error = "+ex.getMessage());
		  System.exit(1);
	  }
	  
	  //movie의 version 읽어오기
	  try {
		  conn.setAutoCommit(false);
		  stmt = conn.createStatement();
		  sql = "SELECT V_id FROM HAS_VERSION WHERE Acs_id ="+title_id;
		  ResultSet rs = stmt.executeQuery(sql);
		  while(rs.next())
		  {
			  version.add(rs.getInt(1));
		  }
		  rs.close();
	  }catch(SQLException ex)
	  {
		  System.err.println("sql error = "+ex.getMessage());
		  System.exit(1);
	  }
	  
	  JPanel titlepanel = new JPanel();
	  JLabel titlelabel = new JLabel("<<"+title+">>");
	  titlepanel.add(titlelabel);
	  
	  JPanel yearpanel = new JPanel();
	  if(endyear==null)
	  {
		  year = startyear;
	  }
	  else
		  year = startyear+"-"+endyear;
	  JLabel yearlabel = new JLabel("year: "+year);
	  yearpanel.add(yearlabel);
	  
	  JPanel runpanel = new JPanel();
	  JLabel runlabel = new JLabel("runtime minute: "+runtime_minute);
	  runpanel.add(runlabel);
	  
	  JPanel directorpanel = new JPanel();
	  JLabel directorlabel = new JLabel("director: "+director);
	  directorpanel.add(directorlabel);
	  
	  JPanel writerpanel = new JPanel();
	  JLabel writerlabel = new JLabel("writer: "+writer);
	  writerpanel.add(writerlabel);
	  
	  JPanel genrepanel = new JPanel();
	  String genrestr="";
	  for(int i =0;i<genre.size();i++)
	  {
		  switch(genre.get(i)) {
		  case 1 :
			  genrestr = genrestr+(i+1)+"."+"Horror"+" " ;
			  break;
		  case 2 :
			  genrestr = genrestr+(i+1)+"."+"Thriller"+" " ;
			  break;
		  case 3 :
			  genrestr = genrestr+(i+1)+"."+"Sci-Fi"+" " ;
			  break;
		  case 4 :
			  genrestr = genrestr+(i+1)+"."+"Crime"+" " ;
			  break;
		  case 5 :
			  genrestr = genrestr+(i+1)+"."+"Drama"+" " ;
			  break;
		  case 6 :
			  genrestr = genrestr+(i+1)+"."+"Fantasy"+" " ;
			  break;
		  case 7 :
			  genrestr = genrestr+(i+1)+"."+"Animation"+" " ;
			  break;
		  case 8 :
			  genrestr = genrestr+(i+1)+"."+"Comedy"+" " ;
			  break;
		  case 9 :
			  genrestr = genrestr+(i+1)+"."+"Romance"+" " ;
			  break;
		  case 10 :
			  genrestr = genrestr+(i+1)+"."+"Action"+" " ;
			  break;
			  
		  }
	  }
	  JLabel genrelabel = new JLabel("genre: "+genrestr);
	  genrepanel.add(genrelabel);
	  
	  JPanel versionpanel = new JPanel();
	  String versionstr="";
	  for(int i =0;i<version.size();i++)
	  {
		  switch(version.get(i)) {
		  case 1 :
			  versionstr = versionstr+(i+1)+"."+"KR"+" " ;
			  break;
		  case 2 :
			  versionstr = versionstr+(i+1)+"."+"US"+" " ;
			  break;
		  case 3 :
			  versionstr = versionstr+(i+1)+"."+"UK"+" " ;
			  break;
		  case 4 :
			  versionstr = versionstr+(i+1)+"."+"JP"+" " ;
			  break;
		  case 5 :
			  versionstr = versionstr+(i+1)+"."+"CN"+" " ;
			  break;
		  case 6 :
			  versionstr = versionstr+(i+1)+"."+"FR"+" " ;
			  break;
			  
		  }
	  }
	  JLabel versionlabel = new JLabel("version: "+versionstr);
	  versionpanel.add(versionlabel);
	  
	  JPanel typepanel = new JPanel();
	  JButton epbtn = new JButton("show episodes");
	  switch(type) {
		  case "s":
			  typepanel.add(new JLabel("type: TV Series"));
			  //시리즈의 경우, 에피소드가 존재함...
			  
			  typepanel.add(epbtn);
			  break;
		  case "m":
			  typepanel.add(new JLabel("type: Movie"));
			  break;
		  case "o":
			  typepanel.add(new JLabel("type: KnuMovieDB Original"));
			  break;
	
	  }
	  
	  JPanel scorepanel = new JPanel();
	  JLabel scorelabel = new JLabel("score: "+score);
	  scorepanel.add(scorelabel);
	  
	  JPanel adultpanel = new JPanel();
	  switch(is_adult) {
	  case"false" :
		  adultpanel.add(new JLabel("청소년 관람 가능"));
		  break;
	  case"true":
		  adultpanel.add(new JLabel("청소년 관람 불가"));
		  break;
	  }
	  
	 
	  JPanel ostpanel = new JPanel();
	  JLabel ostlabel = new JLabel("ost: "+ost);
	  ostpanel.add(ostlabel);
	  
	  JPanel audiencepanel = new JPanel();
	  JLabel audiencelabel = new JLabel("audience: "+audience);
	  audiencepanel.add(audiencelabel);
	  
	  JPanel clippanel = new JPanel();
	  switch(hasclip) {
	  case"false" :
		  clippanel.add(new JLabel("clip not exists"));
		  break;
	  case"true":
		  clippanel.add(new JLabel("clip exists"));
		  break;
	  }
	  
	  JPanel ratingpanel = new JPanel(new GridLayout(1,3));
	  JLabel ratinglabel = new JLabel("");
	  Choice ratingscore = new Choice();
	  String line = "----------";
	  ratingscore.add(line);
	  ratingscore.add("1");
	  ratingscore.add("2");
	  ratingscore.add("3");
	  ratingscore.add("4");
	  ratingscore.add("5");
	  ratingscore.add("6");
	  ratingscore.add("7");
	  ratingscore.add("8");
	  ratingscore.add("9");
	  ratingscore.add("10");
	  
	  JButton ratingbtn = new JButton("점수주기");
	  ratingpanel.add(ratinglabel);
	  ratingpanel.add(ratingscore);
	  ratingpanel.add(ratingbtn);
	  
	  JButton actbtn = new JButton("출연배우보기");
	  
	  
	  JPanel center = new JPanel(new GridLayout(1,2));
	  JPanel center1 = new JPanel(new GridLayout(8,1));
	  JPanel center2 = new JPanel(new GridLayout(8,1));
	  
	  center1.add(yearpanel);
	  center1.add(runpanel);
	  center1.add(directorpanel);
	  center1.add(writerpanel);
	  center1.add(genrepanel);
	  center1.add(versionpanel);
	  center1.add(ratingpanel);
	  
	  center2.add(typepanel);
	  center2.add(scorepanel);
	  center2.add(adultpanel);
	  center2.add(ostpanel);
	  center2.add(audiencepanel);
	  center2.add(clippanel);
	  center2.add(actbtn);
	  
	  center.add(center1);
	  center.add(center2);
	  JButton closeBtn = new JButton("닫기");
	  
	  //
	  
	  add(titlepanel,BorderLayout.NORTH);
	  add(center,BorderLayout.CENTER);
	  add(closeBtn,BorderLayout.SOUTH);
	 
        closeBtn.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		dispose();
        		new MovieList(Account_id);
        		//평가를 적용한 새로윤 list를 보여줌
        	}
        });
        
        
        ratingbtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int cnt = 0;
        		try {
        			  conn.setAutoCommit(false);
        			  stmt = conn.createStatement();
        			  sql = "SELECT count(*) FROM RATING";
        			  
        			  ResultSet rs = stmt.executeQuery(sql);
        			  while(rs.next())
        			  {
        				  cnt = rs.getInt(1);
        				  cnt++;
        				  //System.out.println(title);
        			  }
        			  rs.close();
        		  }catch(SQLException ex)
        		  {
        			  System.err.println("sql error = "+ex.getMessage());
        			  System.exit(1);
        		  }
        		
        		try {
      			  conn.setAutoCommit(false);
      			  stmt = conn.createStatement();
      			  sql = "INSERT INTO RATING VALUES("+cnt+","+title_id+",'"+Account_id+
      					  "',"+ratingscore.getSelectedItem()+")";
      			 int res = stmt.executeUpdate(sql);
      			  conn.commit();
      			JOptionPane.showMessageDialog(null, "평가완료.");
      			ratingscore.disable();
      			
      		  }catch(SQLException ex)
      		  {
      			  System.err.println("sql error = "+ex.getMessage());
      			  System.exit(1);
      		  }
        		
        	}
        	
        });
        
        actbtn.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		new ShowActor(title_id);
        	}
        });
        
        epbtn.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		new ShowEp(title_id);
        	}
        });
        
        setVisible(true);
        setSize(1000,650);
        setLocationRelativeTo(null);    
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
