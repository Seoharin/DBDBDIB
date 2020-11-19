package teamJDBC;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddressBtnPage extends JFrame{

	public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	public static final String USER_UNIVERSITY ="university";
	public static final String USER_PASSWD ="comp322";
	Connection conn = null; // Connection object
	Statement stmt = null;   // Statement object
	String sql ="";
	String data = "";
	
	public AddressBtnPage(String id)
	{
		JPanel addressPanel = new JPanel(new BorderLayout());
		
		setLayout(new GridLayout(3,1));
		
		JLabel addressLabel = new JLabel("주소 변경");
		
		JPanel newaddressPanel = new JPanel();
		JTextField newaddressWrite = new JTextField(20);
		
		JPanel closePanel = new JPanel();
		JButton reBtn = new JButton("완료");
		JButton closeBtn = new JButton("닫기");
		
		addressPanel.add(addressLabel);
		newaddressPanel.add(newaddressWrite);
		closePanel.add(reBtn);
		closePanel.add(closeBtn);
		
		add(addressPanel);
		add(newaddressPanel);
		add(closePanel);
		
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
         
	    // 수정완료 버튼
	    reBtn.addActionListener(new ActionListener(){
	      	public void actionPerformed(ActionEvent e)
	      	{
	      		String data = newaddressWrite.getText();     // 바꾸고 싶은 pw를 data로 선언
	      		
	      		 try {
	     			    conn.setAutoCommit(false);
	     			    stmt = conn.createStatement();
	     			    
	     			    sql = "update account set address = '"+data+"' where Account_id = '"+id+"' ";
	     			    int rs = stmt.executeUpdate(sql);
	     			    
	     			    conn.commit();
	     			    
	     		}catch(SQLException ex) {System.out.println(ex.getMessage());}
	     		  finally {
	     			  try {
	     				  stmt.close();
	     				  conn.close();
	     				  
	     			  }catch(SQLException ex) {
	     			  }
	     		  }
	     	    
	      		
	      		JOptionPane.showMessageDialog(null, "주소 수정완료!");
	      		dispose();
	      	}
	     });
	    
	    // 닫기 버튼
	    closeBtn.addActionListener(new ActionListener(){
	      	public void actionPerformed(ActionEvent e)
	      	{
	      		dispose();
	      	}
	     });
	    
	    setVisible(true);
	    setSize(500,330);
	    setLocationRelativeTo(null);    
	    setResizable(false);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	 }
	
}

