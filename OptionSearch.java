package teamJDBC;

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

public class OptionSearch extends JFrame {
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	public static final String USER_UNIVERSITY ="university";
	public static final String USER_PASSWD ="comp322";
	Connection conn = null; // Connection object
	Statement stmt = null;   // Statement object
	String sql ="";
	ResultSet rs=null;
	ArrayList<Integer> titlelist = new ArrayList<Integer>();

	public OptionSearch()
	{
		JPanel opsearchpanel = new JPanel(new BorderLayout()); //전체 패널(옵션패널+버튼 담음)
		JPanel optionpanel = new JPanel(); //옵션패널 담는 패널
		JPanel typepanel = new JPanel(new GridLayout(4,1)); //타입옵션들 있는 패널
		JPanel genrepanel = new JPanel(new GridLayout(11, 1)); //장르옵션들 있는 패널
		JPanel versionpanel = new JPanel(new GridLayout(7,1)); //버전옵션들 있는 패널
		
		JLabel typelabel = new JLabel("TYPE");
		JLabel genrelabel = new JLabel("GENRE");
		JLabel versionlabel = new JLabel("VERSION");
		
		JButton optionsearchbtn = new JButton("조회");

		JCheckBox type1 = new JCheckBox("Movie", false); //m
		JCheckBox type2 = new JCheckBox("Tv Series", false); //s
		JCheckBox type3 = new JCheckBox("Knu MovieDB Original", false); //o
		
		JCheckBox genre1 = new JCheckBox("Horror", false); //1
		JCheckBox genre2 = new JCheckBox("Thriller", false); //2
		JCheckBox genre3 = new JCheckBox("Sci-Fi", false); //3
		JCheckBox genre4 = new JCheckBox("Crime", false); //4
		JCheckBox genre5 = new JCheckBox("Drama", false); //5
		JCheckBox genre6 = new JCheckBox("Fantasy", false); //6
		JCheckBox genre7 = new JCheckBox("Animation", false); //7
		JCheckBox genre8 = new JCheckBox("Comedy", false); //8
		JCheckBox genre9 = new JCheckBox("Romance", false); //9
		JCheckBox genre10 = new JCheckBox("Action", false); //10
		
		JCheckBox version1 = new JCheckBox("Korean", false); //1
		JCheckBox version2 = new JCheckBox("English(US)", false); //2
		JCheckBox version3 = new JCheckBox("English(UK)", false); //3
		JCheckBox version4 = new JCheckBox("Japanese", false); //4
		JCheckBox version5 = new JCheckBox("Chinese", false); //5
		JCheckBox version6 = new JCheckBox("French", false); //6
		
		
		/*체크박스 추가*/
		typepanel.add(typelabel);
		typepanel.add(type1);
		typepanel.add(type2);
		typepanel.add(type3);
		
		genrepanel.add(genrelabel);
		genrepanel.add(genre1);
		genrepanel.add(genre2);
		genrepanel.add(genre3);
		genrepanel.add(genre4);
		genrepanel.add(genre5);
		genrepanel.add(genre6);
		genrepanel.add(genre7);
		genrepanel.add(genre8);
		genrepanel.add(genre9);
		genrepanel.add(genre10);
		
		versionpanel.add(versionlabel);
		versionpanel.add(version1);
		versionpanel.add(version2);
		versionpanel.add(version3);
		versionpanel.add(version4);
		versionpanel.add(version5);
		versionpanel.add(version6);

		optionpanel.add(typepanel);
		optionpanel.add(genrepanel);
		optionpanel.add(versionpanel);
		opsearchpanel.add(optionpanel, "Center");
		opsearchpanel.add(optionsearchbtn, "South");
		this.add(opsearchpanel);
		
		
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
	
	optionsearchbtn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e)
		{
			try {
				conn.setAutoCommit(false);
				stmt = conn.createStatement();
				
				if (type1.isSelected())
				{
					sql = "select * from movie where type ='m'";
					rs = stmt.executeQuery(sql);

					while(rs.next())
					{
						titlelist.add(rs.getInt(1));
					}
					
				}
				
				if (type2.isSelected())
				{
					
					sql = "select title_id from movie where type ='s'";
					rs = stmt.executeQuery(sql);
					while(rs.next())
						titlelist.add(rs.getInt("title_id"));
					
					
				}
				if (type3.isSelected())
				{
					sql = "select title_id from movie where type ='o'";
					rs = stmt.executeQuery(sql);
					while(rs.next())
						titlelist.add(rs.getInt("title_id"));
					

					
				}
				
				
				if (genre1.isSelected())
				{
					sql = "select * from movie, has_genre where title_id = M_id AND Gnum =1";
					rs = stmt.executeQuery(sql);
					
					//System.out.println(rs.getInt("title_id"));
					while (rs.next()&&!titlelist.contains(rs.getInt("title_id")))
						titlelist.add(rs.getInt("title_id"));
					

				}
				if (genre2.isSelected())
				{
					sql = "select title_id from movie, has_genre where title_id = M_id AND Gnum =2";
					rs = stmt.executeQuery(sql);
					
					while (rs.next()&&!titlelist.contains(rs.getInt("title_id"))) //titlelist에 없으면
						titlelist.add(rs.getInt("title_id"));
				}
				if (genre3.isSelected())
				{
					sql = "select title_id from movie, has_genre where title_id = M_id AND Gnum =3";
					rs = stmt.executeQuery(sql);
					
					while (rs.next()&&!titlelist.contains(rs.getInt("title_id")))
						titlelist.add(rs.getInt("title_id"));
				}
				if (genre4.isSelected())
				{
					sql = "select title_id from movie, has_genre where title_id = M_id AND Gnum =4";
					rs = stmt.executeQuery(sql);
					while (rs.next()&&!titlelist.contains(rs.getInt("title_id")))
						titlelist.add(rs.getInt("title_id"));
				}
				if (genre5.isSelected())
				{
					sql = "select title_id from movie, has_genre where title_id = M_id AND Gnum =5";
					rs = stmt.executeQuery(sql);
					
					while (rs.next()&&!titlelist.contains(rs.getInt("title_id")))
						titlelist.add(rs.getInt("title_id"));
				}
				if (genre6.isSelected())
				{
					sql = "select title_id from movie, has_genre where title_id = M_id AND Gnum =6";
					rs = stmt.executeQuery(sql);
					while (rs.next()&&!titlelist.contains(rs.getInt("title_id")))
						titlelist.add(rs.getInt("title_id"));
				}
				if (genre7.isSelected())
				{
					sql = "select title_id from movie, has_genre where title_id = M_id AND Gnum =7";
					rs = stmt.executeQuery(sql);
					while (rs.next()&&!titlelist.contains(rs.getInt("title_id")))
						titlelist.add(rs.getInt("title_id"));
				}
				if (genre8.isSelected())
				{
					sql = "select title_id from movie, has_genre where title_id = M_id AND Gnum =8";
					rs = stmt.executeQuery(sql);
					while (rs.next()&&!titlelist.contains(rs.getInt("title_id")))
						titlelist.add(rs.getInt("title_id"));
				}
				if (genre9.isSelected())
				{
					sql = "select title_id from movie, has_genre where title_id = M_id AND Gnum =9";
					rs = stmt.executeQuery(sql);
					while (rs.next()&&!titlelist.contains(rs.getInt("title_id")))
						titlelist.add(rs.getInt("title_id"));
				}
				if (genre10.isSelected())
				{
					sql = "select title_id from movie, has_genre where title_id = M_id AND Gnum =10";
					rs = stmt.executeQuery(sql);
					
					while (rs.next()&&!titlelist.contains(rs.getInt("title_id")))
						titlelist.add(rs.getInt("title_id"));
				}
				
				//version 추가하기
				if (version1.isSelected())
				{
					sql = "select * from movie, has_version where title_id = Acs_id AND V_id=1";
					rs = stmt.executeQuery(sql);
				
					while (rs.next()&&!titlelist.contains(rs.getInt("title_id")))
						titlelist.add(rs.getInt("title_id"));
				}
				
				if (version2.isSelected())
				{
					sql = "select * from movie, has_version where title_id = Acs_id AND V_id=2";
					rs = stmt.executeQuery(sql);
					while (rs.next()&&!titlelist.contains(rs.getInt("title_id")))
						titlelist.add(rs.getInt("title_id"));
				}
				
				if (version3.isSelected())
				{
					sql = "select title_id from movie, has_version where title_id = Acs_id AND V_id =3";
					rs = stmt.executeQuery(sql);
					while (rs.next()&&!titlelist.contains(rs.getInt("title_id")))
						titlelist.add(rs.getInt("title_id"));
				}
				
				if (version4.isSelected())
				{
					sql = "select title_id from movie, has_version where title_id = Acs_id AND V_id =4";
					rs = stmt.executeQuery(sql);
					while (rs.next()&&!titlelist.contains(rs.getInt("title_id")))
						titlelist.add(rs.getInt("title_id"));
				}
				
				if (version5.isSelected())
				{
					sql = "select title_id from movie, has_version where title_id = Acs_id AND V_id =5";
					rs = stmt.executeQuery(sql);
					while (rs.next()&&!titlelist.contains(rs.getInt("title_id")))
						titlelist.add(rs.getInt("title_id"));
				}
				
				if (version6.isSelected())
				{
					sql = "select title_id from movie, has_version where title_id = Acs_id AND V_id =6";
					rs = stmt.executeQuery(sql);
					while (rs.next()&&!titlelist.contains(rs.getInt("title_id")))
						titlelist.add(rs.getInt("title_id"));
				}
				
				System.out.println("추가 완료");
				
				if (titlelist.isEmpty()==true)//아무것도 체크 안했으면
					JOptionPane.showMessageDialog(null, "조건을 선택해 주세요.");
				else {//뭐라도 선택 되면
					
					new Search_genreResult(titlelist);
					
				}
			} catch(SQLException ex)
			{
				JOptionPane.showMessageDialog(null, sql);
				System.err.println(ex.getMessage());
				System.out.println("실패");
				System.exit(1);
			}
			
//			
		
		}
});
		
	    setVisible(true);
	    setSize(1000,650);
	    setLocationRelativeTo(null);    
	    setResizable(false);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

}
