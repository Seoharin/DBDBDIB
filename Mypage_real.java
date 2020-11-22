package teamJDBC;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Mypage_real extends JFrame{

	public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	public static final String USER_UNIVERSITY ="university";
	public static final String USER_PASSWD ="comp322";
	Connection conn = null; // Connection object
	Statement stmt = null;   // Statement object
	String sql ="";
	
	String name = "";
	String pw = "";
	String birth = "";
	String address = "";
	String sex = "";
	String job = "";
	String phone_number = "";
	String Regular_payment_day = "";
	String connect_account = "";
	String membership_name = "";
	int sub = 0;
	
	 // 인자 : Account_id
	public Mypage_real(String id)
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
	  
	  /// Account에서 읽어오기 ///
	  try {
		  conn.setAutoCommit(false);
		  stmt = conn.createStatement();
		  
		    // account_id로 정보찾기
		  sql = "select Name, Password, Birth, Address, Sex, Job, Phone_number"
		  		+ " from account"
		  		+ " where Account_id = '"+id+"'";
		  
		  ResultSet rs = stmt.executeQuery(sql);
		  
		  while(rs.next())  // 해당하는 정보를 JTextField에 쓰기
		  {
			  name = rs.getString(1); // 이름 받아오기
			  // id는 인자에서 받아오기
			  
			  pw = rs.getString(2);  // pw 받아오기
			  
			  birth = rs.getString(3);  // 생일 받아오기
			  
			  address = rs.getString(4);  // 주소 받아오기
			  
			  String guiSex = rs.getString(5);  // 성별 받아오기
			  
			  if(guiSex == null)
				  sex = "null";
			  else if(guiSex.equals("false"))
				  sex = "female";
			  else
				  sex = "male"; 
			  	 
			  job = rs.getString(6);  // 직업 받아오기
			  
			  phone_number = rs.getString(7);  // 폰번호 받아오기
		  }	  
	  }catch(SQLException ex2)
	  {
		  System.err.println("sql error1");
		  System.exit(1);
	  }
	  
	  try {
		  conn.setAutoCommit(false);
		  stmt = conn.createStatement();
		  
		  sql = "select count(*) from membership where Ac_id = '"+id+"'";
		  ResultSet rs = stmt.executeQuery(sql);
		  
		  while(rs.next())
		  {
			  sub = rs.getInt(1);
		  }
	  }catch(SQLException ex2)
	  {
		  System.err.println("sql error2");
		  System.exit(1);
	  }
	  
	  if(sub == 0)
	  {
		  Regular_payment_day = null;
		  connect_account = null;
		  membership_name = null;
	  }
	  
	  /// membership에서 읽어오기 ///
	  try {
		  conn.setAutoCommit(false);
		  stmt = conn.createStatement();
		  
		   // account_id로 정보찾기
		  sql = "select Regular_payment_day, Connect_account, Membership_token"
		  		+ " from membership, account"
		  		+ " where Ac_id = Account_id and Account_id = '"+id+"'";
		  ResultSet rs = stmt.executeQuery(sql);
		  
		  while(rs.next()) 
		  {
              Regular_payment_day = rs.getString(1);  // 지급일 받아오기
			  
			  connect_account = rs.getString(2);  // 계좌 받아오기
			  
			  int membership_token = rs.getInt(3);   // 멤버십 이름
			  if(membership_token == 0)
			  {
				  membership_name = "basic";
			  }
			  else if(membership_token == 1)
			  {
				  membership_name = "premium";
			  }
			  else if(membership_token == 2)
			  {
				  membership_name = "prime";
			  }
		  }
	  }catch(SQLException ex2)
	  {
		  System.err.println("sql error2");
		  System.exit(1);
	  }
		
        JPanel titlepanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("마이페이지");
        JButton evalBtn = new JButton("평가내역");
        
        titlepanel.add(title, BorderLayout.WEST);
        titlepanel.add(evalBtn, BorderLayout.EAST);
        
        setLayout(new GridLayout(16,1));
        
        JPanel namepanel = new JPanel();
        JLabel namelabel = new JLabel("이름: " + name);
        
        JPanel idpanel = new JPanel();
        JLabel idlabel = new JLabel("ID: " + id);
       
        JPanel pwpanel = new JPanel();
        JLabel pwlabel = new JLabel("PW: " + pw);
        JButton pwBtn = new JButton("PW 수정");
        
        JPanel birthpanel = new JPanel();
        JLabel birthlabel = new JLabel("생년월일(yyyymmdd): " + birth);
        
        JPanel genderpanel = new JPanel();
        JLabel genderlabel = new JLabel("성별: " + sex);
        
        JPanel jobpanel = new JPanel();
        JLabel joblabel = new JLabel("직업: " + job);
        JButton jobBtn = new JButton("직업 수정");
        
        JPanel addresspanel = new JPanel();
        JLabel addresslabel = new JLabel("주소: " + address);
        JButton addressBtn = new JButton("주소 수정");
        
        JPanel phonepanel = new JPanel();
        JLabel phonelabel = new JLabel("전화번호: " + phone_number);
        JButton phoneBtn = new JButton("전화번호 수정");
        
        JPanel paydaypanel = new JPanel();
        JLabel paydaylabel = new JLabel("지급일: " + Regular_payment_day);
        
        JPanel con_accountpanel = new JPanel();
        JLabel con_accountlabel = new JLabel("연결 계좌: " + connect_account);   
        
        JPanel memnamepanel = new JPanel();
        JLabel memnamelabel = new JLabel("멤버십 종류: " + membership_name);
        
        //// 이 버튼 3개 ActionLister 처리하기
        JPanel nextpanel = new JPanel(new GridLayout(1,3));
        JButton membership = new JButton("멤버십 관리");   
        JButton close = new JButton("닫기");
        JButton getout = new JButton("탈퇴");
        
        namepanel.add(namelabel);  
        
        idpanel.add(idlabel);  
        
        pwpanel.add(pwlabel);
        pwpanel.add(pwBtn);
        
        birthpanel.add(birthlabel); 
        
        addresspanel.add(addresslabel);
        addresspanel.add(addressBtn);
        
        genderpanel.add(genderlabel);   
        
        jobpanel.add(joblabel);
        jobpanel.add(jobBtn);
        
        phonepanel.add(phonelabel);
        phonepanel.add(phoneBtn);
        
        paydaypanel.add(paydaylabel);
        con_accountpanel.add(con_accountlabel);
        memnamepanel.add(memnamelabel);
        
        nextpanel.add(membership);
        nextpanel.add(close);
        nextpanel.add(getout);
        
        add(titlepanel);
        add(namepanel);
        add(idpanel);
        add(pwpanel);
        add(birthpanel);
        add(addresspanel);
        add(genderpanel);
        add(jobpanel);
        add(phonepanel);
        add(paydaypanel);
        add(con_accountpanel);
        add(memnamepanel);
        add(nextpanel);
        
        pwBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e)
        	{
        		new PwBtnPage(id);
        	}
        });
        
        jobBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e)
        	{
        		new JobBtnPage(id);
        	}
        });
        
        addressBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e)
        	{
        		new AddressBtnPage(id);
        	}
        });
        
        phoneBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e)
        	{
        		new PhoneBtnPage(id);
        	}
        });
        
        // 멤버십 관리
  	  membership.addActionListener(new ActionListener(){
  	      	public void actionPerformed(ActionEvent e)
  	      	{
  	      		try {
  	      		   conn.setAutoCommit(false);
  			       stmt = conn.createStatement();
  			       sql = "select count(*) from membership where Ac_id = '"+id+"'";
  			       
                   ResultSet rs = stmt.executeQuery(sql);
  			       
                   while(rs.next())
                   {
                	   if(rs.getInt(1) == 0)   // 멤버십 존재X -> 새로 만들어야 함
      			       {
                		   dispose();
      			    	   new No_membershipPage(id);
      			       }
      			       else                    // 멤버십 존재O -> 멤버십 창으로 감
      			       {
      			    	   new Membership(id);
      			       }
                   }
  			       
  	      		}catch(SQLException ex2)
				  {
					  System.err.println("sql1 error");
					  System.exit(1);
				  }
  	      	}
  	     });
     
	   // 닫기
	  close.addActionListener(new ActionListener(){
	      	public void actionPerformed(ActionEvent e)
	      	{
	      		dispose();
	      		new MovieList(id);
	      	}
	     });
		 
	   // 탈퇴
	  getout.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e)
		  {
			  try {
		    		conn.setAutoCommit(false);
	  			    stmt = conn.createStatement();
	  			    
	  			    sql = "delete from account where Account_id = '"+id+"'";
	  			    int rs = stmt.executeUpdate(sql);
	  			    
	  			    stmt.addBatch(sql);
	  			    
	  			    conn.commit();
	  			    
		    	}catch(SQLException ex) {System.out.println(ex.getMessage());}
			  finally {
				  try {
					  stmt.close();
					  conn.close();
					  
					  dispose();
					  new MovieList(id);
					  
				  }catch(SQLException ex) {
					  
				  }
			  }
	  		   
			  JOptionPane.showMessageDialog(null, "탈퇴 완료ㅜㅜ");
		  }
	  });
	  
	  evalBtn.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e)
		  {
			  new ShowRating(id);
		  }
	  });
	  
	    setVisible(true);
	    setSize(1000,650);
	    setLocationRelativeTo(null);    
	    setResizable(false);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	}

}







