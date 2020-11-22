package teamJDBC;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Mypage_real extends JFrame{

	public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	public static final String USER_UNIVERSITY ="university";
	public static final String USER_PASSWD ="comp322";
	Connection conn = null; // Connection object
	Statement stmt = null;   // Statement object
	String sql ="";
	
	String name = "";
	String pw = "";
	String birth = "";
	String address = "";
	String sex = "";
	String job = "";
	String phone_number = "";
	String Regular_payment_day = "";
	String connect_account = "";
	String membership_name = "";
	int sub = 0;
	
	 // ���� : Account_id
	public Mypage_real(String id)
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
	  
	  /// Account���� �о���� ///
	  try {
		  conn.setAutoCommit(false);
		  stmt = conn.createStatement();
		  
		    // account_id�� ����ã��
		  sql = "select Name, Password, Birth, Address, Sex, Job, Phone_number"
		  		+ " from account"
		  		+ " where Account_id = '"+id+"'";
		  
		  ResultSet rs = stmt.executeQuery(sql);
		  
		  while(rs.next())  // �ش��ϴ� ������ JTextField�� ����
		  {
			  name = rs.getString(1); // �̸� �޾ƿ���
			  // id�� ���ڿ��� �޾ƿ���
			  
			  pw = rs.getString(2);  // pw �޾ƿ���
			  
			  birth = rs.getString(3);  // ���� �޾ƿ���
			  
			  address = rs.getString(4);  // �ּ� �޾ƿ���
			  
			  String guiSex = rs.getString(5);  // ���� �޾ƿ���
			  
			  if(guiSex == null)
				  sex = "null";
			  else if(guiSex.equals("false"))
				  sex = "female";
			  else
				  sex = "male"; 
			  	 
			  job = rs.getString(6);  // ���� �޾ƿ���
			  
			  phone_number = rs.getString(7);  // ����ȣ �޾ƿ���
		  }	  
	  }catch(SQLException ex2)
	  {
		  System.err.println("sql error1");
		  System.exit(1);
	  }
	  
	  try {
		  conn.setAutoCommit(false);
		  stmt = conn.createStatement();
		  
		  sql = "select count(*) from membership where Ac_id = '"+id+"'";
		  ResultSet rs = stmt.executeQuery(sql);
		  
		  while(rs.next())
		  {
			  sub = rs.getInt(1);
		  }
	  }catch(SQLException ex2)
	  {
		  System.err.println("sql error2");
		  System.exit(1);
	  }
	  
	  if(sub == 0)
	  {
		  Regular_payment_day = null;
		  connect_account = null;
		  membership_name = null;
	  }
	  
	  /// membership���� �о���� ///
	  try {
		  conn.setAutoCommit(false);
		  stmt = conn.createStatement();
		  
		   // account_id�� ����ã��
		  sql = "select Regular_payment_day, Connect_account, Membership_token"
		  		+ " from membership, account"
		  		+ " where Ac_id = Account_id and Account_id = '"+id+"'";
		  ResultSet rs = stmt.executeQuery(sql);
		  
		  while(rs.next()) 
		  {
              Regular_payment_day = rs.getString(1);  // ������ �޾ƿ���
			  
			  connect_account = rs.getString(2);  // ���� �޾ƿ���
			  
			  int membership_token = rs.getInt(3);   // ����� �̸�
			  if(membership_token == 0)
			  {
				  membership_name = "basic";
			  }
			  else if(membership_token == 1)
			  {
				  membership_name = "premium";
			  }
			  else if(membership_token == 2)
			  {
				  membership_name = "prime";
			  }
		  }
	  }catch(SQLException ex2)
	  {
		  System.err.println("sql error2");
		  System.exit(1);
	  }
		
        JPanel titlepanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("����������");
        JButton evalBtn = new JButton("�򰡳���");
        
        titlepanel.add(title, BorderLayout.WEST);
        titlepanel.add(evalBtn, BorderLayout.EAST);
        
        setLayout(new GridLayout(16,1));
        
        JPanel namepanel = new JPanel();
        JLabel namelabel = new JLabel("�̸�: " + name);
        
        JPanel idpanel = new JPanel();
        JLabel idlabel = new JLabel("ID: " + id);
       
        JPanel pwpanel = new JPanel();
        JLabel pwlabel = new JLabel("PW: " + pw);
        JButton pwBtn = new JButton("PW ����");
        
        JPanel birthpanel = new JPanel();
        JLabel birthlabel = new JLabel("�������(yyyymmdd): " + birth);
        
        JPanel genderpanel = new JPanel();
        JLabel genderlabel = new JLabel("����: " + sex);
        
        JPanel jobpanel = new JPanel();
        JLabel joblabel = new JLabel("����: " + job);
        JButton jobBtn = new JButton("���� ����");
        
        JPanel addresspanel = new JPanel();
        JLabel addresslabel = new JLabel("�ּ�: " + address);
        JButton addressBtn = new JButton("�ּ� ����");
        
        JPanel phonepanel = new JPanel();
        JLabel phonelabel = new JLabel("��ȭ��ȣ: " + phone_number);
        JButton phoneBtn = new JButton("��ȭ��ȣ ����");
        
        JPanel paydaypanel = new JPanel();
        JLabel paydaylabel = new JLabel("������: " + Regular_payment_day);
        
        JPanel con_accountpanel = new JPanel();
        JLabel con_accountlabel = new JLabel("���� ����: " + connect_account);   
        
        JPanel memnamepanel = new JPanel();
        JLabel memnamelabel = new JLabel("����� ����: " + membership_name);
        
        //// �� ��ư 3�� ActionLister ó���ϱ�
        JPanel nextpanel = new JPanel(new GridLayout(1,3));
        JButton membership = new JButton("����� ����");   
        JButton close = new JButton("�ݱ�");
        JButton getout = new JButton("Ż��");
        
        namepanel.add(namelabel);  
        
        idpanel.add(idlabel);  
        
        pwpanel.add(pwlabel);
        pwpanel.add(pwBtn);
        
        birthpanel.add(birthlabel); 
        
        addresspanel.add(addresslabel);
        addresspanel.add(addressBtn);
        
        genderpanel.add(genderlabel);   
        
        jobpanel.add(joblabel);
        jobpanel.add(jobBtn);
        
        phonepanel.add(phonelabel);
        phonepanel.add(phoneBtn);
        
        paydaypanel.add(paydaylabel);
        con_accountpanel.add(con_accountlabel);
        memnamepanel.add(memnamelabel);
        
        nextpanel.add(membership);
        nextpanel.add(close);
        nextpanel.add(getout);
        
        add(titlepanel);
        add(namepanel);
        add(idpanel);
        add(pwpanel);
        add(birthpanel);
        add(addresspanel);
        add(genderpanel);
        add(jobpanel);
        add(phonepanel);
        add(paydaypanel);
        add(con_accountpanel);
        add(memnamepanel);
        add(nextpanel);
        
        pwBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e)
        	{
        		new PwBtnPage(id);
        	}
        });
        
        jobBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e)
        	{
        		new JobBtnPage(id);
        	}
        });
        
        addressBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e)
        	{
        		new AddressBtnPage(id);
        	}
        });
        
        phoneBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e)
        	{
        		new PhoneBtnPage(id);
        	}
        });
        
        // ����� ����
  	  membership.addActionListener(new ActionListener(){
  	      	public void actionPerformed(ActionEvent e)
  	      	{
  	      		try {
  	      		   conn.setAutoCommit(false);
  			       stmt = conn.createStatement();
  			       sql = "select count(*) from membership where Ac_id = '"+id+"'";
  			       
                   ResultSet rs = stmt.executeQuery(sql);
  			       
                   while(rs.next())
                   {
                	   if(rs.getInt(1) == 0)   // ����� ����X -> ���� ������ ��
      			       {
                		   dispose();
      			    	   new No_membershipPage(id);
      			       }
      			       else                    // ����� ����O -> ����� â���� ��
      			       {
      			    	   new Membership(id);
      			       }
                   }
  			       
  	      		}catch(SQLException ex2)
				  {
					  System.err.println("sql1 error");
					  System.exit(1);
				  }
  	      	}
  	     });
     
	   // �ݱ�
	  close.addActionListener(new ActionListener(){
	      	public void actionPerformed(ActionEvent e)
	      	{
	      		dispose();
	      		new MovieList(id);
	      	}
	     });
		 
	   // Ż��
	  getout.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e)
		  {
			  try {
		    		conn.setAutoCommit(false);
	  			    stmt = conn.createStatement();
	  			    
	  			    sql = "delete from account where Account_id = '"+id+"'";
	  			    int rs = stmt.executeUpdate(sql);
	  			    
	  			    stmt.addBatch(sql);
	  			    
	  			    conn.commit();
	  			    
		    	}catch(SQLException ex) {System.out.println(ex.getMessage());}
			  finally {
				  try {
					  stmt.close();
					  conn.close();
					  
					  dispose();
					  new MovieList(id);
					  
				  }catch(SQLException ex) {
					  
				  }
			  }
	  		   
			  JOptionPane.showMessageDialog(null, "Ż�� �Ϸ�̤�");
		  }
	  });
	  
	  evalBtn.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e)
		  {
			  new ShowRating(id);
		  }
	  });
	  
	    setVisible(true);
	    setSize(1000,650);
	    setLocationRelativeTo(null);    
	    setResizable(false);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	}

}







