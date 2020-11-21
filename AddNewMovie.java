package dbdbdib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Checkbox;
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

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import javax.swing.JFrame;

public class AddNewMovie extends JFrame{
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	   public static final String USER_UNIVERSITY ="university";
	   public static final String USER_PASSWD ="comp322";
	   Connection conn = null; // Connection object
		Statement stmt = null;   // Statement object
		ArrayList<Integer> id = new ArrayList<>();
		int title_id = 0;
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
		//String year = "";
		String type = "";
		
		//String score = "";
		ArrayList<JRadioButton> version = new ArrayList<>(); 
		ArrayList<JRadioButton> genre = new ArrayList<>(); 
		
	public AddNewMovie(String Account_id){
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
	   //디비 연결
	  
	  //현재 내 디비에 저장된 movie가 몇 개인지 확인
	  
		try {
			  conn.setAutoCommit(false);
			  stmt = conn.createStatement();
			  sql = "SELECT title_id FROM MOVIE";
			  
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
		
		
		JPanel titlepanel = new JPanel();
		JLabel titlelabel = new JLabel("제목 : ");
		JTextField titlefield = new JTextField(30);
		titlepanel.add(titlelabel);
		titlepanel.add(titlefield);
		
		JPanel isadultpanel = new JPanel();
		JLabel isadultlabel = new JLabel("청소년 관람 불가 여부: ");
		Checkbox isadult = new Checkbox("해당됨");
		isadultpanel.add(isadultlabel);
		isadultpanel.add(isadult);
		
		JPanel runtimepanel = new JPanel();
		JLabel runtimelabel = new JLabel("상영시간 : ");
		JTextField runtimefield = new JTextField(30);
		runtimepanel.add(runtimelabel);
		runtimepanel.add(runtimefield);
		
		JPanel ostpanel = new JPanel();
		JLabel ostlabel = new JLabel("OST : ");
		JTextField ostfield = new JTextField(30);
		ostpanel.add(ostlabel);
		ostpanel.add(ostfield);
		
		JPanel audiencepanel = new JPanel();
		JLabel audiencelabel = new JLabel("관객 수(단위 : 만) : ");
		JTextField audiencefield = new JTextField(30);
		audiencepanel.add(audiencelabel);
		audiencepanel.add(audiencefield);
		
		JPanel clippanel = new JPanel();
		JLabel cliplabel = new JLabel("클립영상 여부 : ");
		Checkbox clip = new Checkbox("있음");
		clippanel.add(cliplabel);
		clippanel.add(clip);
		
		
		JPanel directorpanel = new JPanel();
		JLabel directorlabel = new JLabel("감독 : ");
		JTextField directorfield = new JTextField(30);
		directorpanel.add(directorlabel);
		directorpanel.add(directorfield);
		
		JPanel writerpanel = new JPanel();
		JLabel writerlabel = new JLabel("작가 : ");
		JTextField writerfield = new JTextField(30);
		writerpanel.add(writerlabel);
		writerpanel.add(writerfield);
		
		JPanel typepanel = new JPanel();
		JLabel typelabel = new JLabel("타입 : ");
		ButtonGroup gr = new ButtonGroup();
		JRadioButton s  = new JRadioButton("TV Series");
		JRadioButton o  = new JRadioButton("KNUMovieDB Original");
		JRadioButton m  = new JRadioButton("Movie");
		gr.add(s);
		gr.add(o);
		gr.add(m);
		typepanel.add(typelabel);
		typepanel.add(s);
		typepanel.add(o);
		typepanel.add(m);
		
		JPanel startyearpanel = new JPanel();
		JLabel startyearlabel = new JLabel("방영시작일(mm-dd-yyyy) : ");
		JTextField startyearfield = new JTextField(30);
		startyearpanel.add(startyearlabel);
		startyearpanel.add(startyearfield);
		
		JPanel endyearpanel = new JPanel();
		JLabel endyearlabel = new JLabel("방영종료일(mm-dd-yyyy), TV Series의 경우만 기입 : ");
		JTextField endyearfield = new JTextField(30);
		endyearpanel.add(endyearlabel);
		endyearpanel.add(endyearfield);
		
		JPanel versionpanel = new JPanel();
		JLabel versionlabel = new JLabel("버전: ");
		version.add(new JRadioButton("KR"));
		version.add(new JRadioButton("US"));
		version.add(new JRadioButton("UK"));
		version.add(new JRadioButton("JP"));
		version.add(new JRadioButton("CN"));
		version.add(new JRadioButton("FR"));
		versionpanel.add(versionlabel);
		for(int i = 0;i<6;i++)
		{
			versionpanel.add(version.get(i));
		}
		
		JPanel genrepanel = new JPanel();
		JLabel genrelabel = new JLabel("장르: ");
		genre.add(new JRadioButton("Horror"));
		genre.add(new JRadioButton("Thriller"));
		genre.add(new JRadioButton("Sci-Fi"));
		genre.add(new JRadioButton("Crime"));
		genre.add(new JRadioButton("Drama"));
		genre.add(new JRadioButton("Fantasy"));
		genre.add(new JRadioButton("Animation"));
		genre.add(new JRadioButton("Comedy"));
		genre.add(new JRadioButton("Romance"));
		genre.add(new JRadioButton("Actioin"));
		genrepanel.add(genrelabel);
		for(int i=0;i<10;i++)
		{
			genrepanel.add(genre.get(i));
		}
		
		JButton actor = new JButton("배우등록");
		
		JPanel north = new JPanel();
		north.add(new JLabel("<< 영상물 추가 >>"));
		JPanel center = new JPanel(new GridLayout(7,2));
		center.add(titlepanel);
		center.add(isadultpanel);
		center.add(startyearpanel);
		center.add(endyearpanel);
		center.add(runtimepanel);
		center.add(audiencepanel);
		center.add(ostpanel);
		center.add(clippanel);
		center.add(directorpanel);
		center.add(writerpanel);
		center.add(actor);
		
		center.add(typepanel);
		center.add(versionpanel);
		center.add(genrepanel);
		
		JPanel south = new JPanel();
		JButton closebtn = new JButton("닫기");
		JButton insertbtn = new JButton("입력완료");
		south.add(insertbtn);
		south.add(closebtn);
		
		setLayout(new BorderLayout());
		add(north,BorderLayout.NORTH);
		add(center,BorderLayout.CENTER);
		add(south,BorderLayout.SOUTH);
		
		
		
		
		
		
		
		
		setVisible(true);
		setSize(1000,650);
		setLocationRelativeTo(null); 		//윈도우를 컴퓨터 중간에 띄우기
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		insertbtn.addActionListener(new ActionListener()
	     {
	     	public void actionPerformed(ActionEvent e)
	     	{
	     		title_id = id.get(id.size()-1)+1;
	     		title = titlefield.getText();
	     		runtime_minute = runtimefield.getText();
	     		ost = ostfield.getText();
	     		audience = audiencefield.getText();
	     		director = directorfield.getText();
	     		writer = writerfield.getText();
	     		startyear = startyearfield.getText();
	     		endyear = endyearfield.getText();
	     		
	     		if(isadult.getState())
	     		{
	     			is_adult = "true";
	     		}
	     		else
	     			is_adult ="false";
	     		
	     		if(clip.getState())
	     		{
	     			hasclip = "true";
	     		}
	     		else
	     			hasclip = "false";
	     		if(s.isSelected())
	     		{
	     			type = "s";
	     		}
	     		else if(o.isSelected())
	     		{
	     			type="o";
	     		}
	     		else
	     		{
	     			type = "m";
	     		}
	     		
	     		
	     		try {
	     			conn.setAutoCommit(false);
	     			  stmt = conn.createStatement();
					if(endyear.equals(""))
					{
						sql = "INSERT INTO MOVIE VALUES ("+title_id+", '"+title+"','"+is_adult+"',"+runtime_minute
								+",'"+ost+"',"+audience+",'"+hasclip+"','"+director+"','"+writer
								+"',TO_DATE('"+startyear+"','mm-dd-yyyy'),NULL,'"+type+"','"
								+Account_id+"')";
					}
					else
					{
						sql = "INSERT INTO MOVIE VALUES ("+title_id+", '"+title+"','"+is_adult+"',"+runtime_minute
								+",'"+ost+"',"+audience+",'"+hasclip+"','"+director+"','"+writer
								+"',TO_DATE('"+startyear+"','mm-dd-yyyy'),TO_DATE('"+endyear+"','mm-dd-yyyy'),'"+type+"','"
								+Account_id+"')";
					}
					
					//System.out.println(sql);
					int res = stmt.executeUpdate(sql);
					conn.commit();
				}catch(SQLException ex2) {
					System.err.println("sql error = "+ex2.getMessage());
					System.exit(1);
				}
	     		
	     		//버전 추가
	     		for(int i = 0;i<6;i++)
	     		{
	     			if(version.get(i).isSelected())
	     			{
	     				try {
	     					conn.setAutoCommit(false);
	  	     			    stmt = conn.createStatement();
	  	     			    sql = "INSERT INTO HAS_VERSION VALUES("+title_id+","+(i+1)+")";
	  	     			 int res = stmt.executeUpdate(sql);
	 					conn.commit();
	     				}catch(SQLException ex2) {
	    					System.err.println("sql error = "+ex2.getMessage());
	    					System.exit(1);
	    				}
	     			}
	     		}
	     		
	     		//장르추가
	     		for(int i = 0;i<10;i++)
	     		{
	     			if(genre.get(i).isSelected())
	     			{
	     				try {
	     					conn.setAutoCommit(false);
	  	     			    stmt = conn.createStatement();
	  	     			    sql = "INSERT INTO HAS_GENRE VALUES("+title_id+","+(i+1)+")";
	  	     			 int res = stmt.executeUpdate(sql);
	 					conn.commit();
	     				}catch(SQLException ex2) {
	    					System.err.println("sql error = "+ex2.getMessage());
	    					System.exit(1);
	    				}
	     			}
	     		}
	     		JOptionPane.showMessageDialog(null, "등록완료.");
	     		new ManagerAccount(Account_id);
	     		dispose();
	     		
	     	}
	     });
	     
		
		
		 closebtn.addActionListener(new ActionListener()
	     {
	     	public void actionPerformed(ActionEvent e)
	     	{
	     		dispose();
	     		new ManagerAccount(Account_id);
	     	}
	     });
		 
		 actor.addActionListener(new ActionListener()
	     {
	     	public void actionPerformed(ActionEvent e)
	     	{
	     		dispose();
	     		new AddActor(Account_id,title_id);
	     	}
	     });
		
	
	     
	}
}

