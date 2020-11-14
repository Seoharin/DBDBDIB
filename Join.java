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

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
public class Join extends JFrame {
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	   public static final String USER_UNIVERSITY ="university";
	   public static final String USER_PASSWD ="comp322";
	   Connection conn = null; // Connection object
		Statement stmt = null;   // Statement object
		String sql ="";
		String name;
		String id;
		String pw;
		String birth;
		String address;
		String sex;
		String job;
		String phone;
	private boolean key =false; //ID중복확인을 했는지
	public Join() {
		JPanel join = new JPanel();
		JLabel joinlabel = new JLabel("JOIN");
		join.add(joinlabel);
		
		JPanel namepanel = new JPanel();
		JLabel namelabel = new JLabel("이름: ");
		JTextField namefield = new JTextField(30);
		namepanel.add(namelabel);
		namepanel.add(namefield);
		
		JPanel idpanel = new JPanel();
		JLabel idlabel = new JLabel("ID: ");
		JTextField idfield = new JTextField(30);
		JButton id_duple_btn = new JButton("ID 중복확인");
		idpanel.add(idlabel);
		idpanel.add(idfield);
		idpanel.add(id_duple_btn);
		
		JPanel pwpanel = new JPanel();
		JLabel pwlabel = new JLabel("PW: ");
		JPasswordField pwfield = new JPasswordField(30);
		pwpanel.add(pwlabel);
		pwpanel.add(pwfield);
		
		JPanel pw2panel = new JPanel();
		JLabel pw2label = new JLabel("PW확인: ");
		JPasswordField pw2field = new JPasswordField(30);
		pw2panel.add(pw2label);
		pw2panel.add(pw2field);
		
		JPanel birthpanel = new JPanel();
		JLabel birthlabel = new JLabel("생년월일(mm-dd-yyyy): ");
		JTextField birthfield = new JTextField(30);
		birthpanel.add(birthlabel);
		birthpanel.add(birthfield);
		
		JPanel addresspanel = new JPanel();
		JLabel addresslabel = new JLabel("주소: ");
		JTextField addressfield = new JTextField(30);
		addresspanel.add(addresslabel);
		addresspanel.add(addressfield);
		
		JPanel sexpanel = new JPanel();
		JLabel sexlabel = new JLabel("성별: ");
		ButtonGroup sexbtn = new ButtonGroup();
		JRadioButton m = new JRadioButton("남");
		JRadioButton f = new JRadioButton("여");
		sexbtn.add(m);
		sexbtn.add(f);
		sexpanel.add(sexlabel);
		sexpanel.add(m);
		sexpanel.add(f);
		
		JPanel jobpanel = new JPanel();
		JLabel joblabel = new JLabel("직업: ");
		JTextField jobfield = new JTextField(30);
		jobpanel.add(joblabel);
		jobpanel.add(jobfield);
		
		
		JPanel phonepanel = new JPanel();
		JLabel phonelabel = new JLabel("전화번호: ");
		JTextField phonefield = new JTextField(30);
		phonepanel.add(phonelabel);
		phonepanel.add(phonefield);
		
		
		JButton joinbtn = new JButton("JOIN");
		
		GridLayout gl = new GridLayout(11,1);
		setLayout(gl);
		
		add(join);
		add(namepanel);
		add(idpanel);
		add(pwpanel);
		add(pw2panel);
		add(birthpanel);
		add(addresspanel);
		add(sexpanel);
		add(jobpanel);
		add(phonepanel);
		add(joinbtn);
		
		
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
		
		id_duple_btn.addActionListener(new ActionListener() {
			//id중복확인버튼 눌렀을 때
			public void actionPerformed(ActionEvent e)
			{
				  String id = idfield.getText();
				  try {
					  conn.setAutoCommit(false);
					  stmt = conn.createStatement();
					 sql = "SELECT * FROM ACCOUNT WHERE Account_id = '"+id+"'";
					 ResultSet rs = stmt.executeQuery(sql);
					 //System.out.println(sql);
					 
					 if(id.compareTo("")==0) { //idfield에 아무입력 없을 때
							JOptionPane.showMessageDialog(null,"ID를 입력해주세요.");
						}
					 else 
					 {
					 if(rs.next()) //이미 있는 id일 때
						{
						 JOptionPane.showMessageDialog(null, "이미있는 ID입니다.");
						 idfield.setText("");
						 }
						 
						//사용가능한 id일 때
					else {
							JOptionPane.showMessageDialog(null, "사용가능한 ID입니다.");
							key = true;
						}
					 }
					 }catch(SQLException ex2) {					  
					  System.exit(1);
				  }	
			}
		});
		
		
		
		
		joinbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				
				if(key) //id중복확인을 했을 경우
				{
					if(pwfield.getText().equals(pw2field.getText()))
					{ //pw와 pw확인의 값이 같을 때
						name = namefield.getText();
						id = idfield.getText();
						pw = pwfield.getText();
						birth = birthfield.getText();
						address =addressfield.getText();
						if(sexbtn.getSelection().equals("남"))
							sex = "true";
						else 
							sex = "false";
						
						job = jobfield.getText();
						phone = phonefield.getText();
						
						try {
							sql = "INSERT INTO ACCOUNT VALUES('"+id+"','"+name+"','"+sex
									+"',TO_DATE('"+birth+"','mm-dd-yyyy'),'"+address+"','"+job+"','"+phone
									+"','"+pw+"','true')";
							System.out.println(sql);
							int res = stmt.executeUpdate(sql);
							conn.commit();
						}catch(SQLException ex2) {
							System.err.println("sql error = "+ex2.getMessage());
							System.exit(1);
						}
					  //account에 insert
						
						JOptionPane.showMessageDialog(null, "회원가입을 축하합니다!");
	                    dispose(); //회원가입창을 닫고
	                    new Login();	//다시 로그인창으로 돌아가기
					}
					else
					{ //pw와 pw확인의 값이 다를 때
	                    pwfield.setText("");
	                    pw2field.setText("");
	                    key = false;
	                    JOptionPane.showMessageDialog(null, "비밀번호를 재확인 해주세요.");
					}
	
				}
				else	//id중복확인 안한경우
				{
					JOptionPane.showMessageDialog(null, "ID 중복확인을 해주세요.");
					idfield.setText("");
                    pwfield.setText("");
                    pw2field.setText("");
				}
				
			}
		});
		
	}

}
