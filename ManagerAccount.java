package teamproject3;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ManagerAccount extends JFrame {

	public ManagerAccount()
	{
	 JPanel titlepanel = new JPanel(new BorderLayout());
     JLabel titlelabel = new JLabel("�����ڰ���");
     JButton register = new JButton("���");
     JButton logout = new JButton("�α׾ƿ�");
     
     titlepanel.add(titlelabel, BorderLayout.WEST);
     titlepanel.add(register, BorderLayout.EAST);
     titlepanel.add(logout, BorderLayout.EAST);
     
     add(titlepanel);
     
     setVisible(true);
     setSize(1000,650);
     setLocationRelativeTo(null);    
     setResizable(false);
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
