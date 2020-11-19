package teamproject3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.*;

public class Searchresult extends JFrame{
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	public static final String USER_UNIVERSITY ="university";
	public static final String USER_PASSWD ="comp322";
	Connection conn = null; // Connection object
	Statement stmt = null;   // Statement object
	String sql ="";
	ResultSet rs=null;

	public Searchresult(ArrayList<Integer> titlelist)
	{
		super.setSize(1000, 650);
		JPanel resultpanel = new JPanel(new BorderLayout()); //전체 패널
		JPanel listpanel = new JPanel(); //결과 영화 리스트 보여주는 패널
		JPanel btnPanel = new JPanel(new GridLayout(1,2)); //버튼 담을 패널
		
		JLabel listtitle = new JLabel("Option: ");//제목 출력
		
        JButton detailbtn = new JButton("상세보기");
        JButton closebtn = new JButton("취소");
        
        
        for (int i = 0;i<titlelist.size();i++)
        {
        	System.out.println(titlelist.get(i));
        }
        
        //버튼패널에 버튼 추가
        btnPanel.add(detailbtn);
        btnPanel.add(closebtn);
        
        this.add(listtitle, "North");
        this.add(btnPanel, "South");
        
        this.add(resultpanel);
        super.setVisible(true);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		/*디비 연결*/
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
	  
	  //상세정보 버튼 누르면 moredeep창으로 넘어가기
	  detailbtn.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				conn.setAutoCommit(false);
				stmt = conn.createStatement();
				
				JLabel temp = new JLabel(Integer.toString(titlelist.get(0)));
				new Moredeep();
			} catch(SQLException e1)
			{
				System.out.println("실패");
				System.exit(1);
			}
		}
	});
	  
	  //닫기 하면 닫기
	  closebtn.addActionListener(new ActionListener()
      {
      	public void actionPerformed(ActionEvent e)
      	{
      		dispose();
      		//new Searchresult();
      	}
      });
	}
}
