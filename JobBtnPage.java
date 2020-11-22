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

public class JobBtnPage extends JFrame{
    
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	public static final String USER_UNIVERSITY ="university";
	public static final String USER_PASSWD ="comp322";
	Connection conn = null; // Connection object
	Statement stmt = null;   // Statement object
	String sql ="";
	
	public JobBtnPage(String id)
	{
		JPanel pwPanel = new JPanel(new BorderLayout());
	      
	    setLayout(new GridLayout(3,1));
		
		JPanel jobPanel = new JPanel();
		JLabel jobLabel = new JLabel("���� ����");
		
		JPanel newJobPanel = new JPanel();
		JTextField newJobWrite = new JTextField(20);
		
		JPanel closePanel = new JPanel();
	    JButton reBtn = new JButton("�Ϸ�");
	    JButton closeBtn = new JButton("�ݱ�");
	    
	    jobPanel.add(jobLabel);
	    newJobPanel.add(newJobWrite);
	    closePanel.add(reBtn);
	    closePanel.add(closeBtn);
	      
	    add(jobPanel);
	    add(newJobPanel);
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

	    // �����ϱ� ��ư
	    reBtn.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		String data = newJobWrite.getText(); 
	    		
	    		try{
	    			    conn.setAutoCommit(false);
	    			    stmt = conn.createStatement();
	    			    
	    			    sql = "update account set job = '"+data+"' where Account_id = '"+id+"' ";
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
	    	    JOptionPane.showMessageDialog(null, "���� �����Ϸ�!");
	    	    dispose();
	    	}
	    });
	    
	    // �ݱ� ��ư
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
