package teamJDBC;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Membership extends JFrame{

	public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	public static final String USER_UNIVERSITY ="university";
	public static final String USER_PASSWD ="comp322";
	Connection conn = null; // Connection object
	Statement stmt = null;   // Statement object
	String sql ="";
	
	String name ="";
	int Regular_payment_day = 0;
	String Connect_account = "";
	int Membership_token = 0;
	String Membership_String = "";
	
	@SuppressWarnings("unused")
	public Membership(String id)   // Account_id
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
		
	     //  이름 뽑아내기
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
		
		 // 지급일, 연결계좌, 멤버십종류 보여주기
		try {
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			
			sql = "select Regular_payment_day, Connect_account, Membership_token"
					+ " from membership where Ac_id = '"+id+"'";
			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				Regular_payment_day = rs.getInt(1);
				Connect_account = rs.getString(2);
				Membership_token = rs.getInt(3);
			}
			
		}catch(SQLException ex2)
		  {
			  System.err.println("sql1 error");
			  System.exit(1);
		  }
		
		if(Membership_token == 0)
			Membership_String = "basic";
		else if(Membership_token == 0)
			Membership_String = "premium";
		if(Membership_token == 0)
			Membership_String = "prime";
		
		
		JPanel titlePanel = new JPanel();
		JLabel titleLabel = new JLabel(""+name+"의 멤버십 등록");
		titlePanel.add(titleLabel);
		
		setLayout(new GridLayout(5,1));
		
		JPanel paydayPanel = new JPanel();
		JLabel paydayLabel = new JLabel("결제일: " + Regular_payment_day);
		paydayPanel.add(paydayLabel);
		
		JPanel accountPanel = new JPanel();
		JLabel accountLabel = new JLabel("연결계좌: " + Connect_account);
		accountPanel.add(accountLabel);
		
		JPanel typePanel = new JPanel();
		JLabel typeLabel = new JLabel("멤버십 종류: " + Membership_String);
		typePanel.add(typeLabel);
		
		JPanel twoBtn = new JPanel();
		JButton drop = new JButton("멤버십 끊기");
		JButton close = new JButton("닫기");
		twoBtn.add(drop);
		twoBtn.add(close);
		
		add(titlePanel);
		add(paydayPanel);
		add(accountPanel);
		add(typePanel);
		add(twoBtn);
		
		
		drop.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e)
			  {
				  try {
			    		conn.setAutoCommit(false);
		  			    stmt = conn.createStatement();
		  			    
		  			    sql = "delete from membership where Ac_id = '"+id+"'";
		  			    int rs = stmt.executeUpdate(sql);
		  			    
		  			    stmt.addBatch(sql);
		  			    
		  			    conn.commit();
		  			    
			    	}catch(SQLException ex) {System.out.println(ex.getMessage());}
				  finally {
					  try {
						  stmt.close();
						  conn.close();
						  
						  dispose();
						  
					  }catch(SQLException ex) {
						  
					  }
				  }
				  JOptionPane.showMessageDialog(null, "멤버십 삭제 완료");
			  }
		  });
		
		
		close.addActionListener(new ActionListener() {
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





