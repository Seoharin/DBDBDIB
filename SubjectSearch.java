package teamproject3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.*;

public class SubjectSearch extends JFrame {
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	public static final String USER_UNIVERSITY ="university";
	public static final String USER_PASSWD ="comp322";
	Connection conn = null; // Connection object
	Statement stmt = null;   // Statement object
	String sql ="";
	ArrayList<Integer> titlelist = new ArrayList<Integer>();
	
	public SubjectSearch(String title)
	{
		super(title);
		super.setSize(1000, 650);
		JPanel searchpanel = new JPanel();
		JLabel titlelabel = new JLabel("제목으로 검색하기");
		JTextField inputbox = new JTextField(20);
		JButton searchbtn = new JButton("조회");
		
		searchpanel.add(titlelabel);
		searchpanel.add(inputbox);
		searchpanel.add(searchbtn);
		
		this.add(searchpanel);

		super.setVisible(true);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		//DB 연결
		
	
		
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
		
		searchbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String inputtitle = inputbox.getText();
				try {
					conn.setAutoCommit(false);
					stmt = conn.createStatement();
					sql = "Select * from movie where title='"+inputtitle+"'";
					
					ResultSet rs = stmt.executeQuery(sql);
					
					while (rs.next()&&!titlelist.contains(rs.getInt("title_id")))
						titlelist.add(rs.getInt("title_id"));
					
					if (!titlelist.isEmpty())//결과가 있으면
					{
						new Searchresult(titlelist);
					}
					
					else
					{
						JOptionPane.showMessageDialog(null, "검색 결과가 없습니다.");
					}
				} catch(SQLException ex)
				{
					 System.err.println(ex.getMessage());
					System.out.println("실패");
					System.exit(1);
				}
				
//				
			
			}
		});
		
		
}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SubjectSearch("Knu movie");
	}


}
