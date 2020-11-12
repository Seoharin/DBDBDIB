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
     JLabel titlelabel = new JLabel("관리자계정");
     JButton register = new JButton("등록");
     JButton logout = new JButton("로그아웃");
     
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
