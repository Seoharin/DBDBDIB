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
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
public class find extends JFrame{

	public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	   public static final String USER_UNIVERSITY ="university";
	   public static final String USER_PASSWD ="comp322";
	   Connection conn = null; // Connection object
		Statement stmt = null;   // Statement object
	public find ()
	{
		JPanel idpanel = new JPanel(new GridLayout(1,3));

		JPanel id_name_panel = new JPanel();
		JLabel id_name_label = new JLabel("이름: ");
		JTextField id_name_field = new JTextField(30);
		id_name_panel.add(id_name_label);
		id_name_panel.add(id_name_field);
		
		JPanel id_phone_panel = new JPanel();
		JLabel id_phone_label = new JLabel("전화번호 :");
		JTextField id_phone_field = new JTextField(30);
		id_phone_panel.add(id_phone_label);
		id_phone_panel.add(id_phone_field);
		
		JButton findidbtn = new JButton("ID 찾기");
		
		idpanel.add(id_name_panel);
		idpanel.add(id_phone_panel);
		idpanel.add(findidbtn);
		
		
		JPanel pwpanel = new JPanel(new GridLayout(1,3));

		JPanel pw_name_panel = new JPanel();
		JLabel pw_name_label = new JLabel("이름: ");
		JTextField pw_name_field = new JTextField(30);
		pw_name_panel.add(pw_name_label);
		pw_name_panel.add(pw_name_field);
		
		JPanel pw_id_panel = new JPanel();
		JLabel pw_id_label = new JLabel("ID :");
		JTextField pw_id_field = new JTextField(30);
		pw_id_panel.add(pw_id_label);
		pw_id_panel.add(pw_id_field);
		
		JButton findpwbtn = new JButton("PW 찾기");
		
		pwpanel.add(pw_name_panel);
		pwpanel.add(pw_id_panel);
		pwpanel.add(findpwbtn);
		
		
		JButton close = new JButton("뒤로");
		setLayout(new BorderLayout());
		
		JPanel center = new JPanel(new GridLayout(2,1));
		
		center.add(idpanel);
		center.add(pwpanel);
		
		add(center,BorderLayout.CENTER);
		add(close,BorderLayout.SOUTH);
		setVisible(true);
		setSize(1000,650);
		setLocationRelativeTo(null); 		//윈도우를 컴퓨터 중간에 띄우기
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	
		
		findidbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{ 	//id찾기 버튼 눌렀을 때
				String name = id_name_field.getText();
				String phone = id_phone_field.getText();
				try {
					conn.setAutoCommit(false);
					stmt = conn.createStatement();
					String sql = "SELECT Account_id FROM ACCOUNT WHERE name = '"+name+"' AND phone_number ='"+phone+"'";
					ResultSet rs = stmt.executeQuery(sql);
					if(rs.next()) {	//일치하는 정보가 있을 때 
						String id = rs.getString(1);
						JOptionPane.showMessageDialog(null, "ID는 '"+id+"' 입니다.");
						
					}
					else {	//일치하는 정보가 없을 떄
						JOptionPane.showMessageDialog(null, "일치하는 정보가 없습니다.");
						id_name_field.setText("");
						id_phone_field.setText("");
					}
				}catch(SQLException ex2) {
					System.err.println("sql error = "+ex2.getMessage());
					System.exit(1);
				}
				
				
			}
		});
		
		
		findpwbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{	//pw찾기 버튼 눌렀을 때
				String name = pw_name_field.getText();
				String id = pw_id_field.getText();
				try {
					conn.setAutoCommit(false);
					stmt = conn.createStatement();
					String sql = "SELECT password FROM ACCOUNT WHERE name = '"+name+"' AND Account_id ='"+id+"'";
					ResultSet rs = stmt.executeQuery(sql);
					if(rs.next()) {	//일치하는 정보가 있을 때 
						String password = rs.getString(1);
						JOptionPane.showMessageDialog(null, "PW는 '"+password+"' 입니다.");
						
					}
					else {	//일치하는 정보가 없을 떄
						JOptionPane.showMessageDialog(null, "일치하는 정보가 없습니다.");
						id_name_field.setText("");
						id_phone_field.setText("");
					}
				}catch(SQLException ex2) {
					System.err.println("sql error = "+ex2.getMessage());
					System.exit(1);
				}
			
			}

			});
		
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{	//로그인창을 띄움
				dispose();
				new Login();
			
			}

			});
		
	}
}
