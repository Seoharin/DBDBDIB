//// 멤버십이 없을 때, 새로 만드는 것
package teamJDBC;

import java.awt.Choice;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class MakeMembership extends JFrame{

	public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	public static final String USER_UNIVERSITY ="university";
	public static final String USER_PASSWD ="comp322";
	Connection conn = null; // Connection object
	Statement stmt = null;   // Statement object
	String sql ="";
	
	String name ="";
	int cntMember = 0;    // membership에 몇명있는지?
	int typeToken = 0;    // basic:0, premium:1, prime:2
	
	@SuppressWarnings("unlikely-arg-type")
	public MakeMembership(String id)
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
		
		try {
			conn.setAutoCommit(false);
			stmt = conn.createStatement();	
		}catch(SQLException ex2)
		  {
			  System.err.println("sql1 error");
			  System.exit(1);
		  }
		
		JPanel titlePanel = new JPanel();
		titlePanel.add(new JLabel(""+name+"의 멤버십 등록"));
		
		setLayout(new GridLayout(5,1));
		
		JPanel paydayPanel = new JPanel();
		JLabel paydayLabel = new JLabel("결제일: ");
		Choice payday = new Choice();
		String line = "----------";
		payday.add("1");
		payday.add("2");
		payday.add("3");
		payday.add("4");
		payday.add("5");
		payday.add("6");
		payday.add("7");
		payday.add("8");
		payday.add("9");
		payday.add("10");
		payday.add("11");
		payday.add("12");
		payday.add("13");
		payday.add("14");
		payday.add("15");
		payday.add("16");
		payday.add("17");
		payday.add("18");
		payday.add("19");
		payday.add("20");
		payday.add("21");
		payday.add("22");
		payday.add("23");
		payday.add("24");
		payday.add("25");
		payday.add("26");
		payday.add("27");
		payday.add("28");
		payday.add("29");
		payday.add("30");
		payday.add("31");
		
		paydayPanel.add(paydayLabel);
		paydayPanel.add(payday);
		
		JPanel accountPanel = new JPanel();
		JLabel accountLabel = new JLabel("연결계좌: ");
		JTextField accountText = new JTextField(20);
		
		JPanel typePanel = new JPanel();
		JLabel typeLabel = new JLabel("멤버십 종류: ");
		ButtonGroup typeBtn = new ButtonGroup();
		JRadioButton basicBtn = new JRadioButton("basic");  // 0
		JRadioButton premiumBtn = new JRadioButton("premium");  // 1
		JRadioButton primeBtn = new JRadioButton("prime");  // 2
		
		JPanel twoBtn = new JPanel();
		JButton yes = new JButton("만들기");
		JButton close = new JButton("닫기");
		
		typeBtn.add(basicBtn);
		typeBtn.add(premiumBtn);
		typeBtn.add(primeBtn);
		
		typePanel.add(typeLabel);
		typePanel.add(basicBtn);
		typePanel.add(premiumBtn);
		typePanel.add(primeBtn);
	    
		paydayPanel.add(paydayLabel);
		
		accountPanel.add(accountLabel);
		accountPanel.add(accountText);
		
		twoBtn.add(yes);
		twoBtn.add(close);
		
		add(titlePanel);
		add(paydayPanel);
		add(accountPanel);
		add(typePanel);
		add(twoBtn);
	    
		try {
			  conn.setAutoCommit(false);
			  stmt = conn.createStatement();
			  sql = "select count(*) from membership";
			  ResultSet rs = stmt.executeQuery(sql);
			  
			  while(rs.next())
			  {
				  cntMember = rs.getInt(1);
			  }  
		  }catch(SQLException ex)
		  {
			  System.err.println("sql error = "+ex.getMessage());
			  System.exit(1);
		  }
	
		
		   // 등록하기
		  yes.addActionListener(new ActionListener(){
		      	public void actionPerformed(ActionEvent e)
		      	{
		      		if(typeBtn.getSelection().equals("basic"))
		    			typeToken = 0;
		    		else if(typeBtn.getSelection().equals("premium"))
		    			typeToken = 1;
		    		else if(typeBtn.getSelection().equals("prime"))
		    			typeToken = 2;
		      		
		      		try {
		      			
		  			  conn.setAutoCommit(false);
		  			  stmt = conn.createStatement();
		  			  sql = "insert into membership values ('"+id+"',"+(cntMember+1)+","+payday.getSelectedItem()+",'"+accountText.getText()+"',"+typeToken+")";                                
		  			
		  			  int res = stmt.executeUpdate(sql);
		  			  conn.commit();	
		  		  }catch(SQLException ex)
		  		  {
		  			  System.err.println("sql error = "+ex.getMessage());
		  			  System.exit(1);
		  		  }
		      		
		      		JOptionPane.showMessageDialog(null, "멤버십 등록 완료");
		      		
		      		new Mypage_real(id);
		      		dispose();
		      	}
		     });

		   // 닫기
		  close.addActionListener(new ActionListener(){
		      	public void actionPerformed(ActionEvent e)
		      	{
		      		dispose();
		      		new Mypage_real(id);
		      	}
		     });
		
		
		setVisible(true);
		setSize(1000,650);
		setLocationRelativeTo(null); 		//윈도우를 컴퓨터 중간에 띄우기
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}




