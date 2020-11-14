
  
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
import java.util.HashMap;
import java.util.Scanner;
import java.sql.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
public class Login extends JFrame {
		public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	   public static final String USER_UNIVERSITY ="university";
	   public static final String USER_PASSWD ="comp322";

	
	public Login() {
		// TODO Auto-generated method stub
		JPanel name = new JPanel(); //knu moive를 적을 panel
		JLabel knu_movie = new JLabel("KNU MOVIE"); //knu_movie가 적힌 label
		name.add(knu_movie);	//name panel에 knu_movie label부착
		
		JPanel logpanel = new JPanel(new GridLayout(1,3));
		JPanel idpanel = new JPanel();
		JPanel pwpanel = new JPanel();
		
		JLabel idlabel = new JLabel("ID :");
		JLabel pwlabel = new JLabel("PW : ");
		JButton logbtn = new JButton("Login");
		JTextField idfield = new JTextField(30);
		JPasswordField pwfield = new JPasswordField(30);
		
		JPanel joinpanel = new JPanel(new GridLayout(1,2));
		JButton findbtn = new JButton("Find ID/PW");
		JButton joinbtn = new JButton("Join");
		
		
		idpanel.add(idlabel);
		idpanel.add(idfield);
		
		pwpanel.add(pwlabel);
		pwpanel.add(pwfield);
		
		
		
		logpanel.add(idpanel);
		logpanel.add(pwpanel);
		logpanel.add(logbtn);
		
		joinpanel.add(joinbtn);
		joinpanel.add(findbtn);
		
		GridLayout gl = new GridLayout(3,1);
		setLayout(gl);
		
		add(name);
		add(logpanel);
		add(joinpanel);
		
		setVisible(true);
		setSize(1000,650);
		setLocationRelativeTo(null); 		//윈도우를 컴퓨터 중간에 띄우기
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		//DB랑 연결
		  
		logbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String id = idfield.getText().toString();
				Connection conn = null; // Connection object
				Statement stmt = null;   // Statement object
				String sql ="";
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
				       
				//DB에 해당 account 가 있으면 
				  try {
					  conn.setAutoCommit(false);
					  stmt = conn.createStatement();
					 sql = "SELECT * FROM ACCOUNT WHERE Account_id = '"+id+"'";
					 ResultSet rs = stmt.executeQuery(sql);
					 System.out.println(sql);
					 
					 if(rs != null)
						{
						 sql = "SELECT password FROM ACCOUNT WHERE Account_id ='"
								 +id+"'";
						 rs = stmt.executeQuery(sql);
						 String inputpw = pwfield.getText().toString();
						 String pw="";
						 while(rs.next()) {
							 pw = rs.getString(1);
						 }
						 if(pw.equals(inputpw)) {		//비밀번호 맞으면
							 sql = "SELECT is_customer FROM ACCOUNT WHERE Account_id = '"
									 +id+"'";
							 rs = stmt.executeQuery(sql);
							 String iscustomer="";
							 while(rs.next())
							 {
								 iscustomer = rs.getString(1);
							 }
								if(iscustomer.equals("false")) { //when administer account
									new ManagerAccount(id);
								}
								else { //when customer account
									new MovieList(id);
								}
							}
						 else { //비밀번호 틀리면 
							 JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호가 틀립니다.");
								idfield.setText("");
								pwfield.setText("");
						 }
						}
						//DB에 해당 ACCOUNT 가 없으면 
						else
						{
							JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호가 틀립니다.");
							idfield.setText("");
							pwfield.setText("");
						}
					 
					 
				  }catch(SQLException ex2) {					  
					  System.exit(1);
				  }
				       
				  
				
					
			}
		});
		
		joinbtn.addActionListener(new ActionListener() { //joinbtn 누르면
			public void actionPerformed(ActionEvent e) {
				new Join(); //join창으로 넘어가고
				dispose();  //login창은 닫힘
			}
		});
		
		findbtn.addActionListener(new ActionListener() { //findbtn 누르면
			public void actionPerformed(ActionEvent e) {
				new find(); //find창으로 넘어가고
				dispose();  //login창은 닫힘 
			}
		});
		
	}
	

}

