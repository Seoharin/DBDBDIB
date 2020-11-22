package teamJDBC;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

 // title_id �޾ƿ���
public class Search_titleResult extends JFrame{

	  ArrayList<Integer> optionlist = new ArrayList<Integer>();          // 
	  ArrayList<JRadioButton> btnlist = new ArrayList<JRadioButton>();   // ��ȭ�� ����Ʈ
	  
	    public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
		public static final String USER_UNIVERSITY ="university";
		public static final String USER_PASSWD ="comp322";
		Connection conn = null; // Connection object
		Statement stmt = null;   // Statement object
		String sql ="";
	  
		String queryTitle = "";
		String titleList = "";
		String admin_id = ""; 
	
      public Search_titleResult(ArrayList<Integer> optionlist)
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
  	  
    	  // ����
    	  JPanel titlePanel = new JPanel(new BorderLayout());
      	  JLabel titleLabel = new JLabel("�˻� ���");
      	  titlePanel.add(titleLabel);
      	  
      	  // �ش�Ǵ� ��ȭ�� ����Ʈ
      	  JPanel listPanel = new JPanel();
      	  listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
      	  ButtonGroup list = new ButtonGroup();
		  JScrollPane scroll = new JScrollPane(listPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    	  
		  // ��ư
		  JPanel twoBtn = new JPanel();
		  JButton detail = new JButton("�󼼺���");
		  JButton close = new JButton("�ݱ�");
		  twoBtn.add(detail);
		  twoBtn.add(close);
		
  	  try {
  		  conn = DriverManager.getConnection(URL,USER_UNIVERSITY,USER_PASSWD);
  	     }catch(SQLException ex) {
  	        ex.printStackTrace();
  	        System.err.println("Cannot get a connection: "+ex.getMessage());
  	        System.exit(1);
  	     }
    	 
  	  
  	  for(int i = 0; i < optionlist.size(); i++)
  	  {
  		 try {
  	  		  conn.setAutoCommit(false);
  			  stmt = conn.createStatement();
  			    
  			  sql = "select title from movie where title_id = " +optionlist.get(i);
  			     /// optionlist.get(i) : i��° ����� title_id
  		  	  	  
  			  ResultSet rs = stmt.executeQuery(sql);
  			  
  			  while(rs.next())
  			  {
  				  queryTitle = rs.getString(1);
  				  titleList =  optionlist.get(i) + queryTitle;  // ���پȿ� �� ����
  				  
  				  btnlist.add(new JRadioButton(titleList));   // buttonGroup
                  list.add((AbstractButton) btnlist.get(i));
                  listPanel.add((AbstractButton) btnlist.get(i)); 
  			  }
  			  rs.close();
  			  
  	  	  }catch(SQLException ex2)
  		  {
  			  System.err.println("sql error1");
  			  System.exit(1);
  		  }
  	  }
  	  
  	// �󼼺��� 	  
  		  detail.addActionListener(new ActionListener(){
  		      	public void actionPerformed(ActionEvent e)
  		      	{
  		      		for(int i = 0; i < btnlist.size(); i++)
  		      		{
  		      		     if(((AbstractButton) btnlist.get(i)).isSelected())
  		      		     {
  		      		    	try {
  		      		  		  conn.setAutoCommit(false);
  		      				  stmt = conn.createStatement();
  		      				  
  		      				  sql = "select Admin_id from movie where Title_id = '"+optionlist.get(i)+"'";
  		      				  
  		      				  ResultSet rs = stmt.executeQuery(sql);
  		      				  
  		      				  while(rs.next())
  		      				  {
  		      					  admin_id = rs.getString(1);
  		      					  new Moredeep(optionlist.get(i), admin_id);
  		      					  dispose();
  		      				  }
  		      				  
  		      		  	    }catch(SQLException ex2)
  		      				  {
  		      					  System.err.println("sql error1");
  		      					  System.exit(1);
  		      				  }
  		      		     }
  		      		}
  		      	   
  		      	}
  		     });
  		    
  	  
  	 // �ݱ�
	  close.addActionListener(new ActionListener(){
	      	public void actionPerformed(ActionEvent e)
	      	{
	      		dispose();
	      		new OptionSearch();
	      	}
	     });
  	  
  	 
  	  add(titlePanel,BorderLayout.NORTH);
  	  add(scroll,BorderLayout.CENTER);
  	  add(twoBtn,BorderLayout.SOUTH);
  	  
    	  
    	  setVisible(true);
  	      setSize(1000,650);
  	      setLocationRelativeTo(null);    
  	      setResizable(false);
  	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      }
}
