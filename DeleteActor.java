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

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class DeleteActor extends JFrame{
	//스크롤바로 actor목록 보여주고, 라디오버튼 추가해서 선택된 배우들을 삭제할 수 있게 설계
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	   public static final String USER_UNIVERSITY ="university";
	   public static final String USER_PASSWD ="comp322";
	   Connection conn = null; // Connection object
		Statement stmt = null;   // Statement object
		String sql = "";
		
		
		ArrayList<Integer>actorlist = new ArrayList<>();
		ArrayList<String>actor_name = new ArrayList<>();
		ArrayList<String>Character_age = new ArrayList<>();
		ArrayList<String>Character_nationality = new ArrayList<>();
		ArrayList<String>Role_name = new ArrayList<>();
		ArrayList<String>is_leading_role = new ArrayList<>();
		ArrayList<JRadioButton> list = new ArrayList<>();
		
	public DeleteActor(int title_id ,String Account_id)
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
		  
		  try {
			  conn.setAutoCommit(false);
			  stmt = conn.createStatement();
			  sql = "SELECT A_id FROM ROLED WHERE Movie_id = "+title_id;
			  ResultSet rs = stmt.executeQuery(sql);
			  while(rs.next())
			  {
				 actorlist.add(rs.getInt(1));
			  }
			  rs.close();
		  }catch(SQLException ex)
		  {
			  System.err.println("sql error = "+ex.getMessage());
			  System.exit(1);
		  }
		  //movie에 출연한 actor들의 id를 actorlist에 저장
			
		  try {
			  conn.setAutoCommit(false);
			  stmt = conn.createStatement();
			  for(int i =0;i<actorlist.size();i++)
			  {
				  sql = "SELECT Character_age, Character_nationality,Role_name,is_leading_role FROM ROLED"
						  +" WHERE Movie_id = "+title_id+"AND A_ID = "+actorlist.get(i);
				  			
				  ResultSet rs = stmt.executeQuery(sql);
				  while(rs.next())
				  {
					  Character_age.add(rs.getString(1));
					  Character_nationality.add(rs.getString(2));
					  Role_name.add(rs.getString(3));
					  is_leading_role.add(rs.getString(4));
				  }
				  rs.close();
			  }
		  }catch(SQLException ex)
		  {
			  System.err.println("sql error = "+ex.getMessage());
			  System.exit(1);
		  }
		  
		  //movie에 출연한 actor들의 id를 actorlist에 저장
		  try {
			  conn.setAutoCommit(false);
			  stmt = conn.createStatement();
			  for(int i =0;i<actorlist.size();i++)
			  {
				  sql = "SELECT Name FROM ACTOR WHERE Act_id ="+actorlist.get(i);
				  			
				  ResultSet rs = stmt.executeQuery(sql);
				  while(rs.next())
				  {
					  actor_name.add(rs.getString(1));
				  }
				  rs.close();
			  }
		  }catch(SQLException ex)
		  {
			  System.err.println("sql error = "+ex.getMessage());
			  System.exit(1);
		  }
		  //actor 들의 이름 저장 
		  
		  JPanel showact = new JPanel();
		  JLabel showactmsg= new JLabel("<<출연 배우 목록>>");
		  showact.add(showactmsg);
		  
		  int cnt = actorlist.size();
		  
		  JPanel show_actor = new JPanel(new GridLayout(cnt+1,1));
		  JLabel attribute = new JLabel("이름: 작중역할, 작중나이, 작중국적");
		  show_actor.add(attribute);
		  
		 
		  JScrollPane scroll = new JScrollPane(show_actor,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		  show_actor.setLayout(new BoxLayout(show_actor, BoxLayout.Y_AXIS));
			
		  for(int i=0;i<cnt;i++)
			  
		  {
			  String rname = "";
			  rname = Role_name.get(i);
			  if(is_leading_role.get(i).equals("true"))
			  {
				  rname = rname +"(주연)";
			  }
			  else
			  {
				  rname= rname + "(조연)";
			  }
			  
			  list.add(new JRadioButton(actor_name.get(i)+": "+rname+", "+Character_age.get(i)+", "+Character_nationality.get(i)));
			  show_actor.add(list.get(i));
		  }
		  
		  
		  JPanel btn = new JPanel();
		  JButton delbtn = new JButton("삭제");
		  JButton closeBtn = new JButton("닫기");
		  btn.add(delbtn);
		  btn.add(closeBtn);
		  setLayout(new BorderLayout());
		  
		  add(showact,BorderLayout.NORTH);
		  add(scroll,BorderLayout.CENTER);
		  add(btn,BorderLayout.SOUTH);
		  
		  
		    setVisible(true);
			setSize(1000,650);
			setLocationRelativeTo(null); 		//윈도우를 컴퓨터 중간에 띄우기
			setResizable(false);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			
			delbtn.addActionListener(new ActionListener()
		      {
		      	public void actionPerformed(ActionEvent e)
		      	{
		      		for(int i =0; i<list.size();i++)
		      		{
		      			if(list.get(i).isSelected())
		      			{
		      				try {
		    	     			conn.setAutoCommit(false);
		    	     			  stmt = conn.createStatement();
		    					sql = "DELETE FROM ROLED WHERE A_id = "+actorlist.get(i);
		    					int res = stmt.executeUpdate(sql);
		    					conn.commit();
		    				}catch(SQLException ex2) {
		    					System.err.println("sql error = "+ex2.getMessage());
		    					System.exit(1);
		    				}
		    	     		
		      			}
		      		}
		      		JOptionPane.showMessageDialog(null, "삭제 완료.");
		      		dispose();
		      		new Update_info(Account_id,title_id);
		      	}
		      });
			
			closeBtn.addActionListener(new ActionListener()
		      {
		      	public void actionPerformed(ActionEvent e)
		      	{
		      		dispose();
		      		new Update_info(Account_id,title_id);
		      	}
		      });
			
		
	}
	

}
